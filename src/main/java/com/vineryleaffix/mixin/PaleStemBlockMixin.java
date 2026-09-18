package com.vineryleaffix.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.satisfy.vinery.core.block.PaleStemBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Vinery's PaleStemBlock has TWO independent code paths that can arm the delayed
 * "grow grapevine leaves" timer by setting the LEAVES_PENDING block-state property
 * to true and scheduling a block tick 4800-9600 ticks later:
 *
 *  1. onPlace()   - fires whenever the block state changes (including the stem's
 *                   own age-up), guarded by a GRAPE == WHITE/RED check.
 *  2. useItemOn() - fires immediately when a player right-clicks the stem with
 *                   Grape Seeds. This is the path that actually matters in normal
 *                   play, since it's what runs the instant you plant seeds.
 *
 * Both are neutralized here:
 *  - onPlace(): cancelled right before the setValue(LEAVES_PENDING, true) call, so
 *    nothing after that point (including scheduling the tick) executes.
 *  - useItemOn(): the single BlockState#setValue(...) call in that method is
 *    redirected to a no-op that returns the state unchanged, so LEAVES_PENDING never
 *    becomes true. The surrounding code (placing the aged block state, consuming the
 *    seed item, playing the plant sound, returning SUCCESS) still runs exactly as
 *    before - only the "arm the leaf timer" side effect is removed.
 *
 * Grape growth, harvesting, bonemeal, and everything else about the stem is
 * completely unaffected by either change.
 */
@Mixin(PaleStemBlock.class)
public abstract class PaleStemBlockMixin {

    @Inject(
            method = "onPlace(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Z)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;setValue(Lnet/minecraft/world/level/block/state/properties/Property;Ljava/lang/Comparable;)Ljava/lang/Object;"
            ),
            cancellable = true
    )
    private void vineryleaffix$disableLeafGrowthOnPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving, CallbackInfo ci) {
        ci.cancel();
    }

    @Redirect(
            method = "useItemOn(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/BlockHitResult;)Lnet/minecraft/world/ItemInteractionResult;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;setValue(Lnet/minecraft/world/level/block/state/properties/Property;Ljava/lang/Comparable;)Ljava/lang/Object;"
            )
    )
    private Object vineryleaffix$disableLeafGrowthOnPlant(BlockState state, Property property, Comparable value) {
        // Ignore the assignment entirely - return the state unchanged so
        // LEAVES_PENDING is never flipped to true when seeds are planted.
        return state;
    }
}