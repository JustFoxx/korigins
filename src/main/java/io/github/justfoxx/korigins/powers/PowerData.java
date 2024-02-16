package io.github.justfoxx.korigins.powers;

import io.github.justfoxx.korigins.IsDisabled;
import lombok.Data;
import net.minecraft.util.Identifier;

@Data
public final class PowerData<T> implements IsDisabled {
    private final T data;
    private final PowerKey<T> powerKey;
    private final boolean isDisabled;

    private PowerData(PowerKey<T> powerKey, T data, IsDisabled isDisabled) {
        this.data = data;
        this.powerKey = powerKey;
        this.isDisabled = isDisabled.isDisabled();
    }

    private PowerData(Identifier powerId, boolean isDisabled) {
        this.isDisabled = isDisabled;
        this.data = null;
        this.powerKey = new PowerKey<>(powerId);
    }

    public static <T> PowerData<T> custom(PowerKey<T> powerKey, T data, IsDisabled isDisabled) {
        return new PowerData<>(powerKey, data, isDisabled);
    }

    public static PowerData<Void> empty(PowerKey<Void> powerKey) {
        return new PowerData<>(powerKey, null, () -> false);
    }

    public static PowerData<?> nonCustom(Identifier powerId) {
        return new PowerData<>(powerId, false);
    }

    @Override
    public boolean isDisabled() {
        return isDisabled;
    }
}
