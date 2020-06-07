package net.ephemeralworlds.item.base;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.client.helper.TextHelper;
import net.ephemeralworlds.registry.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

import java.util.List;

public class ModItem extends Item {

    public ModItem(String uname) {
        this(new Item.Settings().group(EphemeralWorlds.GROUP), uname);
    }

    public ModItem(Settings settings, String uname) {
        super(settings.group(EphemeralWorlds.GROUP));

        ModItems.addItemForRegistration(uname, this);
    }

    public CompoundTag getItemTag(ItemStack stack) {
        CompoundTag tag = stack.getTag();

        if (tag == null) {
            tag = new CompoundTag();
            stack.setTag(tag);
        }
        return tag;
    }

    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack itemStack_1, World world_1, List<Text> list_1, TooltipContext tooltipContext_1) {
        if (itemStack_1.getItem() instanceof ITieredResource) {
            list_1.add((TextHelper.getTieredTooltipText(((ITieredResource) itemStack_1.getItem()).getTier())));
        }
    }
}
