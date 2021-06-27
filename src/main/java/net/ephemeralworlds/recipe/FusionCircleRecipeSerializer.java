package net.ephemeralworlds.recipe;


import com.google.gson.*;
import net.ephemeralworlds.utils.enums.EnumRecipeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.registry.Registry;

import java.util.Map;

public class FusionCircleRecipeSerializer implements RecipeSerializer<FusionCircleRecipe> {

    private static ItemStack parseItemStack(JsonObject jsonObject) {
        String itemName = JsonHelper.getString(jsonObject, "item");
        JsonObject itemTags = JsonHelper.getObject(jsonObject, "tag", new JsonObject());

        Item item = Registry.ITEM.getOrEmpty(new Identifier(itemName)).orElseThrow(() -> new JsonSyntaxException("Unknown item '" + itemName + "'"));
        ItemStack stack = new ItemStack(item);
        if (itemTags.size() > 0) {
            NbtCompound tag = new NbtCompound();
            for (Map.Entry<String, JsonElement> data: itemTags.entrySet())
                tag.putString(data.getKey(), data.getValue().getAsString());
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
    public FusionCircleRecipe read(Identifier identifier, JsonObject jsonObject) {
        Ingredient main = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "main"));
        DefaultedList<Ingredient> ingredients = parseIngredientList(JsonHelper.getArray(jsonObject, "ingredients"));
        ItemStack output = parseItemStack(JsonHelper.getObject(jsonObject, "result"));

        if (main.isEmpty()) {
            throw new JsonParseException("No main ingredient in fusion recipe");
        } else if (ingredients.size() > 9) {
            throw new JsonParseException("Too many ingredients in fusion recipe (more than 5)");
        }

        String colorStr = JsonHelper.getString(jsonObject, "color");
        boolean copyColor = JsonHelper.getBoolean(jsonObject, "recolor");

        EnumRecipeColor color = EnumRecipeColor.byName(colorStr);
        return new FusionCircleRecipe(identifier, output, main, ingredients, color, copyColor);

    }

    @Override
    public FusionCircleRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
        return null;
    }

    @Override
    public void write(PacketByteBuf packetByteBuf, FusionCircleRecipe fusionCircleRecipe) {
    }
}
