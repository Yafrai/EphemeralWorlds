package net.ephemeralworlds.item.rune;

import net.ephemeralworlds.item.base.Rune;
import net.minecraft.entity.LivingEntity;

public class IgnitionRune extends Rune {

    public IgnitionRune(String uname) {
        super(uname);
    }

    @Override
    public boolean applyToTarget(LivingEntity entity) {
        entity.setFireTicks(10);
        return true;
    }

    @Override
    public int[] getCost() {
        return new int[]{0, 0, 0};
    }
}
