package io.github.justfoxx.korigins;

import lombok.val;
import net.fabricmc.api.ModInitializer;

public final class Main implements ModInitializer {
    @Override
    public void onInitialize() {
        GlobalUtils.logger.info("\uD83D\uDC31");
        val config = GlobalUtils.config;
        GlobalUtils.logger.info("Loaded config: " + config);
        if (config.getVentur().isDisabled()) {
            GlobalUtils.logger.info("Ventur is disabled");
        } else {
            GlobalUtils.logger.info("Ventur is enabled");
        }
    }
}
