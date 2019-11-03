package net.ephemeralworlds.utils.instanceHandler;

import net.ephemeralworlds.biome.base.ModBiome;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.helpers.PlayerHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;


public class InstanceOptions2 {

    PlayerEntity owner;
    boolean hasExpired;
    EnumColor color;

    ModBiome forcedBiome = null;

    public InstanceOptions2(PlayerEntity owner, EnumColor color) {
        this.owner = owner;
        this.color = color;
        hasExpired = false;
    }

    public InstanceOptions2(CompoundTag tag, String suffix) {

        this.owner = PlayerHelper.getPlayer(null, tag.getString("owner_"+suffix));
        this.color = EnumColor.byIndex(tag.getInt("color_"+suffix));

        // Setters

    }

    public void setBiome(ModBiome biome) {
        this.forcedBiome = biome;
    }

    boolean hasExpired() {
        return hasExpired;
    }



    public CompoundTag toTag(CompoundTag tag, String suffix) {

        tag.putString("owner_"+suffix, owner!=null?owner.getName().asString():"");
        tag.putBoolean("expired_"+suffix, hasExpired);
        tag.putInt("color_"+suffix, color.getIndex());

        return tag;
    }
}
