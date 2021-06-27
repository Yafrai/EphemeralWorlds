package net.ephemeralworlds.block.base;

import com.google.common.collect.Lists;
import net.ephemeralworlds.registry.ModDimensions;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.enums.EnumColorBrightness;
import net.ephemeralworlds.utils.helpers.ColorHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;


public class ColorBlock extends ModBlock implements BlockColorProvider {

    static final EnumProperty<EnumColor> color = EnumProperty.of("color", EnumColor.class);
    private final EnumColorBrightness brightness;

    public ColorBlock(String uname, Block base, EnumColorBrightness brightness) {
        this(uname, Settings.copy(base), brightness);
    }

    public ColorBlock(String uname, Settings settings, EnumColorBrightness brightness) {
        super(uname, settings);
        this.brightness = brightness;
        setDefaultState(getDefaultState().with(color, EnumColor.BLUE));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> map) {
        super.appendProperties(map);
        map.add(color);
    }

    public int getColor(BlockState blockState, @Nullable BlockRenderView blockView, @Nullable BlockPos blockPos, int tintIndex) {
        EnumColorBrightness brightness = getBrightness(tintIndex);

        if (brightness != null) {
            EnumColor color = getEnumColor(blockState);

            if (blockView != null) {
                // Spirit world color override with player color
                ClientWorld world = MinecraftClient.getInstance().world;
                PlayerEntity player = MinecraftClient.getInstance().player;

//                if (blockPos != null && world != null && world.dimension.getType() == ModDimensions.spiritWorld) {
//                    return ColorHelper.getColorVariation(player, blockPos, brightness);
//                }
            }

            return color.getColorForBrightness(brightness);
        }
        else
            return new Color(255, 255, 255).getRGB();
    }

    protected EnumColorBrightness getBrightness(int tintIndex) {
        if (tintIndex == 0)
            return brightness;
        else
            return null;
    }

    public static EnumColor getEnumColor(BlockState state) {
        return state.get(color);
    }

    public static BlockState getStateWithColor(BlockState state, EnumColor recolor) {
        return state.with(color, recolor);
    }

    public static BlockState getStateWithColor(Block block, EnumColor recolor) {
        BlockState state = block.getDefaultState();
        return ColorBlock.getStateWithColor(state, recolor);
    }

    public List<ItemStack> defaultDropList(BlockState state) {
        List<ItemStack> list_1 = Lists.newArrayList();

        ItemStack stack = new ItemStack(this);
        NbtCompound stateTag = new NbtCompound();
        stateTag.putString("color", state.get(color).asString());
        NbtCompound tag = new NbtCompound();
        tag.put("BlockStateTag", stateTag);
        stack.setTag(tag);
        list_1.add(stack);

        return list_1;
    }
}
