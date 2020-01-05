package net.ephemeralworlds.utils.instanceHandler;

import net.ephemeralworlds.biome.base.ModBiome;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.helpers.PlayerHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class InstanceOptions {

    public static int INSTANCE_WIDTH = 100*16; // Instance width in which the player is allowed to stand
    public static int INSTANCE_MARGIN = 16*16; // Margin that separates 2 instances

    PlayerEntity owner;
    boolean hasExpired;
    EnumColor color;
    BlockPos spawn;

    ModBiome forcedBiome = null;

    public InstanceOptions(PlayerEntity owner, EnumColor color) {
        this.owner = owner;
        this.color = color;
        hasExpired = false;
    }

    public InstanceOptions(World world, CompoundTag tag, String suffix) {

        this.owner = PlayerHelper.getPlayer(world, tag.getString("owner_"+suffix));
        this.color = EnumColor.byIndex(tag.getInt("color_"+suffix));
        this.setSpawn(new BlockPos(tag.getInt("x_"+suffix), tag.getInt("y_"+suffix), tag.getInt("z_"+suffix)));
        // Setters

    }

    public void initIndexData(int index) {
        setSpawn(new BlockPos(index * (INSTANCE_WIDTH + INSTANCE_MARGIN) + INSTANCE_WIDTH / 2, 60, INSTANCE_WIDTH / 2));
    }

    public void setBiome(ModBiome biome) {
        this.forcedBiome = biome;
    }

    public void setSpawn(BlockPos pos) {
        this.spawn = pos;
    }
    public int getSpawnX() {
        return spawn.getX();
    }
    public int getSpawnY() {
        return spawn.getY();
    }
    public int getSpawnZ() {
        return spawn.getZ();
    }

    boolean hasExpired() {
        return hasExpired;
    }



    public CompoundTag toTag(CompoundTag tag, String suffix) {

        tag.putString("owner_"+suffix, owner!=null?owner.getName().asString():"");
        tag.putBoolean("expired_"+suffix, hasExpired);
        tag.putInt("color_"+suffix, color.getIndex());
        tag.putInt("x_"+suffix, spawn.getX());
        tag.putInt("y_"+suffix, spawn.getY());
        tag.putInt("z_"+suffix, spawn.getZ());

        return tag;
    }
}
