@file:JvmName("Utils")

package io.github.justfoxx.korigins.api

import com.electronwill.nightconfig.core.conversion.ObjectConverter
import io.github.apace100.origins.Origins
import io.github.apace100.origins.origin.OriginLayers
import io.github.justfoxx.korigins.MOD_NAME
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory

val logger = LoggerFactory.getLogger(MOD_NAME)!!
val converter = ObjectConverter()
val container = FabricLoader.getInstance().getModContainer(MOD_NAME).get()

val powers: Map<Identifier, PowerBase<*>> = mapOf()
val origins: Map<Identifier, OriginBase> = mapOf()
val layer = OriginLayers.getLayer(Origins.identifier("origin"))!!
val PlayerEntity.origins: List<Identifier>
    get() = layer.getOrigins(this)

val String.id: Identifier
    get() = Identifier(MOD_NAME, this)

typealias IsDisabled<T> = (T) -> Boolean

fun <A, B : IsDisabled<B>> Map<A, B>.filterDisabled(): Map<A, B> = filterNot { it.value(it.value) }

fun <A : IsDisabled<A>> List<A>.filterDisabled(): List<A> = filterNot { it(it) }

fun <A : IsDisabled<A>> disabledListOf(vararg values: A): List<A> = listOf(*values).filterDisabled()

fun <A, B : IsDisabled<B>> disabledMapOf(vararg pairs: Pair<A, B>): Map<A, B> =
    mapOf(*pairs).filterDisabled()
