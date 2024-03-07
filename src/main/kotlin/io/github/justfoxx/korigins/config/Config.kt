package io.github.justfoxx.korigins.config

import com.electronwill.nightconfig.core.file.FileConfig
import io.github.justfoxx.korigins.MOD_NAME
import io.github.justfoxx.korigins.api.container
import io.github.justfoxx.korigins.api.converter
import net.fabricmc.loader.api.FabricLoader

object Config {
    private val fileConfig: FileConfig
    private var configData: ConfigData? = null

    fun getData(): ConfigData {
        if (configData == null) {
            fileConfig.load()
            configData = converter.toObject(fileConfig, ::ConfigData)
        }
        return configData!!
    }

    init {
        val defaultConfig = container.rootPaths[0].resolve("default_config.toml")
        val path = FabricLoader.getInstance().configDir.resolve("$MOD_NAME.toml")
        fileConfig = FileConfig.builder(path).defaultData(defaultConfig).build()
    }
}
