package net.ephemeralworlds.utils.helpers;

import net.ephemeralworlds.block.base.ColorBlock;
import net.ephemeralworlds.item.base.ColorBlockItem;
import net.ephemeralworlds.item.base.ColorItem;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.enums.EnumColorBrightness;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkRandom;

import java.awt.*;
import java.util.Collections;
import java.util.Random;

public class ColorHelper {

    static OctavePerlinNoiseSampler generator;

    public static EnumColor getBlockColor(World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        if (!(block instanceof ColorBlock))
            return null;

        return ColorBlock.getEnumColor(state);
    }

    public static ItemStack setStackColor(ItemStack stack, EnumColor color) {

        if (stack.getItem() instanceof ColorItem) {
            ColorItem item = (ColorItem)stack.getItem();
            item.setTagColor(stack, color);
        }
        else if ( stack.getItem() instanceof ColorBlockItem) {
            ColorBlockItem item = (ColorBlockItem)stack.getItem();
            item.setTagColor(stack, color);
        }

        return stack;
    }

    public static float getColorRedComponent(int color) {
        return color & 255;
    }

    public static float getColorGreenComponent(int color) {
        return (color / 255) & 255;
    }

    public static float getColorBlueComponent(int color) {
        return color / (255 * 255) & 255;
    }

    public static int getColorVariation(PlayerEntity player, BlockPos blockPos, EnumColorBrightness brightness) {
        EnumColor eColor = PlayerHelper.getPlayerColor(player);
        Color color = new Color(eColor.getColorForBrightness(brightness));
        float variation = 0F;

        try {
            variation = (float) sampleColorVariation(player.world, blockPos.getX(), blockPos.getZ()); // todo add cos(Y) to x pos and z pos
        }
        catch (ArrayIndexOutOfBoundsException exception) {}

        float hueShift = 0.005F * variation * eColor.getHueFactor();
        float brighten = 0F * variation;
        float saturationMultiplier = 1F;

        float[] hsb = java.awt.Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        float h = hsb[0] + hueShift % 1;
        float s = hsb[1] * saturationMultiplier;  // Reduce saturation
        float v = MathHelper.clamp(hsb[2] + brighten, 0F, 1F);

        return new java.awt.Color(java.awt.Color.HSBtoRGB(h, s, v)).getRGB();
    }

    public static double sampleColorVariation(World world, double posX, double posZ) {
        if (generator == null)
            generator = new OctavePerlinNoiseSampler(new ChunkRandom(), Collections.singletonList(4));

//        double d0 = 0.03125D;
//
//        int x_start = 0;
//        int z_start = 0;
//        int x_width = 1000;
//        int z_width = 1000;

//        hueNoise = generator.getRegion(hueNoise, x_start, z_start, x_width, z_width,d0 * 2.0D, d0 * 2.0D, 1.0D);

        return generator.sample(posX * 0.15, 10.0D, posZ * 0.15, 1.0D, 0.0D, true);
    }
}
