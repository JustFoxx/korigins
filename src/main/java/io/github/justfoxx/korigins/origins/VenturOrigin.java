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

import io.github.apace100.origins.Origins;
import io.github.apace100.origins.origin.Impact;
import io.github.justfoxx.korigins.Utils;
import io.github.justfoxx.korigins.Vars;
import io.github.justfoxx.korigins.powers.ChangeSize;
import io.github.justfoxx.korigins.powers.PowerData;
import lombok.val;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.List;

public final class VenturOrigin extends CustomOrigin {
    public static final VenturOrigin INSTANCE;

    static {
        val config = Vars.config.getVentur();
        val item = Registries.ITEM.get(Utils.id("ventur")).getDefaultStack();
        val id = Utils.id("ventur");
        val power = List.of(
                PowerData.nonCustom(Origins.identifier("climbing")),
                PowerData.custom(
                        ChangeSize.key,
                        new ChangeSize.ChangeSizeData(
                                (float) config.getChangeSize().getBaseScale(),
                                (float) config.getChangeSize().getReachScale(),
                                (float) config.getChangeSize().getMotionScale()),
                        () -> config.getChangeSize().isDisabled()));

        INSTANCE = new VenturOrigin(id, power, item, Impact.NONE, 0, 0);
    }

    private VenturOrigin(
            final Identifier id,
            final List<PowerData<?>> powers,
            final ItemStack visualItem,
            final Impact impact,
            final int order,
            final int priority) {
        super(id, powers, visualItem, impact, order, priority);
    }

    @Override
    public boolean isDisabled() {
        return Vars.config.getVentur().isDisabled();
    }
}
