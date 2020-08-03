package net.ephemeralworlds.mixin;

import com.raphydaphy.crochet.data.PlayerData;
import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.registry.ModDimensions;
import net.ephemeralworlds.registry.ModStatusEffects;
import net.minecraft.container.PlayerContainer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
			// Check enter spiritWorld
			if (!nausea || getStatusEffect(StatusEffects.NAUSEA).getDuration() <= 3*20) {
				if (!dimension.equals(ModDimensions.spiritWorld)) {
					changeDimension(ModDimensions.spiritWorld);
				}
			}

			if (!nausea && getStatusEffect(ModStatusEffects.illusionEffect).getDuration() < 3*20)
				addPotionEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 6*20));
		}
		else {
			// Check exit dimension
			if (dimension.equals(ModDimensions.spiritWorld)) {
//				CompoundTag tag = PlayerData.get((playerContainer.owner, EphemeralWorlds.MODID);
				// todo send player back to previous dimension
				changeDimension(DimensionType.OVERWORLD);
			}
		}
	}
}
