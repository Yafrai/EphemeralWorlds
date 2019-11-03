package net.ephemeralworlds.biome.feature;

import com.mojang.datafixers.Dynamic;
import net.ephemeralworlds.block.base.ModBlock;
import net.ephemeralworlds.registry.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public abstract class ModTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig> {

    private final boolean alwaysTall;
    private static BlockState LOG = ModBlocks.COLOR_LOG.getDefaultState();
    private static BlockState LEAVES = ModBlocks.COLOR_LEAVES.getDefaultState();

    public ModTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> configDeserializer, boolean emitNeighborBlockUpdates, boolean alwaysTall) {
        super(configDeserializer, emitNeighborBlockUpdates);
        this.alwaysTall = alwaysTall;
    }

    protected static boolean isDirtOrGrass(TestableWorld testableWorld_1, BlockPos blockPos_1) {
        return testableWorld_1.testBlockState(blockPos_1, (blockState_1) -> {
            Block block_1 = blockState_1.getBlock();
            return block_1 == ModBlocks.COLOR_DIRT || block_1 == ModBlocks.COLOR_GRASS;
        });
    }

    protected void setToDirt(ModifiableTestableWorld modifiableTestableWorld_1, BlockPos blockPos_1) {
        modifiableTestableWorld_1.testBlockState(blockPos_1, (blockState_1) -> {
            if (blockState_1.getBlock() != ModBlocks.COLOR_DIRT) {
                this.setBlockState(modifiableTestableWorld_1, blockPos_1, ModBlocks.COLOR_DIRT.getDefaultState());
                return true;
            }
            return false;
        });
    }

    protected boolean canReplaceBlock(ModifiableTestableWorld modifiableTestableWorld_1, BlockPos blockPos_1) {
        return modifiableTestableWorld_1.testBlockState(blockPos_1,
                (blockState_1) -> (blockState_1.isAir() || blockState_1.getBlock() == ModBlocks.COLOR_LEAVES)
        );
    }

    public abstract int getVariant(Random random);
    public abstract List<BlockAndPos> getBlocks(BlockPos pos, int variant);

    @Override
    protected boolean generate(Set<BlockPos> set, ModifiableTestableWorld modifiableTestableWorld, Random random, BlockPos position, MutableIntBoundingBox mutableIntBoundingBox) {
        int variant = getVariant(random);
        List<BlockAndPos> blockList = getBlocks(position, variant);

        // Validate blocks and soil
        if (!isDirtOrGrass(modifiableTestableWorld, position.down()))
            return false;

        for (BlockAndPos bp : blockList) {
            BlockPos actual_pos = bp.pos;
            if (actual_pos.getY() >= 0 && actual_pos.getY() < 256) {
                if (bp.required && !canReplaceBlock(modifiableTestableWorld, actual_pos)) {
                    return false;
                }
            } else {
                return false;
            }
        }

        // Replace blocks
        // todo mirror / rotate
        setToDirt(modifiableTestableWorld, position.down());

        for (BlockAndPos bp : blockList) {
            this.setBlockState(set, modifiableTestableWorld, bp.pos, bp.state, mutableIntBoundingBox);
        }

        return true;
    }

