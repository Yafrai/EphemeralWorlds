package net.ephemeralworlds;

import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.LevelComponentCallback;
import nerdhub.cardinal.components.api.event.WorldComponentCallback;
import nerdhub.foml.obj.OBJLoader;
import net.ephemeralworlds.dimension.IllusionDimension;
import net.ephemeralworlds.recipe.EaselRecipe;
import net.ephemeralworlds.recipe.EaselRecipeSerializer;
import net.ephemeralworlds.registry.*;
import net.ephemeralworlds.utils.instanceHandler.IWorldInstanceManager;
import net.ephemeralworlds.utils.instanceHandler.LevelInstanceManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class EphemeralWorlds implements ModInitializer {

	public static final String MODID = "ephemeralworlds";

	public static final ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier(MODID, "group"), () -> new ItemStack(ModBlocks.GEM_ORE));

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

		EaselRecipe.SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(EphemeralWorlds.MODID, "easel_recipe"), new EaselRecipeSerializer());

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
	}
}
