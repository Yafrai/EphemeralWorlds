package net.ephemeralworlds.recipe;


import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.item.base.ColorItem;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.enums.EnumRecipeColor;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class FusionCircleRecipe implements Recipe<Inventory> {
    public static RecipeSerializer<FusionCircleRecipe> SERIALIZER;
    public static RecipeType<FusionCircleRecipe> TYPE = RecipeType.register(EphemeralWorlds.MODID + "fusion");

    private final DefaultedList<Ingredient> input;
    private final Ingredient main;
    private final ItemStack output;
    private final Identifier id;
    private final EnumRecipeColor color;
    private final boolean copyColor;

    public FusionCircleRecipe(Identifier id, ItemStack output, Ingredient main, DefaultedList<Ingredient> input, EnumRecipeColor color, boolean copyColor) {
        this.id = id;
        this.main = main;
        this.input = input;
        this.output = output;
        this.color = color;
        this.copyColor = copyColor;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        // Main ingredient
        if (!main.test(inventory.getStack(0))) {
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

        for (int i=1; i<inventory.size(); i++) {
            ItemStack playerStack = inventory.getStack(i);
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
        return output.copy();
    }

    public ItemStack getActualOutput(EnumColor color) {
        ItemStack output = getOutput();
        Item item = output.getItem();

        if (copyColor && item instanceof ColorItem) {
            ColorItem colorItem = (ColorItem)item;
            colorItem.setTagColor(output, color);
        }

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
    public RecipeSerializer<FusionCircleRecipe> getSerializer() {
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
