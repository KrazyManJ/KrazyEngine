package me.KrazyManJ.KrazyEngine.Spigot.World;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@SerializableAs("Region")
@SuppressWarnings({"unused", "UnusedReturnValue"})
public final class Region implements Cloneable, ConfigurationSerializable {

    private final Location min;
    private final Location max;

    public Region(@NotNull Location first, @NotNull Location second) {
        this.min = new Location(first.getWorld(),
                Math.min(first.getX(), second.getX()),
                Math.min(first.getY(), second.getY()),
                Math.min(first.getZ(), second.getZ())
        );
        this.max = new Location(first.getWorld(),
                Math.max(first.getX(), second.getX()),
                Math.max(first.getY(), second.getY()),
                Math.max(first.getZ(), second.getZ())
        );
    }

    public boolean isWithin(@NotNull Location location) {
        if (location.getWorld() == null) return false;
        if (!location.getWorld().equals(min.getWorld())) return false;
        return min.getX() >= location.getX()
                && min.getY() >= location.getY()
                && min.getZ() >= location.getZ()
                && max.getX() <= location.getX()
                && max.getY() <= location.getY()
                && max.getZ() <= location.getZ()
                ;
    }

    public boolean isWithin(Block block) {
        return isWithin(block.getLocation());
    }

    public boolean isWithin(Entity entity) {
        return isWithin(entity.getLocation());
    }

    public Location getMin() {
        return min;
    }

    public Location getMax() {
        return max;
    }

    public double getXSize() {
        return max.getX() - min.getX();
    }

    public double getYSize() {
        return max.getY() - min.getY();
    }

    public double getZSize() {
        return max.getZ() - min.getZ();
    }

    @Contract(" -> new")
    public @NotNull Location getCenter() {
        return new Location(
                min.getWorld(),
                min.getX() + getXSize() / 2,
                min.getY() + getYSize() / 2,
                min.getZ() + getZSize() / 2
        );
    }

    public @NotNull List<Location> getCorners() {
        List<Location> r = new ArrayList<>();
        r.add(min.clone());
        r.add(min.clone().add(getXSize(), 0, 0));
        r.add(min.clone().add(0, 0, getZSize()));
        r.add(min.clone().add(getXSize(), 0, getZSize()));
        r.add(min.clone().add(0, getYSize(), 0));
        r.add(min.clone().add(getXSize(), getYSize(), 0));
        r.add(min.clone().add(0, getYSize(), getZSize()));
        r.add(max.clone());
        return r;
    }

    public World getWorld() {
        return min.getWorld();
    }

    public List<Block> getBlocksInRegion() {
        List<Block> r = new ArrayList<>();
        for (int y = 0; y < getYSize(); y++) {
            for (int z = 0; z < getZSize(); z++) {
                for (int x = 0; x < getXSize(); x++) r.add(getWorld().getBlockAt(x, y, z));
            }
        }
        return r;
    }

    public boolean isCollidingWithRegion(Region region) {
        return getBlocksInRegion().stream().anyMatch(b -> region.getBlocksInRegion().contains(b));
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        HashMap<String, Object> r = new HashMap<>();
        r.put("min", min);
        r.put("max", max);
        return r;
    }

    public Region(Map<String, Object> map, Location min) {
        this.min = null;
        this.max = null;
        Location tMin = (Location) map.get("min");
        Location tMax = (Location) map.get("max");
        if (tMin.getWorld() == null || tMax.getWorld() == null)
            throw new NullPointerException();
        if (!tMin.getWorld().equals(tMax.getWorld()))
            throw new RuntimeException("Worlds are not equal!");
        new Region(tMin, tMax);
    }

    @Override
    public Region clone() {
        try {
            return (Region) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return min.equals(region.min) && max.equals(region.max);
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max);
    }
}
