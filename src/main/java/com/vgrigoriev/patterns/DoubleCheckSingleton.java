package com.vgrigoriev.patterns;

/**
 * author vgrigoriev on 19.06.2015.
 */
public class DoubleCheckSingleton {

    private static volatile DoubleCheckSingleton singleton;


    public static DoubleCheckSingleton getInstance() {

        DoubleCheckSingleton instance = singleton;
        if (singleton == null) {
            synchronized (DoubleCheckSingleton.class) {
                instance = singleton;
                if (instance == null) {
                    singleton = instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }

}
