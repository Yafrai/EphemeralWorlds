package net.ephemeralworlds.biome.feature;

//import com.mojang.datafixers.Dynamic;
//import net.ephemeralworlds.block.StructureResourceBlock;
//import net.ephemeralworlds.block.base.ColorBlock;
//import net.ephemeralworlds.registry.ModBlocks;
//import net.ephemeralworlds.utils.enums.EnumColor;
//import net.ephemeralworlds.utils.tools.BlockAndPos;
//import net.minecraft.block.BlockState;
//import net.minecraft.util.math.BlockBox;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.ModifiableTestableWorld;
//import net.minecraft.world.gen.feature.DefaultFeatureConfig;
//import net.minecraft.world.gen.feature.TreeFeatureConfig;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.Set;
//import java.util.function.Function;
//
//public class SpiritTreeFeature extends ModTreeFeature {
//
//    public SpiritTreeFeature(Function<Dynamic<?>, ? extends TreeFeatureConfig> configDeserializer) {
//        super(configDeserializer);
//    }
//
//    @Override
//    protected boolean generate(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> logPositions, Set<BlockPos> leavesPositions, BlockBox blockBox, TreeFeatureConfig config) {
//        return false;
//    }
//
//    @Override
//    public int getVariant(Random random) {
//        return random.nextInt(2); // 0 or 1
//    }
//
//    @Override
//    public List<BlockAndPos> getBlocks(BlockPos pos, int variant, EnumColor color, boolean regenerateOnly) {
//        List<BlockAndPos> list = new ArrayList<>();
//
//        BlockState logSourceState = StructureResourceBlock.getStateWithVariant(ModBlocks.SPIRIT_TREE_RESOURCE.getDefaultState(), variant);
//        BlockState logState = ModBlocks.SPIRIT_LOG.getDefaultState();
//        BlockState leavesState = ModBlocks.SPIRIT_LEAVES.getDefaultState();
//        DIRT = ColorBlock.getStateWithColor(DIRT, color);
//
//        int logHeight = variant==0?1:2;
//        int leavesHeight = variant==0?2:3;
//
//        BlockPos current = pos;
//
//        while (logHeight > 0) {
//            logHeight--;
//            addBlock(logState, true, current, list);
//            current = current.up();
//        }
//
//        addBlock(logState, true, current, list);
//        addDiamond(leavesState, false, current, 1, list);
//        current = current.up();
//
//        while (leavesHeight > 0) {
//            leavesHeight--;
//            addBlock(logState, true, current, list);
//            addDiamond(leavesState, false, current, 2, list);
//            current = current.up();
//        }
//
//        addBlock(logState, true, current, list);
//        addSquare(leavesState, false, current, 1, list);
//        current = current.up();
//
//        addBlock(logState, true, current, list);
//        addDiamond(leavesState, false, current, 1, list);
//        current = current.up();
//
//        addBlock(leavesState, false, current, list);
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
