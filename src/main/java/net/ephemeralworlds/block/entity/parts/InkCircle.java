package net.ephemeralworlds.block.entity.parts;

import net.ephemeralworlds.block.entity.InkDrawBlockEntity;
import net.ephemeralworlds.block.entity.base.ModInkTankBlockEntity;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.List;

public class InkCircle extends ACircle {

    BlockPos providerPos = null;
    BlockPos receptorPos = null;

    public InkCircle(InkDrawBlockEntity drawEntity, Direction face, EnumColor color) {
        super(drawEntity, face, color);
    }

    @Override
    public String getName() {
        return "ink_circle";
    }

    @Override
    public int getMaxUsages() {
        return 10;
    }

    @Override
    public boolean activateWithPaint(ItemStack brushStack) {
        return false;
    }

    @Override
    public NbtCompound writeTag(NbtCompound tag) {
        super.writeTag(tag);
        if (providerPos != null) {
            tag.putInt("provider_x", providerPos.getX());
            tag.putInt("provider_y", providerPos.getY());
            tag.putInt("provider_z", providerPos.getZ());
        }

        if (receptorPos != null) {
            tag.putInt("receptor_x", receptorPos.getX());
            tag.putInt("receptor_y", receptorPos.getY());
            tag.putInt("receptor_z", receptorPos.getZ());
        }
        return tag;
    }

    @Override
    public void readTag(NbtCompound tag) {
        super.readTag(tag);
        providerPos = new BlockPos(tag.getInt("provider_x"), tag.getInt("provider_y"), tag.getInt("provider_z"));
        receptorPos = new BlockPos(tag.getInt("receptor_x"), tag.getInt("receptor_y"), tag.getInt("receptor_z"));
    }

    @Override
    public void drop() {

    }

    public void tick() {
        super.tick();
        if (isActive()) {
            if (getWorld().isClient()) {
                spawnParticle();
            }
        }
        else {
            if (!fainting)
                checkConfiguration();
        }
    }

    public void spawnParticle() {
        BlockPos pos = drawEntity.getPos();
        getWorld().addParticle(ParticleTypes.CRIT, pos.getX() + .5 - .5 * face.getOffsetX(), pos.getY() + .5 - .5 * face.getOffsetY(), pos.getZ() + .5 - .5 * face.getOffsetZ(), face.getOffsetX(), face.getOffsetY(), face.getOffsetZ());
    }

    public void checkConfiguration() {
        ModInkTankBlockEntity receptor = getReceptor();

        if (receptor == null || receptor.isFull())
            return;

        ModInkTankBlockEntity[] providers = getProviders();

        if (providers.length == 0)
            return;

        for (ModInkTankBlockEntity provider: providers) {
            if (provider.isEmpty())
                continue;

            EnumColor color = provider.getColor();
            if (!receptor.canReceiveColor(color))
                continue;

            providerPos = provider.getPos();
            receptorPos = receptor.getPos();
            activate(true);
            return;
        }
    }

    @Override
    public boolean endAction() {

        if (getWorld().isClient)
            return false;

        ModInkTankBlockEntity provider = getInkTankEntity(providerPos);
        ModInkTankBlockEntity receptor = getInkTankEntity(receptorPos);

        if (provider == null || receptor == null) {
            return false;
        }

        ModInkTankBlockEntity.InkTransferStatus provided = provider.provideInk(1);
        if (provided.isSuccess()) {
            receptor.receiveInk(1, color);
            return true;
        }

        return false;
    }

    public ModInkTankBlockEntity getReceptor() {

        for (int i=1; i<4; i++) {
            BlockPos position = drawEntity.getPos().offset(face, i);
            BlockEntity entity = getWorld().getBlockEntity(position);
            if (entity instanceof ModInkTankBlockEntity)
                return (ModInkTankBlockEntity)entity;
            else if (entity != null && !(entity instanceof InkDrawBlockEntity))
                return null;
        }

        return null;
    }

    public ModInkTankBlockEntity[] getProviders() {
        BlockPos blockPos = drawEntity.getPos().offset(face.getOpposite());
        List<ModInkTankBlockEntity> list = new ArrayList<>();

        for (Direction direction: Direction.values()) {
            if (direction == face)
                continue;
            BlockEntity entity = getWorld().getBlockEntity(blockPos.offset(direction));
            if (entity instanceof ModInkTankBlockEntity)
                list.add((ModInkTankBlockEntity)entity);
        }

        ModInkTankBlockEntity[] array = new ModInkTankBlockEntity[list.size()];
        list.toArray(array);
        return array;
    }
}
