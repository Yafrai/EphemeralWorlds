package net.ephemeralworlds.block.entity.parts;

import net.minecraft.nbt.CompoundTag;

public interface ISerializablePart {

    String getName();
    CompoundTag toTag(CompoundTag tag);
    void  fromTag(CompoundTag tag);
    void drop();
    void tick();
}
