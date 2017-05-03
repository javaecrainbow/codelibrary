package com.salk.codelib.sigleton;

/**
 * Created by salk on 2017/5/3.
 */
public class Singleton5 {
    private static volatile Singleton5 singleton5 = null;

    /**
     * double check
     * new Singleton1()并非原子操作
     * 先后做了三件事：1.给singleton分配内存，2.调用singleton的构造方法来初始化成员比那两，3.将instance对象指向分配的内存空间
     * jvm即时编译器中存在指令重排的优化，也就是说上面的顺序是不能保证，所以我们需要把instance是设置成volatile, volatile是可见性
     * 屏蔽指令重排，保证了读操作在写操作后面
     *
     * @return
     */
    public static Singleton5 getInstance2() {
        if (singleton5 == null) {
            synchronized (Singleton5.class) {
                if (singleton5 == null) {
                    singleton5= new Singleton5();
                }
            }

        }
        return singleton5;
    }
}
