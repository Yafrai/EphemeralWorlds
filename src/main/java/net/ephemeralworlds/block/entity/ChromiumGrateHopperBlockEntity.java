package net.ephemeralworlds.block.entity;

//public class ChromiumGrateHopperBlockEntity extends ModTickingBlockEntity implements BlockEntityClientSerializable {
//
//    protected boolean working;
//    protected int steps;
//    protected final int STEPS = 30;
//
////    public ChromiumGrateHopperBlockEntity() {
////        super(ModBlockEntities.CHROMIUM_HOPPER_ENTITY);
////    }
//
//    protected int getTicks() {
//        return 20;
//    }
//
//    protected void onTick() {
//        if (!world.isClient) {
//
//            boolean valid = validateStructure();
//
//            if (!valid && working) {
//                // Turn down extraction
//                working = false;
//                steps = 0;
//                saveAndNotify();
//            }
//            else if (valid && !working) {
//                // Start extraction
//                working = true;
//                steps = 0;
//                saveAndNotify();
//            }
//            else if (valid) {
//                // Keep extracting
//                steps++;
//
//                if (steps >= STEPS)
//                    consumeBlock();
//
//                saveAndNotify();
//            }
//        }
//        else {
//            if (working) {
////                world.addParticle(ModParticleEffect.RED, pos.getX() -.5, pos.getY(), pos.getZ() - .5, 0.0D, 0.0D, 0.0D);
//                world.addParticle(ParticleTypes.DRIPPING_WATER, pos.getX() + .5, pos.getY(), pos.getZ() + .5, 0.0D, 0.0D, 0.0D);
//            }
//        }
//    }
//
//    private void consumeBlock() {
//        steps = 0;
//        working = false;
//
//        EnumColor color = ColorHelper.getBlockColor(world, pos.up());
//
//        if (color == null)
//            return;
//
//        BlockEntity downBlockEntity = world.getBlockEntity(pos.down());
//
//        if (downBlockEntity instanceof ModInkTankBlockEntity) {
//            ((ModInkTankBlockEntity)downBlockEntity).receiveInk(1, color);
//        }
//
//        // todo put back
////        world.setBlockState(pos.up(), Blocks.AIR.getDefaultState());
//
//    }
//
//    private boolean validateStructure() {
//        boolean valid = true;
//
//        // Color block on top
//        Block colorBlock = world.getBlockState(pos.up()).getBlock();
//        if (!(colorBlock instanceof ColorBlock))
//            return false;
//
//        // Diachronium piston with pressure on color block
//        BlockState pistonState = world.getBlockState(pos.up(2));
//        Block piston = pistonState.getBlock();
//        if (!(piston instanceof DiachroniumPiston && DiachroniumPiston.isHeadingDownwards(pistonState) && world.isReceivingRedstonePower(pos.up(2))))
//            return false;
//
//        // InkTank below
//        BlockEntity inkTankBlockEntity = world.getBlockEntity(pos.down());
//        if (!(inkTankBlockEntity instanceof ModInkTankBlockEntity))
//            return false;
//
//        // InkTank color and capacity
//        EnumColor color = ColorHelper.getBlockColor(world, pos.up());
//        if (!((ModInkTankBlockEntity)inkTankBlockEntity).canReceiveColor(color))
//            return false;
//
//        return true;
//    }
//
//    @Override
//    public void fromClientTag(NbtCompound tag) {
//        working = tag.getBoolean("working");
//    }
//
//    @Override
//    public NbtCompound toClientTag(NbtCompound tag) {
//        tag.putBoolean("working", working);
//        return tag;
//    }
//
//    public NbtCompound writeNbt(NbtCompound tag) {
//        super.writeNbt(tag);
//        tag.putBoolean("working", working);
//        tag.putInt("steps", steps);
//        return tag;
//    }
//
//    public void readNbt(NbtCompound tag) {
//        super.readNbt(tag);
//        working = tag.getBoolean("working");
//        steps = tag.getInt("steps");
//    }
//
//}
