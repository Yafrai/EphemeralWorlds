package net.ephemeralworlds.block;

import net.ephemeralworlds.block.base.ModBlock;
import net.ephemeralworlds.block.base.ModOrientableBlock;
import net.ephemeralworlds.block.base.ModPillarBlock;
import net.ephemeralworlds.block.entity.InkJarBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

//public class Easel extends ModOrientableBlock implements BlockEntityProvider {
//
//    public Easel(String uname, Block base) {
//        super(uname, base);
//    }
//
//    public BlockRenderLayer getRenderLayer() {
//        return BlockRenderLayer.TRANSLUCENT;
//    }
//
//    @Override
//    public BlockEntity createBlockEntity(BlockView var1) {
//        return new EaselBlockEntity();
//    }
//
//    @Override
//    public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
//        BlockEntity blockEntity = world.getBlockEntity(pos);
//
//        if (!(blockEntity instanceof EaselBlockEntity)) {
//            return false;
//        }
//
//        return ((EaselBlockEntity)blockEntity).interact(player);
//    }
//}
