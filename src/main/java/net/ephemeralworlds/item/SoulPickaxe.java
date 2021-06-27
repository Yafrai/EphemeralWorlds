package net.ephemeralworlds.item;

import net.ephemeralworlds.item.base.IRunicWeapon;
import net.ephemeralworlds.item.base.SoulTool;
import net.ephemeralworlds.utils.enums.EnumResourceType;
import net.ephemeralworlds.utils.tools.RunicSlot;
import net.ephemeralworlds.utils.tools.RunicSlots;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SoulPickaxe extends SoulTool implements IRunicWeapon {
    public SoulPickaxe(String uname) {
        super(uname, EnumResourceType.STONE, EnumResourceType.METAL, EnumResourceType.PRECIOUS_STONE);
    }

    @Override
    public Collection<RunicSlot> getRunicSlots() {

        List<RunicSlot> list = new ArrayList<RunicSlot>();

        list.add(RunicSlots.onHitSlot);
        list.add(RunicSlots.onCritSlot);

        return list;
    }
}
