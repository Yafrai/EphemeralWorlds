package net.ephemeralworlds.registry;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.item.*;
import net.ephemeralworlds.item.base.*;
import net.ephemeralworlds.item.rune.IgnitionRune;
import net.ephemeralworlds.item.rune.PotionRune;
import net.ephemeralworlds.utils.enums.EnumResourceType;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
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

    // Tiered
    public static final Item ARCANE_QUARTZ = new TieredColorItem("arcane_quartz", 1, EnumResourceType.PRECIOUS_STONE, false);
    public static final Item CHROMIUM_INGOT = new TieredItem("chromium_ingot", 2, EnumResourceType.METAL);
    public static final Item DIACHRONIUM_INGOT = new TieredItem("diachronium_ingot", 2, EnumResourceType.METAL);

    // Crafted
    public static final Item ARCANE_POWDER = new ModItem("arcane_powder");

    // Tools and weapons
    public static final Item SOUL_PICKAXE = new SoulPickaxe("soul_pickaxe");
    public static final Item SOUL_AXE = new SoulAxe("soul_axe");
    public static final Item SOUL_SHEARS = new SoulShears("soul_shears");
    public static final Item SOUL_SHOVEL = new SoulShears("soul_shovel");

    // Misc
    public static final Item POISON_RUNE = new PotionRune("poison_rune", StatusEffects.POISON, 40);
    public static final Item WEAKNESS_RUNE = new PotionRune("weakness_rune", StatusEffects.WEAKNESS, 40);
    public static final Item IGNITION_RUNE = new IgnitionRune("ignition_rune");
    public static final Item CREATIVE_PORTAL = new CreativePortal("creative_portal");
    public static final Item CHROMIUM_ORE = new ModItem("chromium_ore");
    public static final Item DIACHRONIUM_ORE = new ModItem("diachronium_ore");
    public static final Item CHROMIUM_NUGGET = new ModItem("chromium_nugget");
    public static final Item DIACHRONIUM_NUGGET = new ModItem("diachronium_nugget");

    public static final Item HOURGLASS = new GlyphItem("hourglass", false);
    public static final Item PRISM = new GlyphItem("prism", false);
    public static final Item TOP = new GlyphItem("top", false);
    public static final Item MIRROR = new GlyphItem("mirror", false);

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
