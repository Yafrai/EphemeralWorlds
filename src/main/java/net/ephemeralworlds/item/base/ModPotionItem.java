package net.ephemeralworlds.item.base;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.advancement.criterion.Criterions;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

public abstract class ModPotionItem extends ColorItem {
    public ModPotionItem(String uname) {
        super(uname, false);
    }

    public abstract List<StatusEffectInstance> getPotionEffects(ItemStack stack);
    public abstract void onPotionDrink(World world, ItemStack stack, PlayerEntity player);

    @Environment(EnvType.CLIENT)
    public ItemStack getStackForRender() {
        return PotionUtil.setPotion(super.getStackForRender(), Potions.WATER);
    }

    public ItemStack finishUsing(ItemStack itemStack_1, World world_1, LivingEntity livingEntity_1) {
        PlayerEntity playerEntity_1 = livingEntity_1 instanceof PlayerEntity ? (PlayerEntity)livingEntity_1 : null;
        if (playerEntity_1 == null || !playerEntity_1.abilities.creativeMode) {
            itemStack_1.decrement(1);
        }

        if (playerEntity_1 instanceof ServerPlayerEntity) {
            Criterions.CONSUME_ITEM.handle((ServerPlayerEntity)playerEntity_1, itemStack_1);
        }

        if (!world_1.isClient) {
            List<StatusEffectInstance> list_1 = getPotionEffects(itemStack_1);
            Iterator var6 = list_1.iterator();

            while(var6.hasNext()) {
                StatusEffectInstance statusEffectInstance_1 = (StatusEffectInstance)var6.next();
                if (statusEffectInstance_1.getEffectType().isInstant()) {
                    statusEffectInstance_1.getEffectType().applyInstantEffect(playerEntity_1, playerEntity_1, livingEntity_1, statusEffectInstance_1.getAmplifier(), 1.0D);
                } else {
                    livingEntity_1.addPotionEffect(new StatusEffectInstance(statusEffectInstance_1));
                }
            }

            onPotionDrink(world_1, itemStack_1, playerEntity_1);
        }

        if (playerEntity_1 != null) {
            playerEntity_1.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        if (playerEntity_1 == null || !playerEntity_1.abilities.creativeMode) {
            if (itemStack_1.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            if (playerEntity_1 != null) {
                playerEntity_1.inventory.insertStack(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        return itemStack_1;
    }

    public int getMaxUseTime(ItemStack itemStack_1) {
        return 32;
    }

    public UseAction getUseAction(ItemStack itemStack_1) {
        return UseAction.DRINK;
    }

    public TypedActionResult<ItemStack> use(World world_1, PlayerEntity playerEntity_1, Hand hand_1) {
        TypedActionResult<ItemStack> sup = super.use(world_1, playerEntity_1, hand_1);

        if (sup.getResult() == ActionResult.SUCCESS)
            return sup;

        playerEntity_1.setCurrentHand(hand_1);
        return new TypedActionResult(ActionResult.SUCCESS, playerEntity_1.getStackInHand(hand_1));
    }

    public String getTranslationKey(ItemStack itemStack_1) {
        return PotionUtil.getPotion(itemStack_1).getName(this.getTranslationKey() + ".effect.");
    }

    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack itemStack_1, World world_1, List<Text> list_1, TooltipContext tooltipContext_1) {
        PotionUtil.buildTooltip(itemStack_1, list_1, 1.0F);
    }

    @Environment(EnvType.CLIENT)
    public boolean hasEnchantmentGlint(ItemStack itemStack_1) {
        return false;
    }

//    public void appendStacks(ItemGroup itemGroup_1, DefaultedList<ItemStack> defaultedList_1) {
//        if (this.isIn(itemGroup_1)) {
//            Iterator var3 = Registry.POTION.iterator();
//
//            while(var3.hasNext()) {
//                Potion potion_1 = (Potion)var3.next();
//                if (potion_1 != Potions.EMPTY) {
//                    defaultedList_1.add(PotionUtil.setPotion(new ItemStack(this), potion_1));
//                }
//            }
//        }
//
//    }
}
