package net.ephemeralworlds.item.base;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Test extends ModItem {
    public Test(String uname) {
        super(uname);
    }

//    @Override
//    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
//        if (!world.isClient) {
//            ServerWorld serverWorld = (ServerWorld) world;
//
////            serverWorld.getPersistentStateManager().getOrCreate();
//
//        }
//
//    }

//    this.world.getTagManager()

}
