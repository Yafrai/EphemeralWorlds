package net.ephemeralworlds.utils.instanceHandler;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.biome.base.ModBiome;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.helpers.PlayerHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;


public class InstanceOptions {
    public static int BASE_RADIUS = 16;
    public static int BASE_MIN_Y = BASE_RADIUS;
    public static int BASE_MAX_Y = 2*BASE_RADIUS;
//    public static int INSTANCE_WIDTH = 100*16; // Instance width in which the player is allowed to stand
//    public static int INSTANCE_MARGIN = 16*16; // Margin that separates 2 instances

    PlayerEntity owner;
    boolean hasExpired;
    EnumColor color;
    BlockPos spawn;
    BlockPos center;
    int radius;
    long expiration;
    Random random;
    int islandLevel;

    ModBiome forcedBiome = null;

    // Create a new island
    public InstanceOptions(PlayerEntity owner, CompoundTag potionTag, LevelInstanceManager manager) {
        this.owner = owner;
        hasExpired = false;

        random = new Random();

        this.color = getColor(potionTag);
        this.radius = getRadiusFromPotion(potionTag);
        this.center = findIslandPlace(manager);
        this.spawn = findSpawn(manager);
        this.islandLevel = getLevelFromPotion(potionTag);

//        this.expiration = 3 * 20 * 60;
        this.expiration = 0;
    }

    public EnumColor getColor(CompoundTag potionTag) {
        if (potionTag.containsKey("color")) {
            return EnumColor.byIndex(potionTag.getInt("color"));
        }

        return EnumColor.byIndex(0);
    }

    protected int getRadiusFromPotion(CompoundTag potionTag) {

        float multiplier = 0;

        // todo range multiplier
        return Math.round(BASE_RADIUS * (1 + multiplier));
    }

    protected int getLevelFromPotion(CompoundTag potionTag) {
        return 1;
    }

    protected BlockPos findIslandPlace(LevelInstanceManager manager) {
        int y = Math.round((BASE_MAX_Y - BASE_MIN_Y) * random.nextFloat() + BASE_MIN_Y);

        if (manager.testIslandPlace(0, y, 0, radius)) {
            return new BlockPos(0, y, 0);
        }

        int r = BASE_RADIUS;
        int x, z;

        while (true) {

//            for (double a=0; a<2*Math.PI; a+= Math.max(.5 * Math.PI * BASE_RADIUS / r, 10)) {
            for (double a=0; a<2*Math.PI; a++) {

                x = (int)Math.round(r * Math.cos(a));
                z = (int)Math.round(r * Math.sin(a));

                if (manager.testIslandPlace(x, y, z, radius)) {
                    return new BlockPos(x, y, z);
                }
            }
            r += BASE_RADIUS;
        }

    }

    protected BlockPos findSpawn(LevelInstanceManager manager) {
        return this.center.up(); // todo
    }

    public InstanceOptions(World world, CompoundTag tag, String suffix) {

        this.owner = PlayerHelper.getPlayer(world, tag.getString("owner_"+suffix));
        this.color = EnumColor.byIndex(tag.getInt("color_"+suffix));
        this.setSpawn(new BlockPos(tag.getInt("x_"+suffix), tag.getInt("y_"+suffix), tag.getInt("z_"+suffix)));
        // Setters

        this.center = new BlockPos(tag.getInt("center_x_"+suffix), tag.getInt("center_y_"+suffix), tag.getInt("center_z_"+suffix));
        this.radius = tag.getInt("radius_"+suffix);
        this.islandLevel = tag.getInt("island_level_"+suffix);
        this.expiration = tag.getLong("expiration_"+suffix);
    }

//    public void initIndexData(int index) {
//        setSpawn(new BlockPos(index * (INSTANCE_WIDTH + INSTANCE_MARGIN) + INSTANCE_WIDTH / 2, 60, INSTANCE_WIDTH / 2));
//    }

    public void setBiome(ModBiome biome) {
        this.forcedBiome = biome;
    }

    public void setSpawn(BlockPos pos) {
        this.spawn = pos;
    }
    public BlockPos getSpawn() {
        return spawn;
    }public int getSpawnX() {
        return spawn.getX();
    }
    public int getSpawnY() {
        return spawn.getY();
    }
    public int getSpawnZ() {
        return spawn.getZ();
    }
    public BlockPos getCenter() {
        return center;
    }
    public int getCenterX() {
        return center.getX();
    }
    public int getCenterY() {
        return center.getY();
    }
    public int getCenterZ() {
        return center.getZ();
    }
    public EnumColor getColor() {return color;}
    public int getRadius() {return radius;}
    public int getIslandLevel() {return islandLevel;}

    public boolean isDecaying() {
        return (EphemeralWorlds.ILLUSION_WORLD.getTime() > this.expiration);
    }

    public boolean hasExpired() {
        return hasExpired;
    }

    public CompoundTag toTag(CompoundTag tag, String suffix) {

        tag.putString("owner_"+suffix, owner!=null?owner.getName().asString():"");
        tag.putBoolean("expired_"+suffix, hasExpired);
        tag.putLong("expiration_"+suffix, expiration);
        tag.putInt("color_"+suffix, color.getIndex());
        tag.putInt("x_"+suffix, spawn.getX());
        tag.putInt("y_"+suffix, spawn.getY());
        tag.putInt("z_"+suffix, spawn.getZ());

        tag.putInt("center_x_"+suffix, center.getX());
        tag.putInt("center_y_"+suffix, center.getY());
        tag.putInt("center_z_"+suffix, center.getZ());
        tag.putInt("radius_"+suffix, radius);
        tag.putInt("island_level_"+suffix, islandLevel);

        return tag;
    }
}
