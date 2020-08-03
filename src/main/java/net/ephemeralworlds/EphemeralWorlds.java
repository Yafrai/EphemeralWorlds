package net.ephemeralworlds;

import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.LevelComponentCallback;
import nerdhub.cardinal.components.api.event.WorldComponentCallback;
import nerdhub.foml.obj.OBJLoader;
import net.ephemeralworlds.dimension.IllusionDimension;
import net.ephemeralworlds.recipe.FusionCircleRecipe;
import net.ephemeralworlds.recipe.FusionCircleRecipeSerializer;
import net.ephemeralworlds.recipe.TinkeringTableRecipe;
import net.ephemeralworlds.recipe.TinkeringTableRecipeSerializer;
import net.ephemeralworlds.registry.*;
import net.ephemeralworlds.utils.instanceHandler.IWorldInstanceManager;
import net.ephemeralworlds.utils.instanceHandler.LevelInstanceManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.world.WorldTickCallback;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class EphemeralWorlds implements ModInitializer {

	public static final String MODID = "ephemeralworlds";

	public static final ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier(MODID, "group"), () -> new ItemStack(ModBlocks.GEM_ORE1));

//	public static final ComponentType<IWorldInstanceManager> WORLD_DATA = ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier(EphemeralWorlds.MODID, "world_data"), IWorldInstanceManager.class);
	public static final ComponentType<IWorldInstanceManager> LEVEL_DATA = ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier(EphemeralWorlds.MODID, "world_data"), IWorldInstanceManager.class);

	public static World ILLUSION_WORLD = null;

	@Override
	public void onInitialize() {

		OBJLoader.INSTANCE.registerDomain(MODID);

		ModItems.registerItems();
		ModBlocks.registerBlocks();
		ModBlockEntities.registerBlockEntities();
		ModBlockEntityRenderers.registerRenderers();
		ModStatusEffects.registerStatusEffects();
		ModBiomes.registerBiomes();
		ModDimensions.registerDimensions();

		FusionCircleRecipe.SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(EphemeralWorlds.MODID, "fusion_recipe"), new FusionCircleRecipeSerializer());
		TinkeringTableRecipe.SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(EphemeralWorlds.MODID, "tinkering_recipe"), new TinkeringTableRecipeSerializer());

		// Add the component to illusion world
		WorldComponentCallback.EVENT.register((world, components) -> {
			if (world.getDimension() instanceof IllusionDimension) {
//				components.put(WORLD_DATA, new WorldInstanceManager(world));
				ILLUSION_WORLD = world;
			}
		});

		// Add the component to level
		LevelComponentCallback.EVENT.register((level, components) -> {
			components.put(LEVEL_DATA, new LevelInstanceManager());
		});

		WorldTickCallback.EVENT.register((world) -> {
//			if (world.getDimension().getType() != ModDimensions.ephemerium)
//				return;
//
//			LevelInstanceManager wim = DimensionHelper.getLevelManager(world);
//			int globalRadiusSq = 0;
//			int islandRadius = 0;
//			for (InstanceOptions island: wim.getAllInstancesOptions()) {
//				BlockPos center = island.getCenter();
//
//				double radius = center.getSquaredDistance(new BlockPos(0, center.getY(), 0));
//				if (radius > globalRadiusSq)
//					globalRadiusSq = (int)radius;
//
//				if (island.getRadius() > islandRadius)
//					islandRadius = island.getRadius();
//			}
//
//			int globalRadius = (int)Math.sqrt(globalRadiusSq) + islandRadius + 200;
//
//			ChunkManager manager = world.getChunkManager();
//
//			for (int i=-globalRadius; i<=globalRadius; i+=16) {
//				int reducedRadius = (int)Math.sqrt(Math.max(globalRadius*globalRadius - i*i, 0));
//				for (int j=-reducedRadius; j<=reducedRadius; j+=16) {
//					WorldChunk chunk = manager.getWorldChunk(i, j, false);
//
//					if (chunk != null) {
//						ChunkPos chPos = chunk.getPos();
//						int r = 20;
//						while (r > 0) {
//							BlockPos randomPos = world.getRandomPosInChunk(chPos.getStartX(), 0, chPos.getStartZ(), 255);
//
//							BlockState state = world.getBlockState(randomPos);
//
//							if (!state.isAir() && state.getBlock() != ModBlocks.DECAYED_SOIL)
//								world.setBlockState(randomPos, ModBlocks.DECAYED_SOIL.getDefaultState());
//							r--;
//						}
//					}
//				}
//			}

			;


		});
	}
}
