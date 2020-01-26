package net.ephemeralworlds.item.base;

import net.ephemeralworlds.block.base.ColorBlock;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.minecraft.advancement.criterion.Criterions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Iterator;

public class ColorBlockItem extends BlockItem {

    public ColorBlockItem(Block block_1, Settings item$Settings_1) {
        super(block_1, item$Settings_1);
    }

    public int getColor(ItemStack stack, int tintIndex) {
        try {
            ColorBlockItem itemBlock = (ColorBlockItem) stack.getItem();
            ColorBlock colorBlock = (ColorBlock) itemBlock.getBlock();

            BlockState state = ColorBlock.getStateWithColor(colorBlock.getDefaultState(), getTagColor(stack));
            return colorBlock.getColor(state, null, null, tintIndex);
        }
        catch (Exception e) {
            return getTagColor(stack).getColorValue();
        }
    }

    public CompoundTag getItemTag(ItemStack stack) {
        CompoundTag tag = stack.getTag();

        if (tag == null) {
            tag = new CompoundTag();
            stack.setTag(tag);
        }
        return tag;
    }

    public EnumColor getTagColor(ItemStack stack) {
        CompoundTag tag = getItemTag(stack);

        if (!tag.containsKey("BlockStateTag")) {
            setTagColor(stack, EnumColor.BLUE);
        }
        return EnumColor.byName(tag.getCompound("BlockStateTag").getString("color"));
    }

    public void setTagColor(ItemStack stack, EnumColor color) {
        CompoundTag tag = getItemTag(stack);
        CompoundTag stateTag = new CompoundTag();
        stateTag.putString("color", color.getName());
        tag.put("BlockStateTag", stateTag);
    }

    public ActionResult place(ItemPlacementContext itemPlacementContext_1) {
        if (!itemPlacementContext_1.canPlace()) {
            return ActionResult.FAIL;
        } else {
            ItemPlacementContext itemPlacementContext_2 = this.getPlacementContext(itemPlacementContext_1);
            if (itemPlacementContext_2 == null) {
                return ActionResult.FAIL;
            } else {
                BlockState blockState_1 = this.getPlacementState(itemPlacementContext_2);
                if (blockState_1 == null) {
                    return ActionResult.FAIL;
                } else if (!this.place(itemPlacementContext_2, blockState_1)) {
                    return ActionResult.FAIL;
                } else {
                    BlockPos blockPos_1 = itemPlacementContext_2.getBlockPos();
                    World world_1 = itemPlacementContext_2.getWorld();
                    PlayerEntity playerEntity_1 = itemPlacementContext_2.getPlayer();
                    ItemStack itemStack_1 = itemPlacementContext_2.getStack();
                    BlockState blockState_2 = world_1.getBlockState(blockPos_1);
                    Block block_1 = blockState_2.getBlock();
                    if (block_1 == blockState_1.getBlock()) {
                        blockState_2 = this.placeFromTag(blockPos_1, world_1, itemStack_1, blockState_2);
                        this.postPlacement(blockPos_1, world_1, playerEntity_1, itemStack_1, blockState_2);
                        block_1.onPlaced(world_1, blockPos_1, blockState_2, playerEntity_1, itemStack_1);
                        if (playerEntity_1 instanceof ServerPlayerEntity) {
                            Criterions.PLACED_BLOCK.handle((ServerPlayerEntity)playerEntity_1, blockPos_1, itemStack_1);
                        }
                    }

                    BlockSoundGroup blockSoundGroup_1 = blockState_2.getSoundGroup();
                    world_1.playSound(playerEntity_1, blockPos_1, this.getPlaceSound(blockState_2), SoundCategory.BLOCKS, (blockSoundGroup_1.getVolume() + 1.0F) / 2.0F, blockSoundGroup_1.getPitch() * 0.8F);
                    itemStack_1.decrement(1);
                    return ActionResult.SUCCESS;
                }
            }
        }
    }

    private BlockState placeFromTag(BlockPos blockPos_1, World world_1, ItemStack itemStack_1, BlockState blockState_1) {
        BlockState blockState_2 = blockState_1;
        CompoundTag compoundTag_1 = itemStack_1.getTag();
        if (compoundTag_1 != null) {
            CompoundTag compoundTag_2 = compoundTag_1.getCompound("BlockStateTag");
            StateFactory<Block, BlockState> stateFactory_1 = blockState_1.getBlock().getStateFactory();
            Iterator var9 = compoundTag_2.getKeys().iterator();

            while(var9.hasNext()) {
                String string_1 = (String)var9.next();
                Property<?> property_1 = stateFactory_1.getProperty(string_1);
                if (property_1 != null) {
                    String string_2 = compoundTag_2.getTag(string_1).asString();
                    blockState_2 = with(blockState_2, property_1, string_2);
                }
            }
        }

        if (blockState_2 != blockState_1) {
            world_1.setBlockState(blockPos_1, blockState_2, 2);
        }

        return blockState_2;
    }

    private static <T extends Comparable<T>> BlockState with(BlockState blockState_1, Property<T> property_1, String string_1) {
        return (BlockState)property_1.getValue(string_1).map((comparable_1) -> {
            return (BlockState)blockState_1.with(property_1, comparable_1);
        }).orElse(blockState_1);
//        Comparable<T> comparable = property_1.getValues()[1];
//        BlockState state = blockState_1.with(property_1, comparable)
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getStackInHand(hand);
        if (playerEntity.isCreative() && playerEntity.isSneaking()) {
            playerEntity.setCurrentHand(hand);
            cycleThroughItemColor(itemStack);
            return new TypedActionResult(ActionResult.SUCCESS, itemStack);
        } else {
            return new TypedActionResult(ActionResult.FAIL, itemStack);
        }
    }

    public ItemStack cycleThroughItemColor(ItemStack stack) {
        EnumColor color = getTagColor(stack);
        int i = color.getIndex();
        i++;
        if (i >= EnumColor.values().length)
            i = 0;

        setTagColor(stack, EnumColor.byIndex(i));
        return stack;
    }
}
