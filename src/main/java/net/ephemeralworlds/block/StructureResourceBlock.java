package net.ephemeralworlds.block;

import net.ephemeralworlds.block.entity.base.ResourceBlockEntity;
import net.ephemeralworlds.block.entity.base.StructureResourceBlockEntity;
import net.ephemeralworlds.item.base.SoulTool;
import net.ephemeralworlds.utils.enums.EnumResourceType;
import net.ephemeralworlds.utils.helpers.PlayerHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class StructureResourceBlock extends ResourceBlock {

    public static IntProperty VARIANT = IntProperty.of("variant", 0, 12);

    public StructureResourceBlock(String uname, Item output, EnumResourceType effectiveTool) {
        super(uname, output, effectiveTool);

        setDefaultState(getDefaultState().with(VARIANT, 0));
    }

    public StructureResourceBlock(String uname, Identifier output, EnumResourceType effectiveTool) {
        super(uname, output, effectiveTool);

        setDefaultState(getDefaultState().with(VARIANT, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> map) {
        super.appendProperties(map);
        map.add(VARIANT);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
        if (state.get(DEPLETED))
            return ActionResult.FAIL;

        ItemStack toolStack = player.getStackInHand(hand);

        if (toolStack.getItem() instanceof SoulTool) {
            SoulTool tool = (SoulTool)toolStack.getItem();

            if (tool.isEffectiveAgainst(effectiveTool)) {
                BlockState depletedState = state.with(DEPLETED, true);
                world.setBlockState(pos, depletedState);
                PlayerHelper.givePlayerOrDrop(player, getOutput(player));
                BlockEntity entity = world.getBlockEntity(pos);

                if (entity instanceof ResourceBlockEntity) {
                    ((ResourceBlockEntity)entity).setReplenishTime(20);
                }

                if (entity instanceof StructureResourceBlockEntity) {
                    ((StructureResourceBlockEntity)entity).removeStructure();
                }

                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.FAIL;
    }

    public static BlockState getStateWithVariant(BlockState state, int variant) {
        return state.with(VARIANT, variant);
    }

    public static int getVariantFroState(BlockState state) {
        return state.get(VARIANT);
    }
}
