package io.github.justfoxx.korigins.powers;

import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.PowerTypeReference;
import io.github.apace100.apoli.power.PowerTypeRegistry;
import io.github.justfoxx.korigins.Utils;
import io.github.justfoxx.korigins.Vars;
import io.github.justfoxx.korigins.origins.CustomOrigin;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.val;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

@Getter
@ToString
@EqualsAndHashCode
public abstract class CustomPower<T> {
    private final Identifier id;
    private final PowerTypeReference<Power> powerTypeReference;
    private PowerType<?> powerType;
    private PowerHolderComponent powerHolderComponent;

    protected CustomPower(Identifier id) {
        this.id = id;
        this.powerTypeReference = new PowerTypeReference<>(this.id);
    }

    public final T getData(CustomOrigin origin) {
        return origin.getPowerData(getKey());
    }

    public final List<CustomOrigin> getOrigins(ServerPlayerEntity player) {
        val origins = Utils.getPlayerOrigins(player);
        return origins.stream()
                .filter(Vars.origins::containsKey)
                .map(Vars.origins::get)
                .toList();
    }

    public final boolean isActive(ServerPlayerEntity player) {
        return powerTypeReference.isActive(player);
    }

    public final PowerHolderComponent getPowerHolder(ServerPlayerEntity entity) {
        if (this.powerHolderComponent == null) {
            this.powerHolderComponent = PowerHolderComponent.KEY.get(entity);
        }
        return this.powerHolderComponent;
    }

    public final Power getPower(ServerPlayerEntity entity) {
        if (this.powerType == null) {
            this.powerType = PowerTypeRegistry.get(id);
        }
        return getPowerHolder(entity).getPower(this.powerType);
    }

    public void onRemove(PowerType<Power> power, ServerPlayerEntity player) {}

    public void onAdd(PowerType<Power> power, ServerPlayerEntity player) {}

    public void onTick(ServerPlayerEntity player) {}

    public abstract PowerKey<T> getKey();
}
