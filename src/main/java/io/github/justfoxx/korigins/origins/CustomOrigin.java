package io.github.justfoxx.korigins.origins;

import io.github.apace100.apoli.power.PowerTypeReference;
import io.github.apace100.origins.integration.OriginDataLoadedCallback;
import io.github.apace100.origins.origin.Impact;
import io.github.apace100.origins.origin.Origin;
import io.github.apace100.origins.origin.OriginLayer;
import io.github.apace100.origins.origin.OriginRegistry;
import io.github.justfoxx.korigins.IsDisabled;
import io.github.justfoxx.korigins.Utils;
import io.github.justfoxx.korigins.mixins.OriginLayerAccessor;
import io.github.justfoxx.korigins.powers.PowerData;
import io.github.justfoxx.korigins.powers.PowerKey;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.val;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@ToString
@EqualsAndHashCode
public abstract class CustomOrigin implements IsDisabled {
    public static Map<Identifier, CustomOrigin> registerAll(Map<Identifier, CustomOrigin> origins) {
        return IsDisabled.convertMapB(origins);
    }

    private final Identifier id;
    private final HashMap<PowerKey<?>, ?> powers;
    private final Origin origin;

    protected CustomOrigin(
            Identifier id,
            @NotNull List<PowerData<?>> powers,
            ItemStack visualItem,
            Impact impact,
            int order,
            int priority) {
        this.id = id;
        this.powers =
                IsDisabled.convertList(powers).stream().collect(HashMap::new, (m, p) -> m.put(p.getPowerKey(), p.getData()), HashMap::putAll);
        this.origin = new Origin(id, visualItem, impact, order, priority);
    }

    private void originDataLoaded(boolean client) {
        if (client) return;
        OriginRegistry.register(this.origin);
        val layer = (OriginLayerAccessor) getLayer();
        val conditioned = new OriginLayer.ConditionedOrigin(null, List.of(this.id));
        layer.getConditionedOrigins().add(conditioned);
    }

    private void registerPowers() {
        for (val powerKey : powers.keySet()) {
            this.origin.add(new PowerTypeReference<>(powerKey.id()));
        }
    }

    private CustomOrigin register() {
        registerPowers();
        OriginDataLoadedCallback.EVENT.register(this::originDataLoaded);
        return this;
    }

    public final OriginLayer getLayer() {
        return Utils.getLayer();
    }

    public final boolean isActive(PlayerEntity player) {
        return getLayer().getOrigins(player).contains(this.id);
    }

    public final <T> T getPowerData(PowerKey<T> id) {
        return (T) powers.get(id);
    }
}
