package net.ephemeralworlds.client.helper;

import net.ephemeralworlds.item.base.ITieredResource;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

public class TextHelper {
    public static TranslatableText getTieredTooltipText(int tier) {
        return new TranslatableText("tooltip.tier." + String.valueOf(tier));//.formatted(Formatting.ITALIC);
    }
}
