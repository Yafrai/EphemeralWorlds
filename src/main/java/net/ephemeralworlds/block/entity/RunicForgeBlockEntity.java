package net.ephemeralworlds.block.entity;

import net.ephemeralworlds.block.InkDraw;
import net.ephemeralworlds.block.entity.base.ModInkTankBlockEntity;
import net.ephemeralworlds.block.entity.base.ModInkTankInventoryBlockEntity;
import net.ephemeralworlds.block.entity.parts.GlyphCircle;
import net.ephemeralworlds.block.entity.parts.ISerializablePart;
import net.ephemeralworlds.item.Glyph;
import net.ephemeralworlds.registry.ModBlockEntities;
import net.ephemeralworlds.registry.ModItems;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.helpers.TagHelper;
import net.ephemeralworlds.utils.tools.RunicSlot;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class RunicForgeBlockEntity extends ModInkTankInventoryBlockEntity {

    public boolean active;
    public List<BlockPos> glyphs;

    public RunicForgeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.INK_JAR_ENTITY, pos, state, 1);
        active = false;
        glyphs = new ArrayList<>();
    }

    @Override
    protected int getTankSize() {
        return getMissingGlyphs().size()>0?10:0;
    }

    @Override
    public boolean canReceiveColor(EnumColor color) {
        if (!super.canReceiveColor(color))
            return false;

        for (RunicSlot slot: getMissingGlyphs()) {
            if (slot.getColor().equals(color))
                return true;
        }

        return false;
    }

    public NbtCompound writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        tag.putBoolean("active", active);

        List<Integer> list = new ArrayList<>();
        for (BlockPos pos: glyphs) {
            list.add(pos.getX());
            list.add(pos.getY());
            list.add(pos.getZ());
        }
        tag.putIntArray("glyphs", list);

        return tag;
    }

    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        active = tag.getBoolean("active");

        int[] array = tag.getIntArray("glyphs");
        int i = 0;
        glyphs = new ArrayList<>();
        while (i<array.length-3) {
            glyphs.add(new BlockPos(array[i], array[i+1], array[i+2]));
            i+= 3;
        }
    }

    @Override
    public void fromClientTag(NbtCompound tag) {
        active = tag.getBoolean("active");

        int[] array = tag.getIntArray("glyphs");
        int i = 0;
        glyphs = new ArrayList<>();
        while (i<array.length-3) {
            glyphs.add(new BlockPos(array[i], array[i+1], array[i+2]));
            i+= 3;
        }
    }

    @Override
    public NbtCompound toClientTag(NbtCompound tag) {
        tag.putBoolean("active", active);

        List<Integer> list = new ArrayList<>();
        for (BlockPos pos: glyphs) {
            list.add(pos.getX());
            list.add(pos.getY());
            list.add(pos.getZ());
        }
        tag.putIntArray("glyphs", list);

        return tag;
    }

    protected boolean isRunicItemInSlot() {
        ItemStack stack = getStack(0);
        return !TagHelper.getRunicSlots(stack).isEmpty();
    }

    protected List<RunicSlot> getRunicSlots() {
        ItemStack stack = getStack(0);
        return TagHelper.getRunicSlots(stack);
    }

    protected List<InkDrawBlockEntity> getGlyphs(World world) {
        List<InkDrawBlockEntity> list = new ArrayList<>();

        for (BlockPos pos:glyphs) {
            BlockEntity entity = world.getBlockEntity(pos);

            if (entity instanceof InkDrawBlockEntity)
                list.add((InkDrawBlockEntity)entity);
        }

        return list;
    }

    protected List<RunicSlot> getMissingGlyphs() {
        List<RunicSlot> slots = getRunicSlots();
        List<InkDrawBlockEntity> glyphs = getGlyphs(world);

        List<RunicSlot> missing = new ArrayList<>(slots);

        for (RunicSlot slot:slots) {
            for (InkDrawBlockEntity glyph:glyphs) {
                ISerializablePart part = glyph.getFaceContents(Direction.DOWN);
                if (part instanceof GlyphCircle) {
                    GlyphCircle glyphCircle = (GlyphCircle) part;


                }
            }

        }

        return missing;
    }

    public void setActiveState(boolean active) {
        if (active != this.active) {

            this.active = active;
            markDirty();

            if (active)
                activate();
            else
                deactivate();
        }
    }

    private void activate() {
        glyphs.clear();
    }

    private void deactivate() {

    }

    public ActionResult interact(PlayerEntity player) {
        if (player.isSneaking()) {
            // Retrieve
            ItemStack stack = getStack(0);

            if (stack.isEmpty())
                return ActionResult.FAIL;
            else {
                setActiveState(false);
                return retrieveStack(player, 0)?ActionResult.SUCCESS:ActionResult.FAIL;
            }
        }

        ItemStack playerStack = player.getMainHandStack();

        if (!playerStack.isEmpty()) {
            if (playerStack.getItem().equals(ModItems.ARCANE_POWDER)) {
                if (isRunicItemInSlot()) {
                    setActiveState(true);
                    playerStack.decrement(1);
                    return ActionResult.SUCCESS;
                }
                else
                    return ActionResult.FAIL;
            }
            else {
                return putStack(player, 0, Hand.MAIN_HAND)?ActionResult.SUCCESS:ActionResult.FAIL;
            }
        }

        return ActionResult.FAIL;
    }
}
