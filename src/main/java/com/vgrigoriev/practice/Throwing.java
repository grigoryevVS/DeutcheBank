package com.vgrigoriev.practice;


public class Throwing{

    public static void yaUnchecked(Throwable e) {
        Throwing.<RuntimeException>yaBlaUnchecked(e);
    }

    public static <T extends Throwable> void yaBlaUnchecked(Throwable ex) throws T{
        throw (T)ex;
    }
}