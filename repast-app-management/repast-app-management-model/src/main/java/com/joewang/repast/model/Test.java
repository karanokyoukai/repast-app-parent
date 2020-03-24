package com.joewang.repast.model;

import java.util.Random;
import java.util.Vector;

/**
 * @ClassName Test
 * @Description TODO
 * @Author 13061
 * @Date 2020/3/13 17:11
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        Vector vector = new Vector(6);
        for (char i = 'A'; i <= 'F'; i++){
            vector.add(i);
        }
        System.out.println(vector);
        for (int k = 0; k < 3; k++){
            Object object = vector.get(new Random().nextInt(6));
            System.out.println(object);
        }
    }
}
