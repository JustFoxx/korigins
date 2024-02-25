package io.github.justfoxx.korigins

import com.electronwill.nightconfig.core.conversion.ObjectConverter
import io.github.apace100.origins.Origins
import io.github.apace100.origins.origin.OriginLayer
import io.github.apace100.origins.origin.OriginLayers
import io.github.justfoxx.korigins.config.Config
import io.github.justfoxx.korigins.config.ConfigData
import io.github.justfoxx.korigins.origins.CustomOrigin
import io.github.justfoxx.korigins.origins.VenturOrigin
import io.github.justfoxx.korigins.powers.ChangeSize
import io.github.justfoxx.korigins.powers.CustomPower
import io.github.justfoxx.korigins.powers.Sounds
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory

const val MOD_NAME = "korigins"
val logger: Logger = LoggerFactory.getLogger(MOD_NAME)
val converter = ObjectConverter()
val container = FabricLoader.getInstance().getModContainer(MOD_NAME).get()
val config = Config(MOD_NAME, container).load(converter) { ConfigData() }
val powers = listOf<CustomPower<*>>(ChangeSize(), Sounds())
val origins: Map<Identifier, CustomOrigin> =
    CustomOrigin.registerAll(mapOf("ventur".id to VenturOrigin.INSTANCE))
val layer: OriginLayer = OriginLayers.getLayer(Origins.identifier("origin"))
val String.id
  get() = Identifier(MOD_NAME, this)
val PlayerEntity.origins: List<Identifier>
  get() = layer.getOrigins(this)

fun main() {
  logger.info("Origins: {}", origins.values)
}
