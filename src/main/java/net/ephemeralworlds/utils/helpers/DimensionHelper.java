package net.ephemeralworlds.utils.helpers;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.instanceHandler.IWorldInstanceManager;
import net.ephemeralworlds.utils.instanceHandler.InstanceOptions;
import net.ephemeralworlds.utils.instanceHandler.LevelInstanceManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DimensionHelper {

    public static LevelInstanceManager getLevelManager(World world) {

//        World illusionWorld = EphemeralWorlds.ILLUSION_WORLD;
//        IWorldInstanceManager wim = EphemeralWorlds.WORLD_DATA.get(illusionWorld);
//
//        return wim.getInstance(player);

        IWorldInstanceManager wim = EphemeralWorlds.LEVEL_DATA.get(world.getLevelProperties());

        return (LevelInstanceManager)wim;
    }

    public static InstanceOptions getPlayerInstanceOptions(PlayerEntity player) {

//        World illusionWorld = EphemeralWorlds.ILLUSION_WORLD;
//        IWorldInstanceManager wim = EphemeralWorlds.WORLD_DATA.get(illusionWorld);
//
//        return wim.getInstance(player);

        IWorldInstanceManager wim = EphemeralWorlds.LEVEL_DATA.get(player.world.getLevelProperties());

        return wim.getInstance(player);
    }

    public static InstanceOptions getInstanceFromPosition(World world, BlockPos pos) {

        IWorldInstanceManager wim = EphemeralWorlds.LEVEL_DATA.get(world.getLevelProperties());
        return wim.getInstance(pos);
    }

    public static EnumColor getColorFromPosition(World world, BlockPos pos) {

        IWorldInstanceManager wim = EphemeralWorlds.LEVEL_DATA.get(world.getLevelProperties());
        EnumColor color = wim.getInstanceColorFromCoord(pos);
        if (color == null)
            color = EnumColor.BLUE;

        return color;
    }
}
