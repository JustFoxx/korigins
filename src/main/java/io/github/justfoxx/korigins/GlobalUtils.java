package io.github.justfoxx.korigins;

import com.electronwill.nightconfig.core.conversion.ObjectConverter;
import io.github.justfoxx.korigins.config.Config;
import io.github.justfoxx.korigins.config.ConfigData;
import lombok.experimental.UtilityClass;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@UtilityClass
public final class GlobalUtils {
    public final String modName;
    public final Logger logger;
    public final ObjectConverter converter;
    public final ConfigData config;
    public final ModContainer container;

    static {
        modName = "korigins";
        logger = LoggerFactory.getLogger(modName);
        converter = new ObjectConverter();
        container =
                FabricLoader.getInstance().getModContainer(GlobalUtils.modName).get();
        config = new Config(modName, container).load(converter, ConfigData::new);
    }

    public Identifier id(final String name) {
        return new Identifier(modName, name);
    }
}
