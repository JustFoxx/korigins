package io.github.justfoxx.korigins.powers;

import lombok.Data;
import net.minecraft.util.Identifier;

@Data
public final class PowerData<T> {
    private final T data;
    private final PowerKey<T> powerKey;

    private PowerData(PowerKey<T> powerKey, T data) {
        this.data = data;
        this.powerKey = powerKey;
    }

    private PowerData(Identifier powerId) {
        this.data = null;
        this.powerKey = new PowerKey<>(powerId);
    }

    public static <T> PowerData<T> custom(PowerKey<T> powerKey, T data) {
        return new PowerData<>(powerKey, data);
    }

    public static PowerData<Void> empty(PowerKey<Void> powerKey) {
        return new PowerData<>(powerKey, null);
    }

    public static PowerData<?> nonCustom(Identifier powerId) {
        return new PowerData<>(powerId);
    }
}
