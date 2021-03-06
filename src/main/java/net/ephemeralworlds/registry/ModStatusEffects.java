package net.ephemeralworlds.registry;

import net.ephemeralworlds.EphemeralWorlds;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModStatusEffects {

    public static StatusEffect illusionEffect = new ModStatusEffect(StatusEffectType.NEUTRAL, 240);

    public static void registerStatusEffects() {

        int statusCount = 33; // todo

        Registry.register(Registry.STATUS_EFFECT, statusCount, EphemeralWorlds.MODID + ":" + "illusion", illusionEffect);
        statusCount++;
    }


    public static class ModStatusEffect extends StatusEffect {

        protected ModStatusEffect(StatusEffectType statusEffectType, int particleColor) {
            super(statusEffectType, particleColor);
        }

        @Override
        public boolean isInstant() {
            return false;
        }
    }
}
