package net.ephemeralworlds.mixin;

import net.ephemeralworlds.registry.ModBlocks;
import net.ephemeralworlds.registry.ModDimensions;
import net.ephemeralworlds.registry.ModStatusEffects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.container.PlayerContainer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

	@Shadow @Final public PlayerContainer playerContainer;

	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType_1, World world_1) {
		super(entityType_1, world_1);
	}

	@Inject(method = "tick", at = @At("HEAD"), cancellable = true)
	private void tick(CallbackInfo info) {

		boolean illusion = this.hasStatusEffect(ModStatusEffects.illusionEffect);
		boolean nausea = this.hasStatusEffect(StatusEffects.NAUSEA);

		if (illusion) {
			// Check enter illusion
			if (!nausea || getStatusEffect(StatusEffects.NAUSEA).getDuration() <= 3*20) {
				if (!dimension.equals(ModDimensions.illusion)) {
					changeDimension(ModDimensions.illusion);
				}
			}

			if (!nausea && getStatusEffect(ModStatusEffects.illusionEffect).getDuration() < 3*20)
				addPotionEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 6*20));
		}
		else {
			// Check exit dimension
			if (dimension.equals(ModDimensions.illusion)) {
				changeDimension(DimensionType.OVERWORLD);
			}
		}
	}
}
