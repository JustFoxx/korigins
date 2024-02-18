/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 *
 * <one line to give the program's name and a brief idea of what it does.>
 * Copyright (C) 2024 kittech
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 */

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
        this.powers = IsDisabled.convertList(powers).stream()
                .collect(HashMap::new, (m, p) -> m.put(p.getPowerKey(), p.getData()), HashMap::putAll);
        this.origin = new Origin(id, visualItem, impact, order, priority);
    }

    public static Map<Identifier, CustomOrigin> registerAll(Map<Identifier, CustomOrigin> origins) {
        val map = IsDisabled.convertMapB(origins);
        map.forEach((key, value) -> value.register());
        return map;
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
