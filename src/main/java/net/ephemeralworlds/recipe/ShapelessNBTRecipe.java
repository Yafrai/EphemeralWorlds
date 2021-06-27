package net.ephemeralworlds.recipe;


import net.ephemeralworlds.EphemeralWorlds;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

// A recipe in which a given list of tags is copied from ingredients to result
public class ShapelessNBTRecipe implements Recipe<Inventory> {
    public static RecipeSerializer<ShapelessNBTRecipe> SERIALIZER;
    public static RecipeType<ShapelessNBTRecipe> TYPE = RecipeType.register(EphemeralWorlds.MODID + "shapeless_nbt");

    private final DefaultedList<Ingredient> input;
    private final DefaultedList<String> tagsToCopy;
    private final ItemStack output;
    private final Identifier id;

    public ShapelessNBTRecipe(Identifier id, ItemStack output, DefaultedList<Ingredient> input, DefaultedList<String> tagsToCopy) {
        this.id = id;
        this.input = input;
        this.output = output;
        this.tagsToCopy = tagsToCopy;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {

        // Matching ingredients
        List<Ingredient> remaining = new ArrayList<>();
        for (int i=0; i<input.size(); i++) {
            if (!input.get(i).isEmpty()) {
                remaining.add(input.get(i));
            }
        }

        if (remaining.size() != input.size())
            return false;

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
        ItemStack result = output.copy();

        NbtCompound tag = new NbtCompound();

//        for (String tagName: tagsToCopy) {
//            String value = "";
//
//            for (item:)
//
//            tag.putString(tagName, value);
//
//        }

        return result;
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
    public RecipeSerializer<ShapelessNBTRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return TYPE;
    }
}
