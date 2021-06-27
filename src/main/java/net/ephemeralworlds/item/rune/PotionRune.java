package net.ephemeralworlds.item.rune;

import net.ephemeralworlds.item.base.Rune;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;


public class PotionRune extends Rune {

    StatusEffect effect;
    int baseDuration;

    public PotionRune(String uname, StatusEffect effect, int baseDuration) {
        super(uname);
        this.effect = effect;
        this.baseDuration = baseDuration;
    }

    @Override
    public boolean applyToTarget(LivingEntity target) {
        if (effect == null)
            return false;

        StatusEffectInstance instance = new StatusEffectInstance(effect, baseDuration, 0, false, false, true);
        target.addStatusEffect(instance);

        return true;
    }

    @Override
    public int[] getCost() {
        return new int[]{0, 0, 0};
    }
}
