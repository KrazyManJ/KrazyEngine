package me.KrazyManJ.KrazyEngine.Any;

@SuppressWarnings("unused")
public class DataHolder {
    public record One<T1>(T1 t1){}
    public record Two<T1,T2>(T1 t1, T2 t2){}
    public record Three<T1,T2,T3>(T1 t1, T2 t2, T3 t3){}
    public record Four<T1,T2,T3,T4>(T1 t1, T2 t2, T3 t3, T4 t4){}
    public record Five<T1,T2,T3,T4,T5>(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5){}
    public record Six<T1,T2,T3,T4,T5,T6>(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6){}
    public record Seven<T1,T2,T3,T4,T5,T6,T7>(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7){}
    public record Eight<T1,T2,T3,T4,T5,T6,T7,T8>(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8){}
    public record Nine<T1,T2,T3,T4,T5,T6,T7,T8,T9>(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9){}
    public record Ten<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10>(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10){}
}
