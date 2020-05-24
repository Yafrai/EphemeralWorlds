package net.ephemeralworlds.block.entity.effigy;

import net.ephemeralworlds.block.entity.base.AEffigyBlockEntity;
import net.ephemeralworlds.recipe.TinkeringTableRecipe;
import net.ephemeralworlds.registry.ModBlockEntities;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.helpers.PlayerHelper;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import java.util.Optional;


public class EffigyProjectionBlockEntity extends AEffigyBlockEntity {

    public EffigyProjectionBlockEntity() {
        super(ModBlockEntities.EFFIGY_PROJECTION_BLOCK_ENTITY);
    }

    public boolean interact(PlayerEntity player, BlockHitResult hitResult) {
        return true;
    }
}
