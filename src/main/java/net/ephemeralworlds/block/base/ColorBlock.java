package net.ephemeralworlds.block.base;

import com.google.common.collect.Lists;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ExtendedBlockView;


import java.util.List;

public class ColorBlock extends ModBlock implements BlockColorProvider {

    static final EnumProperty<EnumColor> color = EnumProperty.of("color", EnumColor.class);

    public ColorBlock(String uname, Block base) {
        super(uname, base);

        setDefaultState(getDefaultState().with(color, EnumColor.BLUE));
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> map) {
        super.appendProperties(map);
        map.add(color);
    }

    public int getColor(BlockState blockState, ExtendedBlockView blockView, BlockPos blockPos, int tintIndex) {
        EnumColor color = getEnumColor(blockState);
        if (tintIndex == 0)
            return color.getMineralColor();
        else if (tintIndex == 1)
            return color.getVegetalColor();

        return color.getColorValue();
    }

    public static EnumColor getEnumColor(BlockState state) {
        return state.get(color);
    }
    public static BlockState getStateWithColor(BlockState state, EnumColor recolor) {
        return state.with(color, recolor);
    }

    public List<ItemStack> defaultDropList(BlockState state) {
        List<ItemStack> list_1 = Lists.newArrayList();

        ItemStack stack = new ItemStack(this);
        CompoundTag stateTag = new CompoundTag();
        stateTag.putString("color", state.get(color).asString());
        CompoundTag tag = new CompoundTag();
        tag.put("BlockStateTag", stateTag);
        stack.setTag(tag);
        list_1.add(stack);

        return list_1;
    }



}
