package io.github.justfoxx.korigins.origins;

import io.github.apace100.origins.Origins;
import io.github.apace100.origins.origin.Impact;
import io.github.justfoxx.korigins.GlobalUtils;
import io.github.justfoxx.korigins.powers.CustomPower;
import java.util.List;
import net.minecraft.item.Items;

public final class VenturOrigin extends CustomOrigin {
    public static final VenturOrigin INSTANCE = new VenturOrigin();

    private VenturOrigin() {
        super(
                GlobalUtils.id("ventur"),
                List.of(CustomPower.PowerData.nonCustom(Origins.identifier("climbing"))),
                Items.REDSTONE.getDefaultStack(),
                Impact.MEDIUM,
                5,
                0);
    }
}
