package com.salk.codelib.sigleton;

/**
 * Created by salk on 2017/5/3.
 * 静态内部类，通过jvm本身的机制保证了线程的安全
 */
public class Singleton2 {
    private static class SingletonHolder{
        private static final Singleton2 INSTANCE=new Singleton2();
    }
    private Singleton2(){

    }
    public static final Singleton2 getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
