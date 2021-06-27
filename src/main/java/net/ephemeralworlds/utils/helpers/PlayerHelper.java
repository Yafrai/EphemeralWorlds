package net.ephemeralworlds.utils.helpers;

import net.ephemeralworlds.utils.enums.EnumColor;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class PlayerHelper {

    public static PlayerEntity getPlayer(World world, String name) {
        if (world == null)
            return null;

        for (int i = 0; i < world.getPlayers().size(); ++i) {
            PlayerEntity player = world.getPlayers().get(i);

            if (player.getName().asString().equals(name))
                return player;
        }
        return null;
    }

    public static void givePlayerOrDrop(PlayerEntity player, ItemStack stack) {
        if (!player.giveItemStack(stack)) {
            ItemEntity drop = new ItemEntity(player.world, player.getPos().getX(), player.getPos().getY(), player.getPos().getZ(), stack);
            drop.setVelocity(0, 0, 0);
            player.world.spawnEntity(drop);
        }
    }

    public static NbtCompound getPlayerData(PlayerEntity player) { // todo
        return new NbtCompound();
    }

    public static void setPlayerData(PlayerEntity player, EnumColor color) { // todo
//        NbtCompound tag = PlayerData.get(player, EphemeralWorlds.MODID);
//        tag.putInt("color", color.getIndex());
    }

    public static EnumColor getPlayerColor(PlayerEntity player) {
        NbtCompound tag = PlayerHelper.getPlayerData(player);
        if (tag.contains("color"))
            return EnumColor.byIndex(tag.getInt("color"));
        else
            return EnumColor.byIndex(0);
    }

    public static void setPlayerColor(PlayerEntity player, EnumColor color) {
        NbtCompound tag = PlayerHelper.getPlayerData(player);
        tag.putInt("color", color.getIndex());
    }
}
