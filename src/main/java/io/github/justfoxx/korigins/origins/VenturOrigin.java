package io.github.justfoxx.korigins.origins;

import io.github.apace100.origins.Origins;
import io.github.apace100.origins.origin.Impact;
import io.github.justfoxx.korigins.Utils;
import io.github.justfoxx.korigins.Vars;
import io.github.justfoxx.korigins.powers.ChangeSize;
import io.github.justfoxx.korigins.powers.PowerData;
import java.util.List;
import lombok.val;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public final class VenturOrigin extends CustomOrigin {
    public static final VenturOrigin INSTANCE;

    static {
        val config = Vars.config.getVentur();
        val item = Registries.ITEM.get(Utils.id("ventur")).getDefaultStack();
        val id = Utils.id("ventur");
        val powers = List.of(
                PowerData.nonCustom(Origins.identifier("climbing")),
                PowerData.custom(
                        ChangeSize.key,
                        new ChangeSize.ChangeSizeData(
                                (float) config.getBaseScale(), (float) config.getReachScale(), (float)
                                        config.getReachScale())));

        INSTANCE = new VenturOrigin(id, powers, item, Impact.NONE, 0, 0);
    }

    private VenturOrigin(
            final Identifier id,
            final List<PowerData<?>> powers,
            final ItemStack visualItem,
            final Impact impact,
            final int order,
            final int priority) {
        super(id, powers, visualItem, impact, order, priority);
    }

    @Override
    public boolean isDisabled() {
        return Vars.config.getVentur().isDisabled();
    }
}
