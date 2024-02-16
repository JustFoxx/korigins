package io.github.justfoxx.korigins.powers;

import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.justfoxx.korigins.Utils;
import lombok.val;
import net.minecraft.server.network.ServerPlayerEntity;
import virtuoel.pehkui.api.ScaleTypes;

public final class ChangeSize extends CustomPower<ChangeSize.ChangeSizeData> {
    public static final PowerKey<ChangeSizeData> key = new PowerKey<>(Utils.id("change_size"));

    public ChangeSize() {
        super(Utils.id("change_size"));
    }

    @Override
    public void onRemove(final PowerType<Power> power, final ServerPlayerEntity player) {
        val baseData = ScaleTypes.BASE.getScaleData(player);
        val reachData = ScaleTypes.REACH.getScaleData(player);
        val motionData = ScaleTypes.MOTION.getScaleData(player);

        if (baseData.getScale() != 1) baseData.setTargetScale(1);
        if (reachData.getScale() != 1) reachData.setTargetScale(1);
        if (motionData.getScale() != 1) motionData.setTargetScale(1);
    }

    @Override
    public void onAdd(final PowerType<Power> power, final ServerPlayerEntity player) {
        changeSize(player);
    }

    @Override
    public void onTick(final ServerPlayerEntity player) {
        val serverTicks = player.getServer().getTicks();

        if (serverTicks % 40 != 0) return;

        changeSize(player);
    }

    @Override
    public PowerKey<ChangeSizeData> getKey() {
        return key;
    }

    private void changeSize(final ServerPlayerEntity player) {
        val baseScale = ScaleTypes.BASE.getScaleData(player);
        val reachScale = ScaleTypes.REACH.getScaleData(player);
        val motionScale = ScaleTypes.MOTION.getScaleData(player);
        for (val origin : this.getOrigins(player)) {
            val data = this.getData(origin);
            val baseData = data.baseScale;
            val reachData = data.reachScale;
            val motionData = data.motionScale;

            if (baseScale.getScale() == 1.0) baseScale.setTargetScale(baseData);
            if (reachScale.getScale() == 1.0) reachScale.setTargetScale(reachData);
            if (motionScale.getScale() == 1.0) motionScale.setTargetScale(motionData);
        }
    }

    public record ChangeSizeData(float baseScale, float reachScale, float motionScale) {}
}
