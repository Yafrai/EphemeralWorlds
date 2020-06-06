package net.ephemeralworlds.block.base;

import com.google.common.collect.Lists;
import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.enums.EnumColorBrightness;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ExtendedBlockView;

import java.awt.*;
import java.util.List;

public class ModFernBlock extends ColorBlock {

    public ModFernBlock(String uname, Block base) {
        super(uname, FabricBlockSettings.copy(base).ticksRandomly().noCollision().breakInstantly().build(), EnumColorBrightness.ORGANIC);
    }
}
