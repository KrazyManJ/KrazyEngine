package me.KrazyManJ.KrazyEngine.NMS;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SuppressWarnings("UnusedReturnValue")
public final class ReflectionHandler {

    @Deprecated private ReflectionHandler() {}

    public static Object instance(@NotNull Constructor<?> ctor, Object ...params){
        try {
            return ctor.newInstance(params);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

    }
    public static Object method(@NotNull Method method, Object obj, Object ...params){
        try {
            return method.invoke(obj,params);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Object[] enumConstants(@NotNull Class<?> enumClassm){
        return enumClassm.getEnumConstants();
    }
    public static Object field(@NotNull Field field, Object obj){
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Object cast(@NotNull Object obj, Class<?> castTo){
        return castTo.cast(obj);
    }
    public static Class<?> craftbukkit(String packagePath) throws ClassNotFoundException {
        return Class.forName("org.bukkit.craftbukkit."+ NMSUtils.getVersion() +"."+packagePath);
    }
}
