package net.ephemeralworlds.mixin;

import net.minecraft.block.entity.HopperBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(HopperBlockEntity.class)
public interface HopperBlockEntityAccessor {

    @Invoker("needsCooldown")
    boolean getNeedsCooldown();

    @Accessor("transferCooldown")
    int getCooldown();

    @Invoker("setCooldown")
    void setTransferCooldown(int transferCooldown);
}
