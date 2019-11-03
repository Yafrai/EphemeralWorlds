package net.ephemeralworlds.recipe;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.enums.EnumRecipeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class EaselRecipeSerializer implements RecipeSerializer<EaselRecipe> {

    private static ItemStack parseItemStack(JsonObject jsonObject) {
        String itemName = JsonHelper.getString(jsonObject, "item");
        String itemTags = JsonHelper.getString(jsonObject, "tag", "");

        Item item = Registry.ITEM.getOrEmpty(new Identifier(itemName)).orElseThrow(() -> new JsonSyntaxException("Unknown item '" + itemName + "'"));
        ItemStack stack = new ItemStack(item);
        if (!itemTags.isEmpty()) {
            CompoundTag tag = new CompoundTag();
//            tag.put
            stack.setTag(tag);
        }
        return stack;
    }

    private static DefaultedList<Ingredient> parseIngredientList(JsonArray array) {
        DefaultedList<Ingredient> ingredients = DefaultedList.of();

        for (int i = 0; i < array.size(); ++i) {
            Ingredient ingredient = Ingredient.fromJson(array.get(i));
            if (!ingredient.isEmpty()) {
                ingredients.add(ingredient);
            }
        }

        return ingredients;
    }

    @Override
    public EaselRecipe read(Identifier identifier, JsonObject jsonObject) {
        Ingredient main = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "main"));
        DefaultedList<Ingredient> ingredients = parseIngredientList(JsonHelper.getArray(jsonObject, "ingredients"));
        ItemStack output = parseItemStack(JsonHelper.getObject(jsonObject, "result"));

        if (main.isEmpty()) {
            throw new JsonParseException("No main ingredient in easel recipe");
        } else if (ingredients.size() > 9) {
            throw new JsonParseException("Too many ingredients in easel recipe (more than 5)");
        }

        String colorStr = JsonHelper.getString(jsonObject, "color");

        EnumRecipeColor color = EnumRecipeColor.byName(colorStr);
        return new EaselRecipe(identifier, output, main, ingredients, color);

    }

    @Override
    public EaselRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
        return null;
    }

    @Override
    public void write(PacketByteBuf packetByteBuf, EaselRecipe easelRecipe) {
        int a = 0;
    }
}