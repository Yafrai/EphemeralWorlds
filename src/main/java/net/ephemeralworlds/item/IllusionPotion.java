package net.ephemeralworlds.item;

import net.ephemeralworlds.item.base.ColorItem;
import net.ephemeralworlds.item.base.ModPotionItem;
import net.ephemeralworlds.registry.ModStatusEffects;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.helpers.PlayerHelper;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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

//        World illusionWorld = EphemeralWorlds.ILLUSION_WORLD;
//        ServerWorld world1 = MinecraftServer.getWorld(ModDimensions.illusion);

//        if (illusionWorld.getDimension() instanceof IllusionDimension) {
            if (stack.getItem() instanceof ColorItem) {
                EnumColor color = ((ColorItem)stack.getItem()).getTagColor(stack);

                PlayerHelper.setPlayerColor(player, color);
            }

//            NbtCompound potionTags = stack.getTag();
//
//            IWorldInstanceManager wim = EphemeralWorlds.WORLD_DATA.get(illusionWorld);
//            InstanceOptions options = new InstanceOptions(player, color);
//            wim.createInstance(options);

//            IWorldInstanceManager wim = EphemeralWorlds.LEVEL_DATA.get(world.getLevelProperties());
//            InstanceOptions options = new InstanceOptions(player, potionTags, (LevelInstanceManager) wim);
//            wim.createInstance(options);
//            IslandGenerator.createIsland(options, player.getServer());

//        }

    }

}
