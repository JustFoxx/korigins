@file:JvmName("Main")

package io.github.justfoxx.korigins

import io.github.apace100.origins.integration.OriginDataLoadedCallback
import io.github.justfoxx.korigins.api.logger
import io.github.justfoxx.korigins.api.origins

const val MOD_NAME = "korigins"

fun init() {
    OriginDataLoadedCallback.EVENT.register(::onOriginLoaded)
}

fun onOriginLoaded(isClient: Boolean) {
    if (isClient) return
    logger.info("Origins: {}", origins.values)
}
