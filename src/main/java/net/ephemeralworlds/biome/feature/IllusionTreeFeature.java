package net.ephemeralworlds.biome.feature;

import com.mojang.datafixers.Dynamic;
import net.ephemeralworlds.block.base.ColorBlock;
import net.ephemeralworlds.registry.ModBlocks;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.helpers.DimensionHelper;
import net.ephemeralworlds.utils.tools.BlockAndPos;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class IllusionTreeFeature extends ModTreeFeature {

    public IllusionTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> configDeserializer, boolean emitNeighborBlockUpdates, boolean alwaysTall) {
        super(configDeserializer, emitNeighborBlockUpdates, alwaysTall);
    }

    @Override
    public int getVariant(Random random) {
        return random.nextInt(4); // 0, 1, 2 or 3
    }

    @Override
    public List<BlockAndPos> getBlocks(BlockPos pos, int variant, EnumColor color) {
        List<BlockAndPos> list = new ArrayList<>();

        BlockState logState = ColorBlock.getStateWithColor(ModBlocks.COLOR_LOG.getDefaultState(), color);
        BlockState leavesState = ColorBlock.getStateWithColor(ModBlocks.COLOR_LEAVES.getDefaultState(), color);
        DIRT = ColorBlock.getStateWithColor(DIRT, color);

        int logHeight = (variant==0 || variant==2)?1:2;
        boolean youngTree = variant <= 1;
        BlockPos current = pos;

        while (logHeight > 0) {
            logHeight--;
            addBlock(logState, true, current, list);
            current = current.up();
        }

        if (youngTree) {
            addBlock(logState, true, current, list);
            addCross(leavesState, false, current, 1, false, list);
            current = current.up();

            addBlock(logState, true, current, list);
            addDiamond(leavesState, false, current, 1, list);
            current = current.up();

            addCross(leavesState, false, current, 1, true, list);
        }
        else {
            addBlock(logState, true, current, list);
            addHash(leavesState, false, current, false, list);
            current = current.up();

            addBlock(logState, true, current, list);
            addDiamond(leavesState, false, current, 2, list);
            current = current.up();

            addHash(leavesState, false, current, true, list);
            current = current.up();

            addCross(leavesState, false, current, 1, true, list);
        }

        return list;
    }

}
