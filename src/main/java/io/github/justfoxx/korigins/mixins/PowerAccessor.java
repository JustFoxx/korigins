package io.github.justfoxx.korigins.mixins;

import io.github.apace100.apoli.power.Power;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Power.class)
public interface PowerAccessor {
    @Accessor
    LivingEntity getEntity();
}
