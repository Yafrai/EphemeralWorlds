package net.ephemeralworlds.block.entity.base;

import net.ephemeralworlds.block.ResourceBlock;
import net.ephemeralworlds.block.StructureResourceBlock;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.tools.BlockAndPos;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;


public abstract class StructureResourceBlockEntity extends ResourceBlockEntity {

//    BlockAndPos[] elements = new BlockAndPos[0];
//    int variant;

    public StructureResourceBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public NbtCompound writNbt(NbtCompound tag) {
        super.writeNbt(tag);
//        tag.putInt("variant", variant);
        return tag;
    }

    @Override
    public NbtCompound toClientTag(NbtCompound tag) {
        super.toClientTag(tag);
//        tag.putInt("variant", variant);
        return tag;
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
//        variant = tag.getInt("variant");
    }

    @Override
    public void fromClientTag(NbtCompound tag) {
        super.fromClientTag(tag);
//        variant = tag.getInt("variant");
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, ModBlockEntity blockEntity) {
        if (repop > 0 && world.getTime() >= repop) {
            repop = 0;

            if (state.getBlock() instanceof ResourceBlock) {
                ((ResourceBlock)state.getBlock()).replenish(world, pos);
                regenerateStructure();
            }

            saveAndNotify();
        }
    }

    protected void regenerateStructure() {
        for (BlockAndPos bp : getElements()) {
            if (!bp.pos.equals(pos))
                world.setBlockState(bp.pos, bp.state);
        }
    }

    public void removeStructure() {
        for (BlockAndPos bp : getElements()) {
            if (!bp.pos.equals(pos))
                world.setBlockState(bp.pos, Blocks.AIR.getDefaultState());
        }
    }

    protected List<BlockAndPos> getElements() {
//        ModTreeFeature feature = getFeature();
//
//        int variant = getVariant(world, pos);
//
//        List<BlockAndPos> elemets = feature.getBlocks(pos, variant, EnumColor.BLUE, true);
//        return elements;
        return DefaultedList.of();
    }

//    protected abstract ModTreeFeature getFeature();

    protected int getVariant(World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);

        if (state.getBlock() instanceof StructureResourceBlock) {
            return StructureResourceBlock.getVariantFroState(state);
        }

        return 0;
    }

}
