package net.ephemeralworlds.recipe;


import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.enums.EnumRecipeColor;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EaselRecipe implements Recipe<Inventory> {
    public static RecipeSerializer<EaselRecipe> SERIALIZER;
    public static RecipeType<EaselRecipe> TYPE = RecipeType.register(EphemeralWorlds.MODID + "easel");

    private final DefaultedList<Ingredient> input;
    private final Ingredient main;
    private final ItemStack output;
    private final Identifier id;
    private final EnumRecipeColor color;

    public EaselRecipe(Identifier id, ItemStack output, Ingredient main, DefaultedList<Ingredient> input, EnumRecipeColor color) {
        this.id = id;
        this.main = main;
        this.input = input;
        this.output = output;
        this.color = color;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        // Main ingredient
        if (!main.test(inventory.getInvStack(0))) {
            return false;
        }

        // Additional ingredients
        List<Ingredient> remaining = new ArrayList<>();
        for (int i=0; i<input.size(); i++) {
            if (!input.get(i).isEmpty()) {
                remaining.add(input.get(i));
            }
        }

//        if (remaining.size() != input.size())
//            return false;

        for (int i=1; i<inventory.getInvSize(); i++) {
            ItemStack playerStack = inventory.getInvStack(i);
            if (playerStack.isEmpty())
                continue;

            boolean match = false;

            for (int j=0; j<remaining.size(); j++) {
                Ingredient recipeStack = remaining.get(j);
                if (recipeStack.test(playerStack)) {
                    remaining.remove(j);
                    match = true;
                    break;
                }
            }
            // Not an actual ingredient in the grid
            if (!match)
                return false;
        }

        return remaining.isEmpty();
    }

    @Override
    public ItemStack craft(Inventory inventory) {
        return getOutput();
    }

    @Override
    public boolean fits(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return output;
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<EaselRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return TYPE;
    }

    public boolean colorFits(EnumColor color) {
        return this.color.fits(color);
    }
}
