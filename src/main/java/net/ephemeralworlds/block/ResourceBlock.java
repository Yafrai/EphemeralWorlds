package net.ephemeralworlds.block;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.block.base.LayeredColorBlock;
import net.ephemeralworlds.block.entity.*;
import net.ephemeralworlds.block.entity.base.ResourceBlockEntity;
import net.ephemeralworlds.item.base.SoulTool;
import net.ephemeralworlds.item.base.TieredItem;
import net.ephemeralworlds.registry.ModBlocks;
import net.ephemeralworlds.registry.ModItems;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.enums.EnumColorBrightness;
import net.ephemeralworlds.utils.enums.EnumResourceType;
import net.ephemeralworlds.utils.helpers.ColorHelper;
import net.ephemeralworlds.utils.helpers.PlayerHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class ResourceBlock extends LayeredColorBlock implements BlockEntityProvider {

    public static final BooleanProperty DEPLETED = BooleanProperty.of("depleted");

    protected Item output;
    protected Identifier outputIdentifier;
    protected EnumResourceType effectiveTool;

    public ResourceBlock(String uname, Item output, EnumResourceType effectiveTool) {
        super(uname, Blocks.IRON_ORE, EnumColorBrightness.MINERAL);
        this.output = output;
        this.effectiveTool = effectiveTool;

        setDefaultState(getDefaultState().with(DEPLETED, false));
    }

    public ResourceBlock(String uname, Identifier output, EnumResourceType effectiveTool) {
        this(uname, Registry.BLOCK.get(output).asItem(), effectiveTool);
        this.outputIdentifier = output;
    }

//    public int getColor(BlockState blockState, ExtendedBlockView blockView, BlockPos blockPos, int tintIndex) {
//        if (tintIndex == 0) {
//            return new Color(255, 255, 255).getRGB();
//        }
//        else {
//            EnumColor color = getEnumColor(blockState);
//            return color.getColorForBrightness(EnumColorBrightness.MINERAL);
//        }
//    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        if (output.equals(ModItems.CHROMIUM_ORE))
            return new ChromiumOreBlockEntity(pos, state);
        else if (output.equals(ModItems.DIACHRONIUM_ORE))
            return new DiachroniumOreBlockEntity(pos, state);
//        else if (output.equals(ModItems.GEM1))
//            return new Gem1OreBlockEntity();
//        else if (output.equals(ModItems.GEM2))
//            return new Gem2OreBlockEntity();
//        else if (output.equals(ModItems.GEM3))
//            return new Gem3OreBlockEntity();
        else if (output.equals(ModBlocks.RUNE_STONE.asItem()))
            return new RuneStoneBlockEntity(pos, state);
        else if (outputIdentifier != null && outputIdentifier.equals(new Identifier(EphemeralWorlds.MODID, "rune_stone")))
            return new RuneStoneBlockEntity(pos, state);

//        else if (outputIdentifier != null && outputIdentifier.equals(new Identifier(EphemeralWorlds.MODID, "color_log")))
//            return new ColorTreeBlockEntity();
        else if (outputIdentifier != null && outputIdentifier.equals(new Identifier(EphemeralWorlds.MODID, "spirit_log")))
            return new SpiritTreeBlockEntity(pos, state);
        else if (outputIdentifier != null && outputIdentifier.equals(new Identifier(EphemeralWorlds.MODID, "rift_log")))
            return new RiftTreeBlockEntity(pos, state);

        return null;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> map) {
        super.appendProperties(map);
        map.add(DEPLETED);
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

                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.FAIL;
    }

    protected ItemStack getOutput(PlayerEntity player) {
        Item item;
        if (outputIdentifier != null) {
            item = Registry.BLOCK.get(outputIdentifier).asItem();
        }
        else
            item = output;

        ItemStack stack = new ItemStack(item);

        ColorHelper.setStackColor(stack, PlayerHelper.getPlayerColor(player));

        return stack;
    }

    public void replenish(World world, BlockPos pos) {
        world.setBlockState(pos, world.getBlockState(pos).with(DEPLETED, false));
    }

    public int getTier() {
        return ((TieredItem)output).getTier();
    }


}
