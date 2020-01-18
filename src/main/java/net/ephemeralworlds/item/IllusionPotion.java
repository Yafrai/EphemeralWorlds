package net.ephemeralworlds.item;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.dimension.IllusionDimension;
import net.ephemeralworlds.item.base.ColorItem;
import net.ephemeralworlds.item.base.ModItem;
import net.ephemeralworlds.item.base.ModPotionItem;
import net.ephemeralworlds.registry.ModDimensions;
import net.ephemeralworlds.registry.ModStatusEffects;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.helpers.ColorHelper;
import net.ephemeralworlds.utils.instanceHandler.IWorldInstanceManager;
import net.ephemeralworlds.utils.instanceHandler.InstanceOptions;
import net.ephemeralworlds.utils.instanceHandler.LevelInstanceManager;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class IllusionPotion extends ModPotionItem {

    public IllusionPotion(String uname) {
        super(uname);
    }

    @Override
    public List<StatusEffectInstance> getPotionEffects(ItemStack stack) {
        List<StatusEffectInstance> list = new ArrayList<>();

        list.add(new StatusEffectInstance(ModStatusEffects.illusionEffect, 20*60*2));
        list.add(new StatusEffectInstance(StatusEffects.NAUSEA, 6*20));

        return list;
    }

    public void onPotionDrink(World world, ItemStack stack, PlayerEntity player) {

        World illusionWorld = EphemeralWorlds.ILLUSION_WORLD;
//        ServerWorld world1 = MinecraftServer.getWorld(ModDimensions.illusion);

        if (illusionWorld.getDimension() instanceof IllusionDimension) {
//            if (stack.getItem() instanceof ColorItem) {
//                color = ((ColorItem)stack.getItem()).getTagColor(stack);
//            }

            CompoundTag potionTags = stack.getTag();
//
//            IWorldInstanceManager wim = EphemeralWorlds.WORLD_DATA.get(illusionWorld);
//            InstanceOptions options = new InstanceOptions(player, color);
//            wim.createInstance(options);

            IWorldInstanceManager wim = EphemeralWorlds.LEVEL_DATA.get(world.getLevelProperties());
            InstanceOptions options = new InstanceOptions(player, potionTags, (LevelInstanceManager) wim);
            wim.createInstance(options);

        }

    }

}
