package com.salk.codelib.sigleton;

/**
 * Created by salk on 2017/5/3.
 * 饿汉式
 */
public class Singleton4 {
    private static final Singleton4 singleton1 = new Singleton4();

    private Singleton4() {

    }

    public static Singleton4 getInstance() {
        return singleton1;
    }
}
