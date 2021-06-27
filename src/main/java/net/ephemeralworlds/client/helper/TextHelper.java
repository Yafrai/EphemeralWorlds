package net.ephemeralworlds.client.helper;

import net.minecraft.text.TranslatableText;

public class TextHelper {
    public static TranslatableText getTieredTooltipText(int tier) {
        return new TranslatableText("tooltip.tier." + String.valueOf(tier));//.formatted(Formatting.ITALIC);
    }
}
