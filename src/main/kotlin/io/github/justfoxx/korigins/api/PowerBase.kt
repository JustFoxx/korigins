package io.github.justfoxx.korigins.api

import com.jetbrains.rd.util.ConcurrentHashMap
import io.github.apace100.apoli.component.PowerHolderComponent
import io.github.apace100.apoli.power.Power
import io.github.apace100.apoli.power.PowerTypeReference
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.Identifier

interface PowerBase<T> : IsDisabled<PowerBase<T>> {
    val id: Identifier
}

private val powerTypeCache = ConcurrentHashMap<Identifier, PowerTypeReference<Power>>()

fun <T> PowerBase<T>.getFirstOrigin(player: PlayerEntity): OriginBase? =
    player.origins.mapNotNull(origins::get).firstOrNull { it.powers.contains(this.id) }

val PlayerEntity.powerHolder: PowerHolderComponent
    get() = PowerHolderComponent.KEY[this]

val <T> PowerBase<T>.powerTypeReference: PowerTypeReference<Power>
    get() {
        if (!powerTypeCache.contains(this.id)) {
            powerTypeCache[this.id] = PowerTypeReference(this.id)
        }
        return powerTypeCache[this.id]!!
    }

fun <T> PowerBase<T>.isActive(player: PlayerEntity): Boolean = powerTypeReference.isActive(player)
