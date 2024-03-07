package io.github.justfoxx.korigins.api

import io.github.apace100.origins.origin.Origin
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.Identifier

interface OriginBase : IsDisabled<OriginBase> {
    val id: Identifier
    val powers: Map<Identifier, *>
    val origin: Origin
}

fun OriginBase.isActive(player: PlayerEntity): Boolean = layer.getOrigins(player).contains(this.id)

inline fun <reified T : Any> OriginBase.getPowerData(power: Identifier): T? {
    val powerValue = powers[power]
    return if (powerValue is T) powerValue else null
}
