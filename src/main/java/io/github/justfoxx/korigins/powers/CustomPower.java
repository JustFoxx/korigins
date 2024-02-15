package io.github.justfoxx.korigins.powers;

import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.PowerTypeReference;
import io.github.apace100.apoli.power.PowerTypeRegistry;
import io.github.justfoxx.korigins.GlobalUtils;
import io.github.justfoxx.korigins.origins.CustomOrigin;
import lombok.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public abstract class CustomPower<T> {
    public static List<CustomPower<?>> powers = List.of();

    private final Identifier id;
    private final PowerTypeReference<Power> powerTypeReference;
    private PowerType<?> powerType;
    private PowerHolderComponent powerHolderComponent;

    protected CustomPower(Identifier id) {
        this.id = id;
        this.powerTypeReference = new PowerTypeReference<>(this.id);
    }

    public final T getData(CustomOrigin origin) {
        return origin.getPowerData(id);
    }

    public final List<CustomOrigin> getOrigins(PlayerEntity player) {
        val origins = GlobalUtils.getPlayerOrigins(player);
        return origins.stream()
                .filter(CustomOrigin.origins::containsKey)
                .map(CustomOrigin.origins::get)
                .toList();
    }

    public final boolean isActive(PlayerEntity player) {
        return powerTypeReference.isActive(player);
    }

    public PowerHolderComponent getPowerHolder(PlayerEntity entity) {
        if (this.powerHolderComponent == null) {
            this.powerHolderComponent = PowerHolderComponent.KEY.get(entity);
        }
        return this.powerHolderComponent;
    }

    public Power getPower(PlayerEntity entity) {
        if (this.powerType == null) {
            this.powerType = PowerTypeRegistry.get(id);
        }
        return getPowerHolder(entity).getPower(this.powerType);
    }

    @Data
    public static final class PowerData<T> {
        private final T data;
        private final Identifier id;

        public PowerData(CustomPower<T> ignoredPower, T data) {
            this.data = data;
            this.id = ignoredPower.id;
        }

        private PowerData(Identifier powerId) {
            this.data = null;
            this.id = powerId;
        }

        public static PowerData<Void> empty(CustomPower<Void> power) {
            return new PowerData<>(power, null);
        }

        public static PowerData<Void> nonCustom(Identifier powerId) {
            return new PowerData<>(powerId);
        }
    }
}
