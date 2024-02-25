package io.github.justfoxx.korigins.mixins;

import lombok.val;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public final class ServerPlayerEntityMixin {
    @Inject(method = "tick", at = @At("RETURN"))
    public void tick(final CallbackInfo ci) {
        val player = (ServerPlayerEntity) (Object) this;
        for (val power : Vars.powers) {
            if (!power.isActive(player)) continue;
            power.onTick(player);
        }
    }
}
