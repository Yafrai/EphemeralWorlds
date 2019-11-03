package net.ephemeralworlds.utils.helpers;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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
}
