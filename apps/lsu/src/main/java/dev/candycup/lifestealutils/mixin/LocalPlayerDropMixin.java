package dev.candycup.lifestealutils.mixin;

import dev.candycup.lifestealutils.features.items.RareItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//? if >26.2 {
/*import net.minecraft.client.multiplayer.MultiPlayerGameMode;
*///? }

//? if <26.3 {
@Mixin(LocalPlayer.class)
public class LocalPlayerDropMixin {

    @Inject(method = "drop", at = @At("HEAD"), cancellable = true)
    private void confirmRareItemDrop(boolean dropStack, CallbackInfoReturnable<Boolean> cir) {
        LocalPlayer player = (LocalPlayer) (Object) this;

        if (Minecraft.getInstance().screen == null
                && RareItems.shouldBlockWorldDrop(player.getMainHandItem())) {
            cir.setReturnValue(false);
        }
    }
}
//?} else {
/*@Mixin(MultiPlayerGameMode.class)
public class LocalPlayerDropMixin {

    @Inject(method = "dropItem", at = @At("HEAD"), cancellable = true)
    private void confirmRareItemDrop(LocalPlayer player, boolean all, CallbackInfo ci) {
        if (Minecraft.getInstance().screen == null
                && RareItems.shouldBlockWorldDrop(player.getMainHandItem())) {
            ci.cancel();
        }
    }
}
*///?}