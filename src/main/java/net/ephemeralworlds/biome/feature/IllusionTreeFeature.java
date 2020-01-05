package net.ephemeralworlds.biome.feature;

import com.mojang.datafixers.Dynamic;
import net.ephemeralworlds.block.base.ColorBlock;
import net.ephemeralworlds.registry.ModBlocks;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.helpers.DimensionHelper;
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
        return random.nextInt(2); // 0 or 1
    }

    @Override
    public List<BlockAndPos> getBlocks(BlockPos pos, int variant) {
        List<BlockAndPos> list = new ArrayList<>();

        EnumColor color = DimensionHelper.getColorFromPosition(pos);

        BlockState logState = ColorBlock.getStateWithColor(ModBlocks.COLOR_LOG.getDefaultState(), color);
        BlockState leavesState = ColorBlock.getStateWithColor(ModBlocks.COLOR_LEAVES.getDefaultState(), color);

        int logHeight = variant==0?1:2;
        int leavesHeight = variant==0?2:3;

        BlockPos current = pos;

        while (logHeight > 0) {
            logHeight--;
            addBlock(logState, true, current, list);
            current = current.up();
        }

        addBlock(logState, true, current, list);
        addDiamond(leavesState, false, current, 1, list);
        current = current.up();

        while (leavesHeight > 0) {
            leavesHeight--;
            addBlock(logState, true, current, list);
            addDiamond(leavesState, false, current, 2, list);
            current = current.up();
        }

        addBlock(logState, true, current, list);
        addSquare(leavesState, false, current, 1, list);
        current = current.up();

        addBlock(logState, true, current, list);
        addDiamond(leavesState, false, current, 1, list);
        current = current.up();

        addBlock(leavesState, false, current, list);

        return list;
    }

}
