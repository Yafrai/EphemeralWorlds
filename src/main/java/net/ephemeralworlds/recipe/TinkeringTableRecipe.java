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
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class TinkeringTableRecipe implements Recipe<Inventory> {
    public static RecipeSerializer<TinkeringTableRecipe> SERIALIZER;
    public static RecipeType<TinkeringTableRecipe> TYPE = RecipeType.register(EphemeralWorlds.MODID + "easel");

    private final DefaultedList<Ingredient> input;
    private final ItemStack output;
    private final Identifier id;

    public TinkeringTableRecipe(Identifier id, ItemStack output, DefaultedList<Ingredient> input) {
        this.id = id;
        this.input = input;
        this.output = output;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {

        int[][] slots = {
                {0, 1, 2, 3, 4, 5, 6, 7, 8},
                {2, 1, 0, 5, 4, 3, 8, 7, 6},
                {8, 7, 6, 5, 4, 3, 2, 1, 0},
                {6, 7, 8, 3, 4, 5, 0, 1, 2},
                {0, 3, 6, 1, 4, 7, 2, 5, 8},
                {6, 3, 0, 7, 4, 1, 8, 5, 2},
                {2, 5, 8, 1, 4, 7, 0, 3, 6},
                {8, 5, 2, 7, 4, 1, 6, 3, 0}
        };

        for (int[]slotsFor: slots) {
            int recipeIndex = 0;
            boolean valid = true;
            for (int slotIndex: slotsFor) {
                if (recipeIndex >= input.size())
                    continue;
                Ingredient recipeItem = input.get(recipeIndex);
                ItemStack crafingItem = inventory.getInvStack(slotIndex);

                if (!recipeItem.test(crafingItem)) {
                    valid = false;
                    break;
                }

                recipeIndex++;
            }
            if (valid)
                return true;
        }
        return false;
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

        if (item instanceof ColorItem) {
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
    public RecipeSerializer<TinkeringTableRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return TYPE;
    }
}
