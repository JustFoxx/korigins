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

package io.github.justfoxx.korigins.mixins;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.justfoxx.korigins.powers.OnSound;
import lombok.val;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(final EntityType<? extends LivingEntity> entityType, final World world) {
        super(entityType, world);
    }

    @Unique
    private PlayerEntity getEntity() {
        return (PlayerEntity) (Object) this;
    }

    @ModifyReturnValue(method = "getDeathSound", at = @At("RETURN"))
    public SoundEvent deathSound(final SoundEvent original) {
        if (!(getEntity() instanceof ServerPlayerEntity player)) return original;
        SoundEvent sound = original;
        for (val power : Vars.powers) {
            if (!(power instanceof OnSound onSound)) continue;
            if (!power.isActive(player)) continue;
            sound = onSound.deathSound(player);
        }
        return sound;
    }

    @ModifyReturnValue(method = "getFallSounds", at = @At("TAIL"))
    public FallSounds fallSound(final FallSounds original) {
        if (!(getEntity() instanceof ServerPlayerEntity player)) return original;
        var sound = original;
        for (val power : Vars.powers) {
            if (!(power instanceof OnSound onSound)) continue;
            if (!power.isActive(player)) continue;
            sound = onSound.fallSound(player);
        }
        return sound;
    }

    @ModifyReturnValue(method = "getHurtSound", at = @At("RETURN"))
    public SoundEvent hurtSound(final SoundEvent original) {
        if (!(getEntity() instanceof ServerPlayerEntity player)) return original;
        var sound = original;
        for (val power : Vars.powers) {
            if (!(power instanceof OnSound onSound)) continue;
            if (!power.isActive(player)) continue;
            sound = onSound.hurtSound(player);
        }
        return sound;
    }

    @ModifyArg(
            method = "eatFood",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/world/World;playSound(Lnet/minecraft/entity/player/PlayerEntity;DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V"))
    public SoundEvent eatSound(final SoundEvent sound) {
        var soundEvent = sound;
        if (!(getEntity() instanceof ServerPlayerEntity player)) return soundEvent;
        for (val power : Vars.powers) {
            if (!(power instanceof OnSound onSound)) continue;
            if (!power.isActive(player)) continue;
            soundEvent = onSound.eatSound(player);
        }
        return soundEvent;
    }

    @Inject(method = "dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;", at = @At("TAIL"))
    public void dropSound(
            final ItemStack stack,
            final boolean throwRandomly,
            final boolean retainOwnership,
            final CallbackInfoReturnable<ItemEntity> cir) {
        SoundEvent sound = null;
        if (!(getEntity() instanceof ServerPlayerEntity player)) return;
        for (val power : Vars.powers) {
            if (!(power instanceof OnSound onSound)) continue;
            if (!power.isActive(player)) continue;
            sound = onSound.dropSound(player);
        }
        if (sound == null) return;
        getWorld().playSound(player, this.getX(), this.getY(), this.getZ(), sound, SoundCategory.PLAYERS, 1.0F, 1.0F);
    }
}
