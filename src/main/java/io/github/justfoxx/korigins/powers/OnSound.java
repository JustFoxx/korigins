package io.github.justfoxx.korigins.powers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvent;

public interface OnSound {
    SoundEvent deathSound();

    LivingEntity.FallSounds fallSound();

    SoundEvent hurtSound();

    SoundEvent eatSound();

    SoundEvent dropSound();

    SoundEvent sleepSound();
}
