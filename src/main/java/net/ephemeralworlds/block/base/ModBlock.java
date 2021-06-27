package net.ephemeralworlds.block.base;

import com.google.common.collect.Lists;
import net.ephemeralworlds.client.helper.TextHelper;
import net.ephemeralworlds.item.base.ITieredResource;
import net.ephemeralworlds.registry.ModBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.BlockView;

import java.util.Collections;
import java.util.List;

public class ModBlock extends Block {

    public ModBlock(String uname, Block base) {
        this(uname, FabricBlockSettings.copy(base).build());
    }

    public ModBlock(String uname, Settings settings) {
        super(settings);
        ModBlocks.addBlockForRegistration(uname, this);
    }

    // Autoregister loot, still takes loot tables if found
    @Override
    public List<ItemStack> getDroppedStacks(BlockState blockState_1, LootContext.Builder lootContext$Builder_1) {
        Identifier identifier_1 = this.getLootTableId();
        if (identifier_1 == LootTables.EMPTY) {
            return Collections.emptyList();
        } else {
            LootContext lootContext_1 = lootContext$Builder_1.optionalParameter(LootContextParameters.BLOCK_STATE, blockState_1).build(LootContextTypes.BLOCK);
            ServerWorld serverWorld_1 = lootContext_1.getWorld();
            LootTable lootTable = serverWorld_1.getServer().getLootManager().getTable(identifier_1);
            List<ItemStack> dropList = lootTable.generateLoot(lootContext_1);

            if (dropList.isEmpty()) {
                return defaultDropList(blockState_1);
            }
            else
                return dropList;
        }
    }

    public List<ItemStack> defaultDropList(BlockState state) {
        List<ItemStack> list_1 = Lists.newArrayList();
        list_1.add(new ItemStack(this));
        return list_1;
    }

    @Environment(EnvType.CLIENT)
    public void buildTooltip(ItemStack itemStack_1, BlockView blockView_1, List<Text> list_1, TooltipContext tooltipContext_1) {
        if (itemStack_1.getItem() instanceof BlockItem) {
            Block block = ((BlockItem)itemStack_1.getItem()).getBlock();
            if (block instanceof ITieredResource) {
                list_1.add(TextHelper.getTieredTooltipText(((ITieredResource) block).getTier()));
            }
        }
    }
}
