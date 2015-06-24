package com.vgrigoriev.algorithms.stacksQueues;

import java.util.ArrayList;
import java.util.List;

/**
 * this class implemented for testing my stacksAndQueues collections.
 */
public class TestClass {

    public static void main(String[] args) {


        List<String> ff = new ArrayList<>();
        ff.add("12");
        ff.add("1");
        ff.add("13");
        ff.add("135");
        ff.add("14");
        ff.add("115");

        System.out.println(ff.subList(0, 2));
        System.out.println(ff.subList(2, 4));
        System.out.println(ff.subList(4, 6));



    }
}
