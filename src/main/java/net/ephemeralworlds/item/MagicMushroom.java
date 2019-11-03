package net.ephemeralworlds.item;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.item.base.ColorItem;
import net.ephemeralworlds.item.base.ModItem;
import net.ephemeralworlds.registry.ModStatusEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;

public class MagicMushroom extends ColorItem {

    public MagicMushroom(String uname) {
        super(new Item.Settings().group(EphemeralWorlds.GROUP).food(new FoodComponent.Builder().alwaysEdible().hunger(2).saturationModifier(0.2F).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 6*20), 1F).statusEffect(new StatusEffectInstance(ModStatusEffects.illusionEffect, 20*30), 1F).build()), uname, false);


    }

}
