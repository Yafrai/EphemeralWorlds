package net.ephemeralworlds.item.base;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.registry.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public class ModItem extends Item {

    public ModItem(String uname) {
        this(new Item.Settings().group(EphemeralWorlds.GROUP), uname);
    }

    public ModItem(Settings settings, String uname) {
        super(settings.group(EphemeralWorlds.GROUP));

        ModItems.addItemForRegistration(uname, this);
    }

    public CompoundTag getItemTag(ItemStack stack) {
        CompoundTag tag = stack.getTag();

        if (tag == null) {
            tag = new CompoundTag();
            stack.setTag(tag);
        }
        return tag;
    }
}
