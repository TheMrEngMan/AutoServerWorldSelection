package com.mrengman.autoserverworldselection.mixins;

import com.mrengman.autoserverworldselection.AutoServerWorldSelection;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xaero.map.MapProcessor;

@Mixin(MapProcessor.class)
public abstract class MixinMapProcessor {

    @Inject(at = @At("TAIL"), method = "getAutoIdBase", cancellable = true)
    public void getAutoIdBase(ClientWorld world, CallbackInfoReturnable<Object> cir) {
        if(!AutoServerWorldSelection.enabled) return;
        Integer seedHash = (int) ((BiomeAccessAccessor) world.getBiomeAccess()).getSeed();
        cir.setReturnValue(seedHash);
    }

    @Inject(at = @At("TAIL"), method = "hasServerLevelId", cancellable = true, remap = false)
    public void getAutoIdBase(CallbackInfoReturnable<Boolean> cir) {
        if(!AutoServerWorldSelection.enabled) return;
        cir.setReturnValue(true);
    }

}
