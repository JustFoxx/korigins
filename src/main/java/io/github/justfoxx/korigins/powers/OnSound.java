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

package io.github.justfoxx.korigins.powers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;

public interface OnSound {
    SoundEvent deathSound(ServerPlayerEntity player);

    LivingEntity.FallSounds fallSound(ServerPlayerEntity player);

    SoundEvent hurtSound(ServerPlayerEntity player);

    SoundEvent eatSound(ServerPlayerEntity player);

    SoundEvent dropSound(ServerPlayerEntity player);

    SoundEvent sleepSound(ServerPlayerEntity player);
}
