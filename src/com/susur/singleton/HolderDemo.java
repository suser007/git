package com.susur.singleton;

/***
 *  用的最多的内部类模式
 *  声明类的时候，成员变量中不声明实例变量，而是放到内部静态类中
 */
public class HolderDemo {
    private HolderDemo(){

    }
    //实现了懒加载，而且没加锁
    private static class Holder{
        private static HolderDemo instance = new HolderDemo();
    }
    public static HolderDemo getInstance(){
        return Holder.instance;
    }
}
