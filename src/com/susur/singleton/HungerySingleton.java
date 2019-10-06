package com.susur.singleton;

/***
 *  单例模式：  多个线程要操作同一对象，要保证对象的唯一性
 *
 *  实例化的时候实例化一次
 *  有且仅有一个实例化的过程   and   提供返回实例对象的方法
 *
 *  单例模式分类：：        线程安全性、性能、懒加载
 *  1.饿汉式               线程安全、相对低、无懒加载
 *  2.懒汉式               线程不安全、相对高、懒加载
 *  3.懒汉式+synchronized  线程安全、相对低、懒加载
 *  4.DCL                  线程安全、较好、懒加载
 *  5.Holder(静态内部类)   线程安全、性能高、懒加载    ***应用最广泛***         HolderDemo.java
 *  6.枚举(内部枚举)                                  ***应用最广泛***        EnumSingletonDemo.java
 */


/**
 * 1.总结：：饿汉式，不会存在线程安全问题，但是未使用的对象加载到内存，是对堆资源的一种浪费，严重情况即溢出。于是有了延迟加载，需要用的时候再实例化，即懒汉式
 */
public class HungerySingleton {
    //有一个实例化的过程
    private static HungerySingleton instance = new HungerySingleton();//由于使用static关键字，类加载过程中就生成了一个实例，加载到了堆空间，从而实现了单例
    private HungerySingleton(){}
    //提供返回实例对象的方法
    public static HungerySingleton getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                System.out.println(HungerySingleton.getInstance());
            }).start();
        }
    }
}
