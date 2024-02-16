package io.github.justfoxx.korigins;

import com.electronwill.nightconfig.core.conversion.ObjectConverter;
import io.github.justfoxx.korigins.config.Config;
import io.github.justfoxx.korigins.config.ConfigData;
import io.github.justfoxx.korigins.origins.CustomOrigin;
import io.github.justfoxx.korigins.origins.VenturOrigin;
import io.github.justfoxx.korigins.powers.ChangeSize;
import io.github.justfoxx.korigins.powers.CustomPower;
import java.util.List;
import java.util.Map;
import lombok.experimental.UtilityClass;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@UtilityClass
public final class Vars {
    public final String MOD_NAME = "korigins";
    public final Logger logger;
    public final ObjectConverter converter;
    public final ConfigData config;
    public final ModContainer container;
    public final List<CustomPower<?>> powers;
    public final Map<Identifier, CustomOrigin> origins;

    static {
        logger = LoggerFactory.getLogger(Vars.MOD_NAME);
        converter = new ObjectConverter();
        container = FabricLoader.getInstance().getModContainer(Vars.MOD_NAME).get();
        config = new Config(Vars.MOD_NAME, container).load(converter, ConfigData::new);
        origins = Map.ofEntries(Map.entry(Utils.id("ventur"), VenturOrigin.INSTANCE));
        powers = List.of(new ChangeSize());
    }
}
