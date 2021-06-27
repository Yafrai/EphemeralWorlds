package net.ephemeralworlds.mixin;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.registry.ModDimensions;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import net.minecraft.world.Heightmap;
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

//    @Inject(method = "usePortal", at = @At("HEAD"), cancellable = true)
//    private void usePortal(Entity entity, float float_1, CallbackInfoReturnable<Boolean> info) {
//
//        // Enter illusion
////        if (world.getDimension().getType() == ModDimensions.spiritWorld) {
//////            CompoundTag tag = PlayerData.get((PlayerEntity) entity, EphemeralWorlds.MODID); // todo
//////            tag.putLong("pos", entity.getBlockPos().asLong());
//////            tag.putInt("dimension", entity.getEntityWorld().getDimension().getType().getRawId());
////
////            PlayerEntity player = (PlayerEntity)entity;
//////            PlayerData.markDirty(player); // todo
////
////            if (!player.isCreative())
////                player.setGameMode(GameMode.ADVENTURE);
////
//////            InstanceOptions options = DimensionHelper.getPlayerInstanceOptions((PlayerEntity)entity);
////
//////            if (options != null) {
//////                entity.setPosition(options.getSpawnX(), options.getSpawnY(), options.getSpawnZ());
//////            }
////
////            BlockPos spawn = new BlockPos(0, 0, 0);
////            int y = world.getChunk(spawn).getHeightmap(Heightmap.Type.WORLD_SURFACE).get(spawn.getX(), spawn.getZ());
////            spawn = spawn.up(y);
////
////            entity.setPosition(spawn.getX(), spawn.getY(), spawn.getZ());
////
////            BlockState landingBlock = world.getBlockState(entity.getBlockPos().down());
////            BlockState standingBlock1 = world.getBlockState(entity.getBlockPos());
////            BlockState standingBlock2 = world.getBlockState(entity.getBlockPos().up());
////
////            if (landingBlock.isAir() || standingBlock1.isOpaque() || standingBlock2.isOpaque()) {
////                Structure plate = this.world.getStructureManager().getStructure(new Identifier(EphemeralWorlds.MODID, "plate"));
////                if (plate != null) {
////                    plate.place(this.world, entity.getBlockPos().down(), new StructurePlacementData());
////                }
////            }
////
////            info.setReturnValue(true);
////        }
////
////        // Leave illusion
////        else if (entity.getEntityWorld().getDimension().getType() == ModDimensions.spiritWorld) {
////
////            PlayerEntity player = (PlayerEntity)entity;
//////            CompoundTag tag = PlayerData.get(player, EphemeralWorlds.MODID); // todo
////
//////            if (tag.containsKey("pos")) { // todo
//////                BlockPos warpTo = BlockPos.fromLong(tag.getLong("pos"));
//////                entity.setPosition(warpTo.getX(), warpTo.getY(), warpTo.getZ());
//////            }
////
////            if (!player.isCreative())
////                player.setGameMode(GameMode.SURVIVAL);
////
////            info.setReturnValue(true);
////        }
//    }
}