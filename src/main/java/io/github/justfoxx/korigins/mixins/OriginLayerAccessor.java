package io.github.justfoxx.korigins.mixins;

import io.github.apace100.origins.origin.OriginLayer;
import java.util.List;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(OriginLayer.class)
public interface OriginLayerAccessor {
    @Accessor
    List<OriginLayer.ConditionedOrigin> getConditionedOrigins();
}
