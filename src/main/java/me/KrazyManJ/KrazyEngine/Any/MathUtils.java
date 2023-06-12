package me.KrazyManJ.KrazyEngine.Any;

@SuppressWarnings("unused")
public final class MathUtils {

    private MathUtils(){

    }

    public static int minmax(int value, int min, int max){
        return Math.min(Math.max(value, min), max);
    }

    public static float minmax(float value, float min, float max){
        return Math.min(Math.max(value, min), max);
    }

    public static long minmax(long value, long min, long max){
        return Math.min(Math.max(value, min), max);
    }

    public static double minmax(double value, double min, double max){
        return Math.min(Math.max(value, min), max);
    }

    public static int map(int value, int fMin, int fMax, int tMin, int tMax){
        return (value - fMin) * (tMax - tMin) / (fMax - fMin) + tMin;
    }

    public static float map(float value, float fMin, float fMax, float tMin, float tMax){
        return (value - fMin) * (tMax - tMin) / (fMax - fMin) + tMin;
    }

    public static long map(long value, long fMin, long fMax, long tMin, long tMax){
        return (value - fMin) * (tMax - tMin) / (fMax - fMin) + tMin;
    }

    public static double map(double value, double fMin, double fMax, double tMin, double tMax){
        return (value - fMin) * (tMax - tMin) / (fMax - fMin) + tMin;
    }
}
