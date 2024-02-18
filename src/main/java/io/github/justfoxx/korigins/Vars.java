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

package io.github.justfoxx.korigins;

import com.electronwill.nightconfig.core.conversion.ObjectConverter;
import io.github.justfoxx.korigins.config.Config;
import io.github.justfoxx.korigins.config.ConfigData;
import io.github.justfoxx.korigins.origins.CustomOrigin;
import io.github.justfoxx.korigins.origins.VenturOrigin;
import io.github.justfoxx.korigins.powers.ChangeSize;
import io.github.justfoxx.korigins.powers.CustomPower;
import io.github.justfoxx.korigins.powers.Sounds;
import lombok.experimental.UtilityClass;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@UtilityClass
public final class Vars {
    public final String MOD_NAME = "korigins";
    public final Logger logger;
    public final ObjectConverter converter;
    public final ConfigData config;
    public final ModContainer container;
    public final List<CustomPower<?>> powers;
    public final Map<Identifier, CustomOrigin> origins;

    static {
        logger = LoggerFactory.getLogger(Vars.MOD_NAME);
        converter = new ObjectConverter();
        container = FabricLoader.getInstance().getModContainer(Vars.MOD_NAME).get();
        config = new Config(Vars.MOD_NAME, container).load(converter, ConfigData::new);
        powers = List.of(new ChangeSize(), new Sounds());
        origins = CustomOrigin.registerAll(Map.ofEntries(Map.entry(Utils.id("ventur"), VenturOrigin.INSTANCE)));
    }
}
