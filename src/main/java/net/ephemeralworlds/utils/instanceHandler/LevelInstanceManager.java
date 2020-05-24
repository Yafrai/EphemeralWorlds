package net.ephemeralworlds.utils.instanceHandler;

import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.util.sync.LevelSyncedComponent;
import nerdhub.cardinal.components.api.util.sync.WorldSyncedComponent;
import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.registry.ModDimensions;
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
        // Remove expired instances
        for (int i=instances.size()-1; i>=0; i--) {
            if (i >= instances.size())
                continue;

            InstanceOptions option = instances.get(i);
            if (option.hasExpired()) {
                instances.remove(i);
            }
        }

        // Add new instance
        instances.add(options);

        markDirty();

//        if (world instanceof ServerWorld)
//            syncWithAll(((ServerWorld)world).getServer());
    }

    public void syncWithAll(MinecraftServer server) {
        LevelSyncedComponent.super.syncWithAll(server);
    }

    public InstanceOptions getInstance(PlayerEntity player) {
        InstanceOptions inst = null;
        for (InstanceOptions instance: instances) {
            if (instance.owner != null && instance.owner.getName().asString().equals(player.getName().asString()))
                inst = instance;
        }

        return inst;
    }

    public InstanceOptions getInstance(BlockPos pos) {

        int x, z;

        for (InstanceOptions instance: instances) {
            x = instance.getCenterX() - pos.getX();
            z = instance.getCenterZ() - pos.getZ();
            if (x*x + z*z <= instance.radius * instance.radius)
                return instance;
        }

        return null;
    }



    @Override
    public EnumColor getInstanceColorFromCoord(BlockPos pos) {
        InstanceOptions instance = getInstance(pos);

        if (instance == null)
            return null;

        return instance.color;
    }

    public boolean testIslandPlace(int x, int y, int z, int radius) {
        for (InstanceOptions instance: instances) {

            double distSq = Math.pow(x - instance.getCenterX(), 2) + Math.pow(y - instance.getCenterY(), 2) + Math.pow(z - instance.getCenterZ(), 2);

            if (distSq < Math.pow(radius + instance.radius, 2)) {
                return false;
            }
        }

        return true;
    }


    @Override public void fromTag(CompoundTag tag) {
        this.instances.clear();
        int elements = tag.getInt("count");
//        World world = MinecraftServer.worlds.getWorld(ModDimensions.ephemerium);

        for (int i=0; i<elements; i++) {
            InstanceOptions option = new InstanceOptions(null, tag, Integer.toString(i));
//            option.initIndexData(instances.size());
            instances.add(option);
        }
    }

    @Override public CompoundTag toTag(CompoundTag tag) {
        tag.putInt("count", instances.size());
        // todo list
        for (int i=0; i<instances.size(); i++) {
            InstanceOptions option = instances.get(i);
            if (option != null) {
                option.toTag(tag, Integer.toString(i));
            }
        }

        return tag;
    }


    public InstanceOptions[] getAllInstancesOptions() {
        InstanceOptions[] opts = new InstanceOptions[instances.size()];
        instances.toArray(opts);
        return opts;
    }
}
