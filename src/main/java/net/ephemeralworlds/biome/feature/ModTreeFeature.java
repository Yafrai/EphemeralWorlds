package net.ephemeralworlds.biome.feature;

//import com.mojang.datafixers.Dynamic;
//import net.ephemeralworlds.registry.ModBlocks;
//import net.ephemeralworlds.utils.enums.EnumColor;
//import net.ephemeralworlds.utils.tools.BlockAndPos;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.util.math.BlockBox;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.ModifiableTestableWorld;
//import net.minecraft.world.TestableWorld;
//import net.minecraft.world.gen.feature.AbstractTreeFeature;
//import net.minecraft.world.gen.feature.DefaultFeatureConfig;
//import net.minecraft.world.gen.feature.TreeFeatureConfig;
//
//import java.util.List;
//import java.util.Random;
//import java.util.Set;
//import java.util.function.Function;
//
//public abstract class ModTreeFeature extends AbstractTreeFeature<TreeFeatureConfig> {
//
////    private final boolean alwaysTall;
////    protected static BlockState LOG = ModBlocks.COLOR_LOG.getDefaultState();
////    protected static BlockState LEAVES = ModBlocks.COLOR_LEAVES.getDefaultState();
//    protected static BlockState DIRT = ModBlocks.COLOR_DIRT.getDefaultState();
//
//    public ModTreeFeature(Function<Dynamic<?>, ? extends TreeFeatureConfig> configDeserializer) {
//        super(configDeserializer);
////        this.alwaysTall = alwaysTall;
//    }
//
//    protected static boolean isDirtOrGrass(TestableWorld testableWorld_1, BlockPos blockPos_1) {
//        return testableWorld_1.testBlockState(blockPos_1, (blockState_1) -> {
//            Block block_1 = blockState_1.getBlock();
//            return block_1 == ModBlocks.COLOR_DIRT || block_1 == ModBlocks.COLOR_GRASS;
//        });
//    }
//
//    protected void setToDirt(ModifiableTestableWorld modifiableTestableWorld_1, BlockPos blockPos_1) {
//        modifiableTestableWorld_1.testBlockState(blockPos_1, (blockState_1) -> {
//            if (blockState_1.getBlock() != ModBlocks.COLOR_DIRT) {
//                this.setBlockState(modifiableTestableWorld_1, blockPos_1, DIRT);
//                return true;
//            }
//            return false;
//        });
//    }
//
//    protected boolean canReplaceBlock(ModifiableTestableWorld modifiableTestableWorld_1, BlockPos blockPos_1) {
//        return modifiableTestableWorld_1.testBlockState(blockPos_1,
//                (blockState_1) -> (blockState_1.isAir() || blockState_1.getBlock() == ModBlocks.COLOR_LEAVES)
//        );
//    }
//
//    public abstract int getVariant(Random random);
//    public abstract List<BlockAndPos> getBlocks(BlockPos pos, int variant, EnumColor color, boolean regenerateOnly);
//
//    @Override
//    protected boolean generate(ModifiableTestableWorld modifiableTestableWorld, Random random, BlockPos position, Set<BlockPos> logPositions, Set<BlockPos> leavesPositions, BlockBox blockBox, TreeFeatureConfig config) {
////    public boolean generate(Set<BlockPos> set, ModifiableTestableWorld modifiableTestableWorld, Random random, BlockPos position, MutableIntBoundingBox mutableIntBoundingBox) {
//        int variant = getVariant(random);
////        EnumColor color = DimensionHelper.getColorFromPosition((World)modifiableTestableWorld, position);
//        EnumColor color = EnumColor.BLUE;
////        List<BlockAndPos> blockList = getBlocks(position, variant, color, false);
//
//        // Validate blocks and soil
//        if (!isDirtOrGrass(modifiableTestableWorld, position.down()))
//            return false;
//
//        for (BlockPos actual_pos : logPositions) {
//            if (actual_pos.getY() >= 0 && actual_pos.getY() < 256) {
//                if (!canReplaceBlock(modifiableTestableWorld, actual_pos)) {
//                    return false;
//                }
//            } else {
//                return false;
//            }
//        }
//
//        // Replace blocks
//        // todo mirror / rotate in variant system
//        setToDirt(modifiableTestableWorld, position.down());
//
//        for (BlockPos bp : logPositions) {
//            this.setBlockState(modifiableTestableWorld, bp, config.trunkProvider.getBlockState(random, position), blockBox);
//        }
//
//        return true;
//    }
//
//    public void addBlock(BlockState state, boolean required, BlockPos position, List<BlockAndPos> list) {
//        list.add(new BlockAndPos(state, position, required));
//    }
//
//    public void addSquare(BlockState state, boolean required, BlockPos center, int halfWidth, List<BlockAndPos> list) {
//        for (int i=center.getX()-halfWidth; i<=center.getX()+halfWidth; i++) {
//            for (int k=center.getZ()-halfWidth; k<=center.getZ()+halfWidth; k++) {
//                if (i != center.getX() || k != center.getZ()) {
//                    list.add(new BlockAndPos(state, new BlockPos(i, center.getY(), k), required));
//                }
//            }
//        }
//    }
//
//    public void addDiamond(BlockState state, boolean required, BlockPos center, int radius, List<BlockAndPos> list) {
//        for (int i=center.getX()-radius; i<=center.getX()+radius; i++) {
//            int leftRadius = radius - Math.abs(center.getX() - i);
//            for (int k=center.getZ()-leftRadius; k<=center.getZ()+leftRadius; k++) {
//                if (i != center.getX() || k != center.getZ()) {
//                    list.add(new BlockAndPos(state, new BlockPos(i, center.getY(), k), required));
//                }
//            }
//        }
//    }
//
//    public void addCross(BlockState state, boolean required, BlockPos center, int radius, boolean fillCenter, List<BlockAndPos> list) {
//        for (int i=-radius; i<=radius; i++) {
//            if (i==0) {
//                if (fillCenter)
//                    list.add(new BlockAndPos(state, new BlockPos(center.getX(), center.getY(), center.getZ()), required));
//                continue;
//            }
//
//            list.add(new BlockAndPos(state, new BlockPos(center.getX()+i, center.getY(), center.getZ()+i), required));
//            list.add(new BlockAndPos(state, new BlockPos(center.getX()-i, center.getY(), center.getZ()+i), required));
//        }
//    }
//
//    public void addHash(BlockState state, boolean required, BlockPos center, boolean fillCenter, List<BlockAndPos> list) {
//        for (int i=center.getX()-2; i<=center.getX()+2; i++) {
//            for (int k=center.getZ()-2; k<=center.getZ()+2; k++) {
//                if (i==0 && k==0) {
//                    if (fillCenter)
//                        list.add(new BlockAndPos(state, new BlockPos(i, center.getY(), k), required));
//                    continue;
//                }
//
//                if (Math.abs(i - center.getX()) == 1 || Math.abs(k - center.getZ()) == 1)
//                    list.add(new BlockAndPos(state, new BlockPos(i, center.getY(), k), required));
//            }
//        }
//    }
//
//}
