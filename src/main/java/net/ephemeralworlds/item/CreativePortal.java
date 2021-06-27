package net.ephemeralworlds.item;

import net.ephemeralworlds.item.base.ModItem;
import net.ephemeralworlds.registry.ModStatusEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class CreativePortal extends ModItem {

    public CreativePortal(String uname) {
        super(uname);
    }

    public TypedActionResult<ItemStack> use(World world_1, PlayerEntity playerEntity_1, Hand hand_1) {
        if (!world_1.isClient) {

//            if (world_1.getDimension() instanceof SpiritDimension) {
//                playerEntity_1.removeStatusEffect(ModStatusEffects.illusionEffect);
//            }
//            else {
//                playerEntity_1.addPotionEffect(new StatusEffectInstance(ModStatusEffects.illusionEffect, 60*60*20));
//            }

        }

        return new TypedActionResult<>(ActionResult.SUCCESS, playerEntity_1.getStackInHand(hand_1));
    }
}
