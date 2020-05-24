package net.ephemeralworlds.block.entity;

import net.ephemeralworlds.block.entity.base.ModBlockEntity;
import net.ephemeralworlds.block.entity.base.ModInkTankBlockEntity;
import net.ephemeralworlds.block.entity.base.ModTickingBlockEntity;
import net.ephemeralworlds.block.entity.parts.*;
import net.ephemeralworlds.registry.ModBlockEntities;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.Direction;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class InkDrawBlockEntity extends ModTickingBlockEntity  {

    public InkDrawBlockEntity() {
        super(ModBlockEntities.INK_DRAW_ENTITY);
    }

    Map<Direction, ISerializablePart> drawing = new HashMap<>();

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

    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);

        drawing.forEach((face, part)-> {
            CompoundTag drawingTag = new CompoundTag();
            drawingTag.putString("name", part.getName());
            part.toTag(drawingTag);
            tag.put(face.asString(), drawingTag);
        });
        return tag;
    }

    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);

        drawing.clear();
        for (Direction face: Direction.values()) {

            if (tag.containsKey(face.asString())) {
                ISerializablePart part = null;

                CompoundTag drawingTag = tag.getCompound(face.asString());

                switch (drawingTag.getString("name")) {
                    case "fusion_circle":
                        part = new FusionCircle(this, face);
                        part.fromTag(drawingTag);
                        break;
                    case "fire_circle":
                        part = new FireCircle(this, face);
                        part.fromTag(drawingTag);
                        break;
                    case "ink_circle":
                        part = new InkCircle(this, face, EnumColor.byIndex(tag.getInt("color")));
                        part.fromTag(drawingTag);
                        break;
                    case "generic_circle":
                        part = new GenericCircle(this, face,null);
                        part.fromTag(drawingTag);
                        break;
                    default:
                        int a = 1 / 0;
                }

                if (part != null)
                    drawing.put(face, part);
            }
        }
    }
}
