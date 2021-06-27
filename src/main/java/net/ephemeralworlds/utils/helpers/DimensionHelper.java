package net.ephemeralworlds.utils.helpers;

import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.instanceHandler.InstanceOptions;
import net.ephemeralworlds.utils.instanceHandler.LevelInstanceManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DimensionHelper {

    public static LevelInstanceManager getLevelManager(World world) {

//        IWorldInstanceManager wim = EphemeralWorlds.LEVEL_DATA.get(world.getLevelProperties()); // todo
//
//        return (LevelInstanceManager)wim;
        return null;
    }

    public static InstanceOptions getPlayerInstanceOptions(PlayerEntity player) {

//        IWorldInstanceManager wim = EphemeralWorlds.LEVEL_DATA.get(player.world.getLevelProperties()); // todo
//
//        return wim.getInstance(player);
        return null;
    }

    public static InstanceOptions getInstanceFromPosition(World world, BlockPos pos) {

//        IWorldInstanceManager wim = EphemeralWorlds.LEVEL_DATA.get(world.getLevelProperties()); // todo
//        return wim.getInstance(pos);
        return null;
    }

    public static EnumColor getColorFromPosition(World world, BlockPos pos) {

//        IWorldInstanceManager wim = EphemeralWorlds.LEVEL_DATA.get(world.getLevelProperties()); // todo
//        EnumColor color = wim.getInstanceColorFromCoord(pos);
//        if (color == null)
//            color = EnumColor.BLUE;
//
//        return color;
        return EnumColor.byIndex(0);
    }
}
