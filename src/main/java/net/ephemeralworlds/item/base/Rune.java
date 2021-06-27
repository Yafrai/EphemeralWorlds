package net.ephemeralworlds.item.base;

import net.ephemeralworlds.item.base.ModItem;
import net.ephemeralworlds.item.base.SoulTool;
import net.ephemeralworlds.utils.enums.EnumResourceType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtil;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.UseAction;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

import java.util.List;

public abstract class Rune extends ModItem {
    public Rune(String uname) {
        super(uname);
    }

    public abstract boolean applyToTarget(LivingEntity entity);
    public abstract int[] getCost();

//    public UseAction getUseAction(ItemStack stack) {
//        return UseAction.SPEAR;
//    }
//
//    public int getMaxUseTime(ItemStack stack) {
//        return 32;
//    }

    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity target, Hand hand) {

        if (!affordable(user))
            return ActionResult.FAIL;

        applyToTarget(target);
        applyCost(user);

        return ActionResult.SUCCESS;
    }

    public boolean affordable(LivingEntity user) {
        // todo
        getCost();
        return true;
    }

    public void applyCost(LivingEntity user) {
        int[] cost = getCost();
        // todo
    }


}
