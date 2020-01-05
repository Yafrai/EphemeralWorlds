package net.ephemeralworlds.biome.surfaceBuilder;

import com.mojang.datafixers.Dynamic;
import net.ephemeralworlds.block.base.ColorBlock;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.helpers.DimensionHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import net.minecraft.world.level.LevelProperties;

import java.util.Random;
import java.util.function.Function;

public class DefaultModSurfaceBuilder extends SurfaceBuilder<TernarySurfaceConfig> {

    public DefaultModSurfaceBuilder(Function<Dynamic<?>, ? extends TernarySurfaceConfig> function_1) {
        super(function_1);
    }

    public void generate(Random random_1, Chunk chunk_1, Biome biome_1, int int_1, int int_2, int int_3, double double_1, BlockState blockState_1, BlockState blockState_2, int int_4, long long_1, TernarySurfaceConfig ternarySurfaceConfig_1) {
        this.generate(random_1, chunk_1, biome_1, int_1, int_2, int_3, double_1, blockState_1, blockState_2, ternarySurfaceConfig_1.getTopMaterial(), ternarySurfaceConfig_1.getUnderMaterial(), ternarySurfaceConfig_1.getUnderwaterMaterial(), int_4);
    }

    protected void generate(Random random, Chunk chunk, Biome biome, int x, int z, int ymax, double amplitude, BlockState blockState_default, BlockState blockState_default_top, BlockState top, BlockState under, BlockState underwater, int seaLevel) {

        EnumColor color = DimensionHelper.getColorFromPosition(new BlockPos(x, 0, z));

        if (top.getBlock() instanceof ColorBlock)
            top = ColorBlock.getStateWithColor(top, color);

        if (under.getBlock() instanceof ColorBlock)
            under = ColorBlock.getStateWithColor(top, color);

        BlockPos.Mutable blockPos$Mutable_1 = new BlockPos.Mutable();
        BlockState effectiveTop = top;
        BlockState effectiveUnder = under;

        int buffer = -1; // Stores previous state
        int reducedAmplitude = (int)(amplitude / 3.0D + 3.0D + random.nextDouble() * 0.25D);
        int xm = x & 15; // x coord in chunck
        int zm = z & 15; // z coord in chunck

        for(int ym = ymax; ym >= 0; --ym) {
            blockPos$Mutable_1.set(xm, ym, zm);
            BlockState blockStatem = chunk.getBlockState(blockPos$Mutable_1);
            if (blockStatem.isAir()) {
                buffer = -1;
            } else if (blockStatem.getBlock() == blockState_default.getBlock()) {
                if (buffer == -1) {
                    // Top block (i.e. under air block)

                    if (reducedAmplitude <= 0) {
                        // negative amplitude
                        effectiveTop = Blocks.AIR.getDefaultState();
                        effectiveUnder = blockState_default;
                    } else if (seaLevel - 4 <= ym && ym <= seaLevel + 1) {
                        // y within 5 blocks interval based on yThreshold
                        effectiveTop = top;
                        effectiveUnder = under;
                    }

                    // Undergoing threshold
                    if (ym < seaLevel && (effectiveTop == null || effectiveTop.isAir())) {
                        if (biome.getTemperature(blockPos$Mutable_1.set(x, ym, z)) < 0.15F) {
                            // Cold temperature -> ice
                            effectiveTop = Blocks.ICE.getDefaultState();
                        } else {
                            // Else -> default top block
                            effectiveTop = blockState_default_top;
                        }

                        // return to block m
                        blockPos$Mutable_1.set(xm, ym, zm);
                    }

                    buffer = reducedAmplitude;
                    if (ym >= seaLevel - 1) {
                        chunk.setBlockState(blockPos$Mutable_1, effectiveTop, false);
                    } else if (ym < seaLevel - 7 - reducedAmplitude) {
                        effectiveTop = Blocks.AIR.getDefaultState();
                        effectiveUnder = blockState_default;
                        chunk.setBlockState(blockPos$Mutable_1, underwater, false);
                    } else {
                        chunk.setBlockState(blockPos$Mutable_1, effectiveUnder, false);
                    }
                } else if (buffer > 0) {
                    // Under surface
                    // Decrease buffer to have buffer = -1 eventually (under top layer)
                    --buffer;
                    chunk.setBlockState(blockPos$Mutable_1, effectiveUnder, false);
//                    if (int_5 == 0 && under2.getBlock() == Blocks.SAND && int_6 > 1) {
//                        int_5 = random.nextInt(4) + Math.max(0, ym - 63);
//                        under2 = under2.getBlock() == Blocks.RED_SAND ? Blocks.RED_SANDSTONE.getDefaultState() : Blocks.SANDSTONE.getDefaultState();
//                    }
                }
                else { // custom edit
                    if (!(effectiveTop == null || effectiveTop.isAir()))
                        chunk.setBlockState(blockPos$Mutable_1, effectiveUnder, false);
                }
            }
        }

    }
}
