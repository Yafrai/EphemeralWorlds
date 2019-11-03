package net.ephemeralworlds.utils.helpers;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.instanceHandler.IWorldInstanceManager;
import net.ephemeralworlds.utils.instanceHandler.InstanceOptions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DimensionHelper {

    public static InstanceOptions getPlayerInstanceOptions(PlayerEntity player) {

//        World illusionWorld = EphemeralWorlds.ILLUSION_WORLD;
//        IWorldInstanceManager wim = EphemeralWorlds.WORLD_DATA.get(illusionWorld);
//
//        return wim.getInstance(player);

        IWorldInstanceManager wim = EphemeralWorlds.LEVEL_DATA.get(player.world.getLevelProperties());

        return wim.getInstance(player);
    }

    public static EnumColor getColorFromPosition(BlockPos pos) {

        IWorldInstanceManager wim = EphemeralWorlds.LEVEL_DATA.get(EphemeralWorlds.ILLUSION_WORLD.getLevelProperties());
        return wim.getInstanceColorFromCoord(pos);

    }
}
