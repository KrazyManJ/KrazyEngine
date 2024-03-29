package me.KrazyManJ.KrazyEngine.Core;

import org.bukkit.Bukkit;

@NeedsUpdateEachMinecraftVersion(lastUpdated = "1.19")
@SuppressWarnings("unused")
public enum MinecraftNMSVersion {
    v1_8_R1,
    v1_8_R2,
    v1_8_R3,
    v1_9_R1,
    v1_9_R2,
    v1_10_R1,
    v1_11_R1,
    v1_12_R1,
    v1_13_R1,
    v1_13_R2,
    v1_14_R1,
    v1_15_R1,
    v1_16_R1,
    v1_16_R2,
    v1_16_R3,
    v1_17_R1,
    v1_18_R1,
    v1_18_R2,
    v1_19_R1,
    v1_19_R2,
    v1_19_R3
    ;

    public static MinecraftNMSVersion getCurrentVersion(){
        String currVersion = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        return valueOf(currVersion);
    }
}
