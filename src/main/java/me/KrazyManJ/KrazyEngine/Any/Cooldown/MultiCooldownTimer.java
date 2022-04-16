package me.KrazyManJ.KrazyEngine.Any.Cooldown;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@SuppressWarnings({"unused","UnusedReturnValue"})
public final class MultiCooldownTimer<K> {
    private final HashMap<K,Long> cooldowns = new HashMap<>();
    private long delay;


    public MultiCooldownTimer(long delay) {
        this.delay = delay;
    }

    public MultiCooldownTimer(long delay, TimeUnit unit) {
        this.delay = unit.toMillis(delay);
    }

    public long getDelay() {
        return delay;
    }
    public MultiCooldownTimer<K> setDelay(long delay) {
        this.delay = delay;
        return this;
    }
    public MultiCooldownTimer<K> setDelay(long delay, TimeUnit unit){
        this.delay = unit.toMillis(delay);
        return this;
    }

    public MultiCooldownTimer<K> tick(K key){
        cooldowns.put(key,System.currentTimeMillis());
        return this;
    }
    public boolean check(K key){
        if (!cooldowns.containsKey(key)) return true;
        else return System.currentTimeMillis() > cooldowns.get(key) + delay;
    }
    public boolean checkAndTick(K key){
        if (check(key)){
            tick(key);
            return true;
        }
        return false;
    }


    public MultiCooldownTimer<K> clear(K key){
        if (!cooldowns.containsKey(key)) return this;
        cooldowns.remove(key);
        return this;
    }
    public MultiCooldownTimer<K> clearAll(){
        cooldowns.clear();
        return this;
    }

}
