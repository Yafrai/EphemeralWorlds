package net.ephemeralworlds.block.entity.parts;


import net.minecraft.nbt.NbtCompound;

public interface ISerializablePart {

    String getName();
    NbtCompound writeTag(NbtCompound tag);
    void  readTag(NbtCompound tag);
    void drop();
    void tick();
}
