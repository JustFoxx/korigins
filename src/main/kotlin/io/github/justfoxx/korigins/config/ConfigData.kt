package io.github.justfoxx.korigins.config

import com.electronwill.nightconfig.core.conversion.Conversion
import com.electronwill.nightconfig.core.conversion.Converter
import com.electronwill.nightconfig.core.conversion.Path
import com.electronwill.nightconfig.core.conversion.SpecDoubleInRange
import io.github.apace100.origins.origin.Impact
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier

class ConfigData {
    @Path("ventur")
    private val ventur = VenturConfig()
}

class VenturConfig internal constructor() {
    @Path("disabled")
    val disabled = false

    @Path("change_size")
    val changeSize = ChangeSize()

    @Path("impact")
    @Conversion(ImpactToInteger::class)
    val impact = Impact.NONE

    @Path("visual_item")
    @Conversion(ItemToString::class)
    val visualItem = Items.AIR
}

class ChangeSize internal constructor() {
    @Path("disabled")
    val disabled = false

    @Path("base_scale")
    @SpecDoubleInRange(min = 0.0, max = Float.MAX_VALUE.toDouble())
    val baseScale = 0.0

    @Path("reach_scale")
    @SpecDoubleInRange(min = 0.0, max = Float.MAX_VALUE.toDouble())
    val reachScale = 0.0

    @Path("motion_scale")
    @SpecDoubleInRange(min = 0.0, max = Float.MAX_VALUE.toDouble())
    val motionScale = 0.0
}

private class ItemToString : Converter<Item, String?> {
    override fun convertToField(value: String?): Item {
        return Registries.ITEM[Identifier.tryParse(value)]
    }

    override fun convertFromField(value: Item): String {
        return Registries.ITEM.getId(value).toString()
    }
}

private class ImpactToInteger : Converter<Impact, Int?> {
    override fun convertToField(value: Int?): Impact {
        return Impact.getByValue(value!!)
    }

    override fun convertFromField(value: Impact): Int {
        return value.impactValue
    }
}