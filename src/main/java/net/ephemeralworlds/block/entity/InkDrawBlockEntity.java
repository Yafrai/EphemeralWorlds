package net.ephemeralworlds.block.entity;

import net.ephemeralworlds.block.entity.base.ModTickingBlockEntity;
import net.ephemeralworlds.block.entity.parts.*;
import net.ephemeralworlds.registry.ModBlockEntities;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;


public class InkDrawBlockEntity extends ModTickingBlockEntity  {


    Map<Direction, ISerializablePart> drawing = new HashMap<>();

    public InkDrawBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.INK_DRAW_ENTITY, pos, state);
    }

    public boolean isFaceDrawn(Direction side) {
        return drawing.containsKey(side);
    }

    public void setFaceContents(Direction face, ISerializablePart part) {
        drawing.put(face, part);
        saveAndNotify();
    }

    public ISerializablePart getFaceContents(Direction face) {
        if (drawing.containsKey(face))
            return drawing.get(face);
        return null;
    }

    public void eraseFaceContents(ISerializablePart reference) {
        eraseFaceContents(getFace(reference));
    }

    public void eraseFaceContents(Direction face) {
        if (drawing.containsKey(face)) {
            drawing.get(face).drop();
            drawing.remove(face);
            saveAndNotify();
        }

        if (drawing.isEmpty()) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
    }

    public Direction getFace(ISerializablePart reference) {

        for (Direction face: Direction.values()) {
            if (drawing.containsKey(face)) {
                if (drawing.get(face) == reference)
                    return face;
            }
        }
        return null;
    }

    public void setCircle(Direction face, ACircle circle) {
        setFaceContents(face, circle);
    }

    @Override
    protected int getTicks() {
        return 10;
    }

    @Override
    protected void onTick() {
        for (Direction face: Direction.values()) {
            if (drawing.containsKey(face)) {
                getFaceContents(face).tick();
            }
        }
    }

    @Override
    public NbtCompound writeNbt(NbtCompound tag) {
        super.writeNbt(tag);

        drawing.forEach((face, part)-> {
            NbtCompound drawingTag = new NbtCompound();
            drawingTag.putString("name", part.getName());
            part.writeTag(drawingTag);
            tag.put(face.asString(), drawingTag);
        });
        return tag;
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);

        drawing.clear();
        for (Direction face: Direction.values()) {

            if (tag.contains(face.asString())) {
                ISerializablePart part = null;

                NbtCompound drawingTag = tag.getCompound(face.asString());

                switch (drawingTag.getString("name")) {
                    case "base_circle":
                        part = new BaseCircle(this, face, EnumColor.byIndex(tag.getInt("color")));
                        part.readTag(drawingTag);
                        break;
                    case "fusion_circle":
                        part = new FusionCircle(this, face);
                        part.readTag(drawingTag);
                        break;
                    case "fire_circle":
                        part = new FireCircle(this, face);
                        part.readTag(drawingTag);
                        break;
                    case "ink_circle":
                        part = new InkCircle(this, face, EnumColor.byIndex(tag.getInt("color")));
                        part.readTag(drawingTag);
                        break;
                    case "seal_circle":
                        part = new SealCircle(this, face, EnumColor.byIndex(tag.getInt("color")));
                        part.readTag(drawingTag);
                        break;
                }

                if (part != null)
                    drawing.put(face, part);
            }
        }
    }
}
