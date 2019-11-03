package net.ephemeralworlds.utils.instanceHandler;

import nerdhub.cardinal.components.api.component.Component;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface IWorldInstanceManager extends Component {

    public void createInstance(InstanceOptions options);
    public InstanceOptions getInstance(PlayerEntity player);
    public EnumColor getInstanceColorFromCoord(BlockPos pos);
}
