package me.KrazyManJ.KrazyEngine.NMS;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionHandler {

    @Deprecated private ReflectionHandler() {}

    public static Object instance(@NotNull Constructor<?> ctor, Object ...params){
        try {
            return ctor.newInstance(params);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

    }
    public static Object method(Method method, Object obj, Object ...params){
        try {
            return method.invoke(obj,params);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Object[] enumConstants(Class<?> enumClassm){
        return enumClassm.getEnumConstants();
    }
}
