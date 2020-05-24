package net.ephemeralworlds.block.entity;


//public class EaselBlockEntity extends ModInventoryBlockEntity {
//
//    public EaselBlockEntity() {
//        super(ModBlockEntities.EASEL_ENTITY, 6);
//    }
//
//    public boolean interact(PlayerEntity player) {
//        if (player.isSneaking()) {
//            return false;
//        }
//
//        ItemStack stack = player.getMainHandStack();
//        if (stack.isEmpty()) {
//            ItemStack retrieved = retrieveItem();
//            if (!retrieved.isEmpty() && !player.world.isClient) {
//                PlayerHelper.givePlayerOrDrop(player, retrieved);
//                saveAndNotify();
//            }
//            return !retrieved.isEmpty();
//        }
//
//        if (stack.getItem() instanceof Brush) {
//            Brush brush = (Brush)stack.getItem();
//            Optional<EaselRecipe> recipe = world.getRecipeManager().getFirstMatch(EaselRecipe.TYPE, this, world);
//
//            if (recipe.isPresent() && brush.getTagInkAmount(stack) >= 1 && recipe.get().colorFits(brush.getTagColor(stack))) {
//                inventory.clear();
//                inventory.set(0, recipe.get().getActualOutput(brush.getTagColor(stack)).copy());
//                brush.wipeInk(stack);
//                return true;
//            }
//
//            return false;
//        }
//
//        return addItem(player, stack);
//    }
//
//    public boolean addItem(PlayerEntity player, ItemStack stack) {
//        for (int i=0;i<inventory.size(); i++) {
//            ItemStack invStack = inventory.get(i);
//            if (invStack.isEmpty()) {
//                if (!player.world.isClient) {
//                    inventory.set(i, stack.split(1));
//                    saveAndNotify();
//                }
//
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public ItemStack retrieveItem() {
//        for (int i=inventory.size()-1; i>=0; i--) {
//            ItemStack invStack = inventory.get(i);
//            if (!invStack.isEmpty()) {
//                inventory.set(i, ItemStack.EMPTY);
//                return invStack;
//            }
//        }
//        return ItemStack.EMPTY;
//    }
//
//}
