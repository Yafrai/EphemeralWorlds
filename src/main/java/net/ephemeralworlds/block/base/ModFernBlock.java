package net.ephemeralworlds.block.base;

import net.ephemeralworlds.utils.enums.EnumColorBrightness;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;

public class ModFernBlock extends ColorBlock {

    public ModFernBlock(String uname, Block base) {
        super(uname, FabricBlockSettings.copy(base).ticksRandomly().noCollision().breakInstantly().build(), EnumColorBrightness.ORGANIC);
    }
}