//        boolean validPlace = true;
//        if (blockPos.getY() >= 1 && blockPos.getY() + treeHeight + 1 <= 256) { // Height validation
//            int yFor;
//            int xFor;
//            int zFor;
//            for(yFor = blockPos.getY(); yFor <= blockPos.getY() + 1 + treeHeight; ++yFor) { // Height loop
//                int treeDetectionRadius = 1;
//                if (yFor == blockPos.getY()) {
//                    treeDetectionRadius = 0;
//                }
//
//                if (yFor >= blockPos.getY() + 1 + treeHeight - 2) {
//                    treeDetectionRadius = 2;
//                }
//
//                BlockPos.Mutable blockPos$Mutable_1 = new BlockPos.Mutable();
//
//                for(xFor = blockPos.getX() - treeDetectionRadius; xFor <= blockPos.getX() + treeDetectionRadius && validPlace; ++xFor) {
//                    for(zFor = blockPos.getZ() - treeDetectionRadius; zFor <= blockPos.getZ() + treeDetectionRadius && validPlace; ++zFor) {
//                        if (yFor >= 0 && yFor < 256) {
//                            if (!canTreeReplace(modifiableTestableWorld, blockPos$Mutable_1.set(xFor, yFor, zFor))) {
//                                validPlace = false;
//                            }
//                        } else {
//                            validPlace = false;
//                        }
//                    }
//                }
//            }
//
//            if (!validPlace) {
//                return false;
//            } else if (isDirtOrGrass(modifiableTestableWorld, blockPos.down()) && blockPos.getY() < 256 - treeHeight - 1) {
//                this.setToDirt(modifiableTestableWorld, blockPos.down());
//
//                for(yFor = blockPos.getY() - 3 + treeHeight; yFor <= blockPos.getY() + treeHeight; ++yFor) { // loop over leaves y-layers
//                    int int_7 = yFor - (blockPos.getY() + treeHeight);
//                    int int_8 = 1 - int_7 / 2;
//
//                    for(xFor = blockPos.getX() - int_8; xFor <= blockPos.getX() + int_8; ++xFor) {
//                        zFor = xFor - blockPos.getX();
//
//                        for(int int_11 = blockPos.getZ() - int_8; int_11 <= blockPos.getZ() + int_8; ++int_11) {
//                            int int_12 = int_11 - blockPos.getZ();
//                            if (Math.abs(zFor) != int_8 || Math.abs(int_12) != int_8 || random.nextInt(2) != 0 && int_7 != 0) {
//                                BlockPos blockPos_2 = new BlockPos(xFor, yFor, int_11);
//                                if (isAirOrLeaves(modifiableTestableWorld, blockPos_2)) {
//                                    this.setBlockState(set, modifiableTestableWorld, blockPos_2, LEAVES, mutableIntBoundingBox);
//                                }
//                            }
//                        }
//                    }
//                }
//
//                for(yFor = 0; yFor < treeHeight; ++yFor) {
//                    if (isAirOrLeaves(modifiableTestableWorld, blockPos.up(yFor))) {
//                        this.setBlockState(set, modifiableTestableWorld, blockPos.up(yFor), LOG, mutableIntBoundingBox);
//                    }
//                }
//
//                return true;
//            } else {
//                return false;
//            }
//        } else {
//            return false;
//        }
//    }

    public void addBlock(BlockState state, boolean required, BlockPos position, List<BlockAndPos> list) {
        list.add(new BlockAndPos(state, position, required));
    }

    public void addSquare(BlockState state, boolean required, BlockPos center, int halfSize, List<BlockAndPos> list) {
        for (int i=center.getX()-halfSize; i<=center.getX()+halfSize; i++) {
            for (int k=center.getZ()-halfSize; k<=center.getZ()+halfSize; k++) {
                if (i != center.getX() || k != center.getZ()) {
                    list.add(new BlockAndPos(state, new BlockPos(i, center.getY(), k), required));
                }
            }
        }
    }

    public void addDiamond(BlockState state, boolean required, BlockPos center, int radius, List<BlockAndPos> list) {
        for (int i=center.getX()-radius; i<=center.getX()+radius; i++) {
            int leftRadius = radius - Math.abs(center.getX() - i);
            for (int k=center.getZ()-leftRadius; k<=center.getZ()+leftRadius; k++) {
                if (i != center.getX() || k != center.getZ()) {
                    list.add(new BlockAndPos(state, new BlockPos(i, center.getY(), k), required));
                }
            }
        }
    }



    protected class BlockAndPos {
        public BlockState state;
        public BlockPos pos;
        boolean required;

        public BlockAndPos(BlockState state, BlockPos pos, boolean required) {
            this.state = state;
            this.pos = pos;
            this.required = required;
        }
    }
}
