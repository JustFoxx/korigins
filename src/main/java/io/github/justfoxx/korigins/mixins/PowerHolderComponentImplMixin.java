package io.github.justfoxx.korigins.mixins;

import io.github.apace100.apoli.component.PowerHolderComponentImpl;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.justfoxx.korigins.Vars;
import lombok.val;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowerHolderComponentImpl.class)
public class PowerHolderComponentImplMixin {
    @Shadow
    @Final
    private LivingEntity owner;

    @Inject(method = "removePower", at = @At("RETURN"))
    public void removePower(final PowerType<Power> powerType, final Identifier source, final CallbackInfo ci) {
        if (!(this.owner instanceof ServerPlayerEntity player)) return;

        val powerId = powerType.getIdentifier();

        for (val power : Vars.powers) {
            if (!power.getId().equals(powerId)) continue;

            power.onRemove(powerType, player);
        }
    }

    @Inject(method = "addPower", at = @At("RETURN"))
    public void addPower(
            final PowerType<Power> powerType, final Identifier source, final CallbackInfoReturnable<Boolean> cir) {
        if (!(this.owner instanceof ServerPlayerEntity player)) return;

        val powerId = powerType.getIdentifier();

        for (val power : Vars.powers) {
            if (!power.getId().equals(powerId)) continue;

            power.onAdd(powerType, player);
        }
    }
}
