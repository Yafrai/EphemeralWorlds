package net.ephemeralworlds.utils.instanceHandler;

import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.util.sync.LevelSyncedComponent;
import nerdhub.cardinal.components.api.util.sync.WorldSyncedComponent;
import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class LevelInstanceManager implements IWorldInstanceManager, LevelSyncedComponent {


    public LevelInstanceManager() {
    }

    @Override
    public ComponentType<?> getComponentType() {
        return EphemeralWorlds.LEVEL_DATA;
    }

    // Actual data

    private List<InstanceOptions> instances = new ArrayList<>();

    public void createInstance(InstanceOptions options) {
        boolean found = false;

        // Detach other instances form player
        for (int i=0; i<instances.size(); i++) {
            InstanceOptions option = instances.get(i);

            if (option != null && option.owner!= null && option.owner.getName().asString().equals(options.owner.getName().asString())) {
                option.owner = null;
            }
        }

        // Replace expired instance with the new one
        for (int i=0; i<instances.size(); i++) {
            InstanceOptions option = instances.get(i);
            if (option.hasExpired()) {
                options.initIndexData(i);
                instances.set(i, options);
                found = true;
                break;
            }
        }

        // Or add
        if (!found) {
            options.initIndexData(instances.size());
            instances.add(options);
        }

        markDirty();
//        if (world instanceof ServerWorld)
//            syncWithAll(((ServerWorld)world).getServer());
    }

    public void syncWithAll(MinecraftServer server) {
        LevelSyncedComponent.super.syncWithAll(server);
    }

    public InstanceOptions getInstance(PlayerEntity player) {
        for (InstanceOptions instance: instances) {
            if (instance.owner != null && instance.owner.getName().asString().equals(player.getName().asString()))
                return instance;
        }

        return null;
    }

    public InstanceOptions getInstanceFromCoord(BlockPos pos) {
        if (pos.getX() < 0)
            return null;

        if (pos.getZ() < 0 || pos.getZ() > InstanceOptions.INSTANCE_WIDTH)
            return null;

        int index = Math.floorDiv(pos.getX(), InstanceOptions.INSTANCE_WIDTH + InstanceOptions.INSTANCE_MARGIN);

        try {
            return instances.get(index);
        }
        catch (Exception e) {
            return null;
        }
    }



    @Override
    public EnumColor getInstanceColorFromCoord(BlockPos pos) {
        InstanceOptions instance = getInstanceFromCoord(pos);

        if (instance == null)
            return null;

        return instance.color;
    }


    @Override public void fromTag(CompoundTag tag) {
        this.instances.clear();
        int elements = tag.getInt("count");

        for (int i=0; i<elements; i++) {
            InstanceOptions option = new InstanceOptions(null, tag, Integer.toString(i));
            option.initIndexData(instances.size());
            instances.add(option);
        }
    }

    @Override public CompoundTag toTag(CompoundTag tag) {
        tag.putInt("count", instances.size());

        for (int i=0; i<instances.size(); i++) {
            InstanceOptions option = instances.get(i);
            if (option != null) {
                option.toTag(tag, Integer.toString(i));
            }
        }

        return tag;
    }


}
