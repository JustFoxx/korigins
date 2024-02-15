package io.github.justfoxx.korigins.config;

import com.electronwill.nightconfig.core.conversion.Path;
import com.electronwill.nightconfig.core.conversion.SpecDoubleInRange;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

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

        @Path("base_scale")
        @SpecDoubleInRange(min = 0.0, max = Float.MAX_VALUE)
        private double baseScale;

        @Path("reach_scale")
        @SpecDoubleInRange(min = 0.0, max = Float.MAX_VALUE)
        private double reachScale;
    }
}
