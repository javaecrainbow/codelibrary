package com.salk.codelib.sigleton;

/**
 * Created by salk on 2017/5/3.
 */
public class Singleton1 {
    private static Singleton1 singleton1 = null;

    private Singleton1() {

    }

    /**
     * 懒汉式，默认不做强制要求懒加载，默认用该种实现
     *
     * @return
     */
    public static synchronized Singleton1 getInstance() {
        if (singleton1 == null) {
            singleton1= new Singleton1();
        }
        return singleton1;
    }




}
