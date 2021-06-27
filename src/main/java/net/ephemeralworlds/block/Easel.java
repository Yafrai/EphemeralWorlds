package net.ephemeralworlds.block;

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
//    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
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
