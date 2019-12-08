package net.ephemeralworlds.block.base;

import com.google.common.collect.Lists;
import net.ephemeralworlds.registry.ModBlocks;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ExtendedBlockView;
import net.minecraft.world.loot.LootSupplier;
import net.minecraft.world.loot.LootTables;
import net.minecraft.world.loot.context.LootContext;
import net.minecraft.world.loot.context.LootContextParameters;
import net.minecraft.world.loot.context.LootContextTypes;

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
        Identifier identifier_1 = this.getDropTableId();
        if (identifier_1 == LootTables.EMPTY) {
            return Collections.emptyList();
        } else {
            LootContext lootContext_1 = lootContext$Builder_1.put(LootContextParameters.BLOCK_STATE, blockState_1).build(LootContextTypes.BLOCK);
            ServerWorld serverWorld_1 = lootContext_1.getWorld();
            LootSupplier lootSupplier_1 = serverWorld_1.getServer().getLootManager().getSupplier(identifier_1);
            List<ItemStack> dropList = lootSupplier_1.getDrops(lootContext_1);

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
}
