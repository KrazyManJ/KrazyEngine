package me.KrazyManJ.KrazyEngine.Any.Cooldown;

import java.util.concurrent.TimeUnit;

/**
 *
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public final class CooldownTimer {
    private long delay;
    private long tick = System.currentTimeMillis();

    public CooldownTimer(long delay) {
        this.delay = delay;
    }

    public CooldownTimer(long delay, TimeUnit unit) {
        this.delay = unit.toMillis(delay);
    }

    public long getDelay() {
        return delay;
    }

    public CooldownTimer setDelay(long delay) {
        this.delay = delay;
        return this;
    }

    public CooldownTimer setDelay(long delay, TimeUnit unit) {
        this.delay = unit.toMillis(delay);
        return this;
    }

    public boolean check() {
        return System.currentTimeMillis() > tick + delay;
    }

    public CooldownTimer tick() {
        tick = System.currentTimeMillis();
        return this;
    }

    public boolean checkAndTick() {
        if (check()) {
            tick();
            return true;
        }
        return false;
    }

    public CooldownTimer clear() {
        tick = -delay;
        return this;
    }
}
