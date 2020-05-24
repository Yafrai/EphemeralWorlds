package net.ephemeralworlds.mixin;

import com.raphydaphy.crochet.data.PlayerData;
import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.registry.ModDimensions;
import net.ephemeralworlds.utils.helpers.DimensionHelper;
import net.ephemeralworlds.utils.instanceHandler.InstanceOptions;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.PortalForcer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PortalForcer.class)
public class IllusionPortalForcerMixin {
    @Shadow @Final
    private ServerWorld world;

    @Inject(method = "usePortal", at = @At("HEAD"), cancellable = true)
    private void usePortal(Entity entity, float float_1, CallbackInfoReturnable<Boolean> info) {

        // Enter illusion
        if (world.getDimension().getType() == ModDimensions.ephemerium) {
            CompoundTag tag = PlayerData.get((PlayerEntity) entity, EphemeralWorlds.MODID);
            tag.putLong("pos", entity.getBlockPos().asLong());
            tag.putInt("dimension", entity.getEntityWorld().getDimension().getType().getRawId());
            PlayerData.markDirty((PlayerEntity)entity);

            InstanceOptions options = DimensionHelper.getPlayerInstanceOptions((PlayerEntity)entity);

            if (options != null) {
                entity.setPosition(options.getSpawnX(), options.getSpawnY(), options.getSpawnZ());
            }

            BlockState landingBlock = world.getBlockState(entity.getBlockPos().down());
            BlockState standingBlock1 = world.getBlockState(entity.getBlockPos());
            BlockState standingBlock2 = world.getBlockState(entity.getBlockPos().up());

            if (landingBlock.isAir() || standingBlock1.isOpaque() || standingBlock2.isOpaque()) {
                Structure plate = this.world.getStructureManager().getStructure(new Identifier(EphemeralWorlds.MODID, "plate"));
                if (plate != null) {
                    plate.place(this.world, entity.getBlockPos().down(), new StructurePlacementData());
                }
            }

            info.setReturnValue(true);
        }

        // Leave illusion
        else if (entity.getEntityWorld().getDimension().getType() == ModDimensions.ephemerium) {
            CompoundTag tag = PlayerData.get((PlayerEntity) entity, EphemeralWorlds.MODID);

            if (tag.containsKey("pos")) {
                BlockPos warpTo = BlockPos.fromLong(tag.getLong("pos"));
                entity.setPosition(warpTo.getX(), warpTo.getY(), warpTo.getZ());
            }

            info.setReturnValue(true);
        }
    }
}