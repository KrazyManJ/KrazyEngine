package me.KrazyManJ.KrazyEngine.Core;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SuppressWarnings("UnusedReturnValue")
public final class ReflectionHandler {

    @Deprecated private ReflectionHandler() {}


    public static Field accessibleField(@NotNull Field field){
        field.setAccessible(true);
        return field;
    }
    public static Object invokeMethod(@NotNull Method method, Object obj, Object ...params){
        try {
            return method.invoke(obj,params);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void setField(@NotNull Field field, Object obj, Object setTo){
        try {
            field.set(obj,setTo);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static Object cast(@NotNull Object obj, Class<?> castTo){
        return castTo.cast(obj);
    }
    public static Class<?> craftbukkitClass(String packagePath) throws ClassNotFoundException {
        return Class.forName("org.bukkit.craftbukkit."+ getVersion() +"."+packagePath);
    }

    public static String getVersion(){
        return Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
    }
}
