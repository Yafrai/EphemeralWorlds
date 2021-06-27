package net.ephemeralworlds.utils.instanceHandler;

//public class WorldInstanceManager implements IWorldInstanceManager, WorldSyncedComponent  {
//
//    private World world;
//
//    public WorldInstanceManager(World world) {
//        this.world = world;
//    }
//
//    @Override
//    public World getWorld() {
//        return world;
//    }
//
//    @Override
//    public ComponentType<?> getComponentType() {
//        return EphemeralWorlds.WORLD_DATA;
//    }
//
//    // Actual data
//
//    private List<InstanceOptions> instances = new ArrayList<>();
//
//    public void createInstance(InstanceOptions options) {
//        boolean found = false;
//
//        // Detach other instances form player
//        for (int i=0; i<instances.size(); i++) {
//            InstanceOptions option = instances.get(i);
//
//            if (option != null && option.owner!= null && option.owner.getName().asString().equals(options.owner.getName().asString())) {
//                option.owner = null;
//            }
//        }
//
//        // Replace expired instance with the new one
//        for (int i=0; i<instances.size(); i++) {
//            InstanceOptions option = instances.get(i);
//            if (option.hasExpired()) {
//                instances.set(i, options);
//                found = true;
//                break;
//            }
//        }
//
//        // Or add
//        if (!found) {
//            instances.add(options);
//        }
//
//        markDirty();
//        if (world instanceof ServerWorld)
//            syncWithAll(((ServerWorld)world).getServer());
//    }
//
//    public void syncWithAll(MinecraftServer server) {
//        WorldSyncedComponent.super.markDirty();
//    }
//
//    public InstanceOptions getInstance(PlayerEntity player) {
//        for (InstanceOptions instance: instances) {
//            if (instance.owner != null && instance.owner.getName().asString().equals(player.getName().asString()))
//                return instance;
//        }
//
//        return null;
//    }
//
//
//    @Override public void readNbt(NbtCompound tag) {
//        this.instances.clear();
//        int elements = tag.getInt("count");
//
//        for (int i=0; i<elements; i++) {
//            InstanceOptions option = new InstanceOptions(world, tag, Integer.toString(i));
//            instances.add(option);
//        }
//    }
//
//    @Override public NbtCompound writeNbt(NbtCompound tag) {
//        tag.putInt("count", instances.size());
//
//        for (int i=0; i<instances.size(); i++) {
//            InstanceOptions option = instances.get(i);
//            if (option != null) {
//                option.writeNbt(tag, Integer.toString(i));
//            }
//        }
//
//        return tag;
//    }
//
//
//}
