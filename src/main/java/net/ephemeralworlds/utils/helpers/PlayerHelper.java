package net.ephemeralworlds.utils.helpers;

import com.raphydaphy.crochet.data.PlayerData;
import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
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

    public static EnumColor getPlayerColor(PlayerEntity player) {
        CompoundTag tag = PlayerData.get(player, EphemeralWorlds.MODID);
        return EnumColor.byIndex(tag.getInt("color"));
    }

    public static void setPlayerColor(PlayerEntity player, EnumColor color) {
        CompoundTag tag = PlayerData.get(player, EphemeralWorlds.MODID);
        tag.putInt("color", color.getIndex());
    }
}
