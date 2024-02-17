package io.github.justfoxx.korigins;

import io.github.apace100.origins.Origins;
import io.github.apace100.origins.origin.OriginLayer;
import io.github.apace100.origins.origin.OriginLayers;
import lombok.experimental.UtilityClass;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import java.util.List;

@UtilityClass
public final class Utils {
    public Identifier id(final String name) {
        return new Identifier(Vars.MOD_NAME, name);
    }

    public OriginLayer getLayer() {
        return OriginLayers.getLayer(Origins.identifier("origin"));
    }

    public List<Identifier> getPlayerOrigins(PlayerEntity player) {
        return getLayer().getOrigins(player);
    }
}
