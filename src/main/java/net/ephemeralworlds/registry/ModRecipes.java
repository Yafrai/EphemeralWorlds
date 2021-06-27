package net.ephemeralworlds.registry;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.recipe.FusionCircleRecipe;
import net.ephemeralworlds.recipe.FusionCircleRecipeSerializer;
import net.ephemeralworlds.recipe.TinkeringTableRecipe;
import net.ephemeralworlds.recipe.TinkeringTableRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRecipes {

    public static void registerRecipes() {
        FusionCircleRecipe.SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(EphemeralWorlds.MODID, "fusion_recipe"), new FusionCircleRecipeSerializer());
        TinkeringTableRecipe.SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(EphemeralWorlds.MODID, "tinkering_recipe"), new TinkeringTableRecipeSerializer());
    }
}
