package net.ephemeralworlds.item;

import net.ephemeralworlds.block.InkDraw;
import net.ephemeralworlds.block.base.ColorBlock;
import net.ephemeralworlds.block.entity.InkDrawBlockEntity;
import net.ephemeralworlds.block.entity.parts.*;
import net.ephemeralworlds.item.base.ColorTieredIngredientsItem;
import net.ephemeralworlds.registry.ModBlocks;
import net.ephemeralworlds.utils.enums.CircleType;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class Brush extends ColorTieredIngredientsItem {

    public Brush(String uname) {
        super(uname, true);
    }

    public TypedActionResult<ItemStack> use(World world_1, PlayerEntity playerEntity_1, Hand hand_1) {
        if (playerEntity_1.isSneaking()) {
            if (wipeInk(playerEntity_1.getStackInHand(hand_1))) {
                return new TypedActionResult<>(ActionResult.SUCCESS, playerEntity_1.getStackInHand(hand_1));
            }
        }

        return new TypedActionResult<>(ActionResult.FAIL, playerEntity_1.getStackInHand(hand_1));
    }

    public ActionResult useOnBlock(ItemUsageContext itemUsageContext_1) {

        if (itemUsageContext_1.isPlayerSneaking())
            if (attemptToEraseDrawing(itemUsageContext_1.getWorld(), itemUsageContext_1.getPlayer(), itemUsageContext_1.getBlockPos(), itemUsageContext_1.getSide()))
                return ActionResult.SUCCESS;
            else
                return ActionResult.PASS;

        ItemStack brushStack = itemUsageContext_1.getPlayer().getMainHandStack();

        if (getTagInkAmount(brushStack) > 0 && getTagCircleType(brushStack) != null) {
            if (attemptToDrawOnBlock(itemUsageContext_1.getWorld(), itemUsageContext_1.getPlayer(), itemUsageContext_1.getBlockPos(), itemUsageContext_1.getSide())) {
                return ActionResult.SUCCESS;
            }
        }

        if (getTagInkAmount(brushStack) > 0) {
            if (attemptToCollectBlock(itemUsageContext_1.getWorld(), itemUsageContext_1.getPlayer(), itemUsageContext_1.getBlockPos())) {
                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }

    public boolean attemptToDrawOnBlock(World world, PlayerEntity player, BlockPos blockPos, Direction side) {

        // todo check full square face isSideSolidFullSquare
        if (false)
            return false;

        BlockPos drawPos = blockPos.offset(side);

        ItemStack stack = player.getMainHandStack();
        Item itemBrush = stack.getItem();
        if (itemBrush instanceof Brush) {
            Brush brush = (Brush)itemBrush;

            if (brush.getTagInkAmount(stack) > 0) {

                if (world.getBlockState(drawPos).isAir() || world.getBlockState(drawPos).getBlock() instanceof InkDraw) {

                    if (!(world.getBlockState(drawPos).getBlock() instanceof InkDraw))
                        world.setBlockState(drawPos, ModBlocks.INK_DRAW.getDefaultState());

                    BlockEntity entity = world.getBlockEntity(drawPos);
                    if (entity instanceof InkDrawBlockEntity) {

                        if (!((InkDrawBlockEntity)entity).isFaceDrawn(side.getOpposite())) {
                            return drawOnBlock((InkDrawBlockEntity)entity, world.getBlockState(blockPos), side, brush.getTagColor(stack), stack);
                        }
                        else {
                            ISerializablePart part = ((InkDrawBlockEntity)entity).getFaceContents(side.getOpposite());
                            if (part instanceof ACircle) {
                                return ((ACircle)part).activateWithPaint(stack);
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean attemptToCollectBlock(World world, PlayerEntity player, BlockPos blockPos) {

        ItemStack stack = player.getMainHandStack();
        Item itemBrush = stack.getItem();
        if (itemBrush instanceof Brush) {
            Brush brush = (Brush)itemBrush;

            Block block = world.getBlockState(blockPos).getBlock();

            CircleType type = CircleType.fromBlock(block, getTagColor(stack));

            if (type != null) {
                setTagCircleType(stack, type);
                if (!player.isCreative())
                    world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
                return true;
            }
        }

        return false;
    }

    public boolean attemptToEraseDrawing(World world, PlayerEntity player, BlockPos blockPos, Direction side) {
        BlockPos drawingPos  = blockPos.offset(side);

        BlockEntity entity = world.getBlockEntity(drawingPos);

        if (entity instanceof InkDrawBlockEntity) {
            InkDrawBlockEntity inkEntity = (InkDrawBlockEntity)entity;

            boolean hasDrawing = inkEntity.isFaceDrawn(side.getOpposite());

            if (hasDrawing)
                inkEntity.eraseFaceContents(side.getOpposite());

            return hasDrawing;
        }

        return false;
    }

    public boolean drawOnBlock(InkDrawBlockEntity blockEntity, BlockState supportBlockState, Direction side, EnumColor color, ItemStack brushStack) {
        Block supportBlock = supportBlockState.getBlock();

        CircleType type = this.getTagCircleType(brushStack);

        switch (type) {
            case CRAFTING:
                if (color == EnumColor.BLUE) {
                    FusionCircle circle = new FusionCircle(blockEntity, side);
                    blockEntity.setCircle(side.getOpposite(), circle);
                    useInk(brushStack, 1);
                    return true;
                }
                break;

            case FIRE:
                if (color == EnumColor.RED) {
                    FireCircle circle = new FireCircle(blockEntity, side);
                    blockEntity.setCircle(side.getOpposite(), circle);
                    useInk(brushStack, 1);
                    return true;
                }
                break;

            case INK:
                InkCircle circle = new InkCircle(blockEntity, side, color);
                blockEntity.setCircle(side.getOpposite(), circle);
                useInk(brushStack, 1);
                return true;
            default:
        }

        return false;
    }

    public int getTagInkAmount(ItemStack stack) {
        CompoundTag tag = getItemTag(stack);

        if (!tag.containsKey("amount")) {
            tag.putInt("amount", 0);
        }
        return tag.getInt("amount");
    }

    public void setTagAmount(ItemStack stack, int amount) {
        CompoundTag tag = getItemTag(stack);

        tag.putInt("amount", amount);
    }

    public CircleType getTagCircleType(ItemStack stack) {
        CompoundTag tag = getItemTag(stack);

        if (!tag.containsKey("circle")) {
            tag.putInt("circle", -1);
        }
        return CircleType.byIndex(tag.getInt("circle"));
    }

    public void setTagCircleType(ItemStack stack, CircleType type) {
        CompoundTag tag = getItemTag(stack);

        tag.putInt("circle", CircleType.indexOf(type));
    }

    public boolean wipeInk(ItemStack brush) {
        if (getTagInkAmount(brush) > 0) {
            setTagAmount(brush, 0);
            setTagColor(brush, null);
            setTagCircleType(brush, null);
            return true;
        }
        return false;
    }

    public boolean useInk(ItemStack brush, int amount) {
        setTagCircleType(brush, null);
        int current = getTagInkAmount(brush);
        if (current > amount) {
            setTagAmount(brush, current - amount);
            return true;
        }

        return wipeInk(brush);
    }

}
