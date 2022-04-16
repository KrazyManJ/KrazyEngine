package me.KrazyManJ.KrazyEngine.Any.Cooldown;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;


@SuppressWarnings({"unused","UnusedReturnValue"})
public final class MultiTimeCooldownTimer<K> {
    private final HashMap<K,CooldownTimer> cooldowns = new HashMap<>();
    private long defaultDelay;


    public MultiTimeCooldownTimer(long defaultDelay) {
        this.defaultDelay = defaultDelay;
    }
    public MultiTimeCooldownTimer(long defaultDelay, TimeUnit unit) {
        this.defaultDelay = unit.toMillis(defaultDelay);
    }

    public long getDefaultDelay() {
        return defaultDelay;
    }

    public void setDefaultDelay(long defaultDelay) {
        this.defaultDelay = defaultDelay;
    }

    public MultiTimeCooldownTimer<K> setDelay(K key, long delay){
        cooldowns.put(key,new CooldownTimer(delay));
        return this;
    }
    public MultiTimeCooldownTimer<K> setDelay(K key, long delay, TimeUnit unit){
        cooldowns.put(key,new CooldownTimer(delay,unit));
        return this;
    }
    public boolean check(K key){
        if (!cooldowns.containsKey(key)) return true;
        return cooldowns.get(key).check();
    }
    public MultiTimeCooldownTimer<K> tick(K key){
        if (!cooldowns.containsKey(key)) cooldowns.put(key,new CooldownTimer(defaultDelay));
        cooldowns.get(key).tick();
        return this;
    }
    public boolean checkAndTick(K key){
        if (check(key)){
            tick(key);
            return true;
        }
        return false;
    }

    public MultiTimeCooldownTimer<K> clear(K key){
        if (!cooldowns.containsKey(key)) return this;
        cooldowns.remove(key);
        return this;
    }
    public MultiTimeCooldownTimer<K> clearAll(){
        cooldowns.clear();
        return this;
    }
}
