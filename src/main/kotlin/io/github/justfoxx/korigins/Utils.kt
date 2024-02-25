package io.github.justfoxx.korigins

import com.electronwill.nightconfig.core.conversion.ObjectConverter
import io.github.apace100.origins.Origins
import io.github.apace100.origins.origin.OriginLayers
import io.github.justfoxx.korigins.origins.CustomOrigin
import io.github.justfoxx.korigins.powers.ChangeSize
import io.github.justfoxx.korigins.powers.CustomPower
import io.github.justfoxx.korigins.powers.Sounds
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory

val logger = LoggerFactory.getLogger(MOD_NAME)!!
val converter = ObjectConverter()
val container = FabricLoader.getInstance().getModContainer(MOD_NAME).get()
val powers = listOf<CustomPower<*>>(ChangeSize(), Sounds())
val origins: Map<Identifier, CustomOrigin> = null!!
val layer = OriginLayers.getLayer(Origins.identifier("origin"))!!

fun String.id() = Identifier(MOD_NAME, this)
fun PlayerEntity.origins(): List<Identifier> = layer.getOrigins(this)

typealias IsDisabled<T> = (T) -> Boolean

fun <A, B : IsDisabled<B>> Map<A, B>.filterDisabled() = filterNot { it.value(it.value) }
fun <A : IsDisabled<A>> List<A>.filterDisabled() = filterNot { it(it) }