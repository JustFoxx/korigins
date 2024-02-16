package io.github.justfoxx.korigins.config;

import com.electronwill.nightconfig.core.conversion.Conversion;
import com.electronwill.nightconfig.core.conversion.Converter;
import com.electronwill.nightconfig.core.conversion.Path;
import com.electronwill.nightconfig.core.conversion.SpecDoubleInRange;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

@Getter
@ToString
@EqualsAndHashCode
public final class ConfigData {
    @Path("ventur")
    private VenturConfig ventur;

    @Getter
    @ToString
    @EqualsAndHashCode
    public static final class VenturConfig {
        private VenturConfig() {}

        @Path("disabled")
        private boolean disabled;

        @Path("change_size")
        private ChangeSize changeSize;

        @Path("visual_item")
        @Conversion(ItemStringConversion.class)
        private Item visualItem;
    }

    @Getter
    @ToString
    @EqualsAndHashCode
    public static final class ChangeSize {
        private ChangeSize() {}

        @Path("disabled")
        private boolean disabled;

        @Path("base_scale")
        @SpecDoubleInRange(min = 0.0, max = Float.MAX_VALUE)
        private double baseScale;

        @Path("reach_scale")
        @SpecDoubleInRange(min = 0.0, max = Float.MAX_VALUE)
        private double reachScale;

        @Path("motion_scale")
        @SpecDoubleInRange(min = 0.0, max = Float.MAX_VALUE)
        private double motionScale;
    }

    static class ItemStringConversion implements Converter<Item, String> {
        @Override
        public Item convertToField(final String value) {
            return Registries.ITEM.get(Identifier.tryParse(value));
        }

        @Override
        public String convertFromField(final Item value) {
            return Registries.ITEM.getId(value).toString();
        }
    }
}
