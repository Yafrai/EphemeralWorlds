package net.ephemeralworlds.registry;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.block.base.ColorBlock;
import net.ephemeralworlds.item.*;
import net.ephemeralworlds.item.base.ModItem;
import net.ephemeralworlds.item.base.ColorItem;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    private static List<Pair<String, Item>> ITEMS = new ArrayList<>();

    // Core
    public static final Item MAGIC_MUSHROOM = new MagicMushroom("magic_mushroom");
    public static final Item BRUSH = new Brush("brush");
    public static final Item BOTTLE = new ModItem("bottle");
    public static final Item ILLUSION_POTION = new IllusionPotion("illusion_potion");
//    public static final Item PAPER = new ModItem("paper");
    public static final Item GLYPH = new Glyph("glyph");

    // Tiered
    public static final Item GEM1 = new ColorItem("gem1", false);
    public static final Item GEM2 = new ColorItem("gem2", false);
    public static final Item GEM3 = new ColorItem("gem3", false);

    public static final Item BONE1 = new ModItem("bone1");
    public static final Item BONE2 = new ModItem("bone2");
    public static final Item BONE3 = new ModItem("bone3");

    public static final Item TERIUM_INGOT = new ModItem("terium_ingot");
    public static final Item XERIUM_INGOT = new ModItem("xerium_ingot");

    // Misc
    public static final Item CREATIVE_PORTAL = new CreativePortal("creative_portal");
    public static final Item TERIUM_NUGGET = new ModItem("terium_nugget");
    public static final Item XERIUM_NUGGET = new ModItem("xerium_nugget");

    public static final Item HOURGLASS = new ColorItem("hourglass", false);
    public static final Item PRISM = new ModItem("prism");
    public static final Item TOP = new ModItem("top");
    public static final Item MIRROR = new ModItem("mirror");

    public static void registerItems() {
        for (Pair<String, Item> pair: ITEMS) {
            String name = pair.getLeft();
            Item item = pair.getRight();

            Registry.register(Registry.ITEM, new Identifier(EphemeralWorlds.MODID, name), item);

            if (item instanceof ItemColorProvider) {
                ColorProviderRegistry.ITEM.register(((ItemColorProvider) item), item);
            }
        }
    }

    public static void addItemForRegistration(String uname, Item item) {
        ITEMS.add(new Pair<>(uname, item));
    }
}
