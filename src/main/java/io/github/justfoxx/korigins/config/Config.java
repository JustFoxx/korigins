package io.github.justfoxx.korigins.config;

import com.electronwill.nightconfig.core.conversion.ObjectConverter;
import com.electronwill.nightconfig.core.file.FileConfig;
import lombok.val;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

import java.util.function.Supplier;

public final class Config {
    private final FileConfig config;

    public Config(String name, ModContainer container) {
        val file = FabricLoader.getInstance().getConfigDir().resolve(name + ".toml");
        val defaultConfig = container.getRootPaths().get(0).resolve("default_config.toml");
        this.config = FileConfig.builder(file)
                .autoreload()
                .concurrent()
                .defaultData(defaultConfig)
                .build();
    }

    public <T> T load(ObjectConverter converter, Supplier<T> supplier) {
        this.config.load();
        converter.toObject(config, supplier);
        return converter.toObject(config, supplier);
    }
}
