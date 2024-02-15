package io.github.justfoxx.korigins.origins;

import io.github.apace100.apoli.power.PowerTypeReference;
import io.github.apace100.origins.integration.OriginDataLoadedCallback;
import io.github.apace100.origins.origin.Impact;
import io.github.apace100.origins.origin.Origin;
import io.github.apace100.origins.origin.OriginLayer;
import io.github.apace100.origins.origin.OriginRegistry;
import io.github.justfoxx.korigins.GlobalUtils;
import io.github.justfoxx.korigins.mixins.OriginLayerAccessor;
import io.github.justfoxx.korigins.powers.CustomPower;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.val;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@ToString
@EqualsAndHashCode
public abstract class CustomOrigin {
    public static Map<Identifier, CustomOrigin> origins =
            Map.ofEntries(Map.entry(GlobalUtils.id("ventur"), VenturOrigin.INSTANCE));

    public static void registerAll() {
        for (val origin : origins.values()) {
            origin.register();
        }
    }

    private final Identifier id;
    private final HashMap<Identifier, ?> powers;
    private final Origin origin;

    protected CustomOrigin(
            Identifier id,
            List<CustomPower.PowerData<?>> powers,
            ItemStack visualItem,
            Impact impact,
            int order,
            int priority) {
        this.id = id;
        this.powers = powers.stream().collect(HashMap::new, (m, p) -> m.put(p.getId(), p.getData()), HashMap::putAll);
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
        for (val id : powers.keySet()) {
            this.origin.add(new PowerTypeReference<>(id));
        }
    }

    public final void register() {
        registerPowers();
        OriginDataLoadedCallback.EVENT.register(this::originDataLoaded);
    }

    public final OriginLayer getLayer() {
        return GlobalUtils.getLayer();
    }

    public final boolean isActive(PlayerEntity player) {
        return getLayer().getOrigins(player).contains(this.id);
    }

    public final <T> T getPowerData(Identifier id) {
        return (T) powers.get(id);
    }
}
