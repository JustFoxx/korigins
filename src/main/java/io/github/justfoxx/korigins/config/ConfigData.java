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

package io.github.justfoxx.korigins.config;

import com.electronwill.nightconfig.core.conversion.Conversion;
import com.electronwill.nightconfig.core.conversion.Converter;
import com.electronwill.nightconfig.core.conversion.Path;
import com.electronwill.nightconfig.core.conversion.SpecDoubleInRange;
import io.github.apace100.origins.origin.Impact;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

@Getter
@ToString
@EqualsAndHashCode
public final class ConfigData {
    @Path("ventur")
    private VenturConfig ventur;

    @Getter
    @ToString
    @EqualsAndHashCode
    public static final class VenturConfig {
        @Path("disabled")
        private boolean disabled;

        @Path("change_size")
        private ChangeSize changeSize;

        @Path("impact")
        @Conversion(ImpactToInteger.class)
        private Impact impact;

        @Path("visual_item")
        @Conversion(ItemToString.class)
        private Item visualItem;

        private VenturConfig() {}
    }

    @Getter
    @ToString
    @EqualsAndHashCode
    public static final class ChangeSize {
        @Path("disabled")
        private boolean disabled;

        @Path("base_scale")
        @SpecDoubleInRange(min = 0.0, max = Float.MAX_VALUE)
        private double baseScale;

        @Path("reach_scale")
        @SpecDoubleInRange(min = 0.0, max = Float.MAX_VALUE)
        private double reachScale;

        @Path("motion_scale")
        @SpecDoubleInRange(min = 0.0, max = Float.MAX_VALUE)
        private double motionScale;

        private ChangeSize() {}
    }

    static class ItemToString implements Converter<Item, String> {
        @Override
        public Item convertToField(final String value) {
            return Registries.ITEM.get(Identifier.tryParse(value));
        }

        @Override
        public String convertFromField(final Item value) {
            return Registries.ITEM.getId(value).toString();
        }
    }

    static class ImpactToInteger implements Converter<Impact, Integer> {
        @Override
        public Impact convertToField(Integer value) {
            return Impact.getByValue(value);
        }

        @Override
        public Integer convertFromField(Impact value) {
            return value.getImpactValue();
        }
    }
}
