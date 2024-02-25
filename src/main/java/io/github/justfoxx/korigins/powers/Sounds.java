/*
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

public final class Sounds extends CustomPower<Sounds.SoundData> implements OnSound {
    public static final PowerKey<SoundData> key = new PowerKey<>(Utils.id("sounds"));

    public Sounds() {
        super(Utils.id("sounds"));
    }

    @Override
    public PowerKey<SoundData> getKey() {
        return key;
    }

    @Override
    public SoundEvent deathSound(ServerPlayerEntity player) {

        return null;
    }

    @Override
    public LivingEntity.FallSounds fallSound(ServerPlayerEntity player) {
        return null;
    }

    @Override
    public SoundEvent hurtSound(ServerPlayerEntity player) {
        return null;
    }

    @Override
    public SoundEvent eatSound(ServerPlayerEntity player) {
        return null;
    }

    @Override
    public SoundEvent dropSound(ServerPlayerEntity player) {
        return null;
    }

    @Override
    public SoundEvent sleepSound(ServerPlayerEntity player) {
        return null;
    }

    public record SoundData(
            SoundEvent deathSound,
            LivingEntity.FallSounds fallSound,
            SoundEvent hurtSound,
            SoundEvent eatSound,
            SoundEvent dropSound,
            SoundEvent sleepSound) {}
}
