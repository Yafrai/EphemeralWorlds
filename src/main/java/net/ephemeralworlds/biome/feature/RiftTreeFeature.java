package net.ephemeralworlds.biome.feature;

//import com.mojang.datafixers.Dynamic;
//import net.ephemeralworlds.block.base.ColorBlock;
//import net.ephemeralworlds.registry.ModBlocks;
//import net.ephemeralworlds.utils.enums.EnumColor;
//import net.ephemeralworlds.utils.tools.BlockAndPos;
//import net.minecraft.block.BlockState;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.gen.feature.DefaultFeatureConfig;
//import net.minecraft.world.gen.feature.TreeFeatureConfig;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.function.Function;
//
//public class RiftTreeFeature extends ModTreeFeature {
//
//    public RiftTreeFeature(Function<Dynamic<?>, ? extends TreeFeatureConfig> configDeserializer) {
//        super(configDeserializer);
//    }
//
//    @Override
//    public int getVariant(Random random) {
//        return random.nextInt(1); // 0, 1
//    }
//
//    @Override
//    public List<BlockAndPos> getBlocks(BlockPos pos, int variant, EnumColor color, boolean regenerateOnly) {
//        List<BlockAndPos> list = new ArrayList<>();
//
//        BlockState logSourceState = ModBlocks.RIFT_TREE_RESOURCE.getDefaultState();
//        BlockState logState = ModBlocks.RIFT_LOG.getDefaultState();
//        BlockState leavesState = ModBlocks.RIFT_LEAVES.getDefaultState();
//        DIRT = ColorBlock.getStateWithColor(DIRT, color);
//
////        int logHeight = (variant==0 || variant==2)?1:2;
//        int logHeight = 2;
//        boolean youngTree = variant <= 1;
//        BlockPos current = pos;
//
//        while (logHeight > 0) {
//            logHeight--;
//            addBlock(logState, true, current, list);
//            current = current.up();
//        }
//
//        addBlock(leavesState, false, current, list);
//
////        if (youngTree) {
////            addBlock(logState, true, current, list);
////            addCross(leavesState, false, current, 1, false, list);
////            current = current.up();
////
////            addBlock(logState, true, current, list);
////            addDiamond(leavesState, false, current, 1, list);
////            current = current.up();
////
////            addCross(leavesState, false, current, 1, true, list);
////        }
////        else {
////            addBlock(logState, true, current, list);
////            addHash(leavesState, false, current, false, list);
////            current = current.up();
////
////            addBlock(logState, true, current, list);
////            addDiamond(leavesState, false, current, 2, list);
////            current = current.up();
////
////            addHash(leavesState, false, current, true, list);
////            current = current.up();
////
////            addCross(leavesState, false, current, 1, true, list);
////        }
////
////        if (!regenerateOnly) {
////            for (BlockAndPos bp: list) {
////                if (bp.pos.equals(pos)) {
////                    bp.state = logSourceState;
////                }
////            }
////        }
//
//        if (!regenerateOnly) {
//            for (BlockAndPos bp: list) {
//                if (bp.pos.equals(pos)) {
//                    bp.state = logSourceState;
//                }
//            }
//        }
//
//        return list;
//    }
//
//}
