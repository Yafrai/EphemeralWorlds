package net.ephemeralworlds.block;

import net.ephemeralworlds.block.base.ModBlock;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class MagicMushroomBlock extends ModBlock {

    public MagicMushroomBlock(String uname) {
        super(uname, Blocks.BROWN_MUSHROOM);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }
}
