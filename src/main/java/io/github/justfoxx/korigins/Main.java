package io.github.justfoxx.korigins;

import io.github.justfoxx.korigins.origins.CustomOrigin;
import net.fabricmc.api.ModInitializer;

public final class Main implements ModInitializer {

    @Override
    public void onInitialize() {
        Vars.logger.info("\uD83D\uDC31");
        Vars.logger.info(
                "Origins: {}",
                Vars.origins.values().stream().map(CustomOrigin::getId).toList());
    }
}
