package net.ephemeralworlds.utils.helpers;

import net.ephemeralworlds.item.base.IRunicWeapon;
import net.ephemeralworlds.utils.tools.RunicSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

import java.util.ArrayList;
import java.util.List;

public class TagHelper {

    public static List<RunicSlot> getRunicSlots(ItemStack stack) {
        List<RunicSlot> list = new ArrayList<>();

        // Slots from runic item
        if (stack.getItem() instanceof IRunicWeapon) {
            list.addAll(((IRunicWeapon)stack.getItem()).getRunicSlots());
        }

        // Slots from enchants
        if (stack.hasTag()) {
            NbtCompound tag = stack.getTag();

            // todo
        }

        return list;
    }
}
