package com.susur.singleton;


import java.net.Socket;
import java.sql.Connection;

/***
 *  DCL的问题：：
 *  指令重排造成的线程安全问题：：重排后变量x比instance变量后赋值，当指令运行到instance刚赋值之后（不为空），另一个线程访问了这个实例中的x就会空指针异常，因为此时给x赋值的指令还没运行！！！
 *  DCL问题解决方案：：instance实例加上volatile关键字  volatile + Double-check
 *
 * 有且只有一个实例化的过程   and   提供返回实例对象的方法
 *
 *  等同于：：DCL双锁
 * 懒汉式+同步方法：：线程安全，但是加了synchronized，退化到了串行执行,所以将synchronized关键字缩小了粒度，并且用了双锁
 */
public class Dcl {

    private Socket socket;
    private Connection conn;//volatile关键字解决了指令重排中的线程安全问题
    private volatile static Dcl instance = null;
    private Dcl(){

    }
    //多个线程同时访问对象可能会创建多个实例对象
    public Dcl getInstance(){
        if(null == instance)
            synchronized(Dcl.class){
                if(null==instance)
                    instance =  new Dcl();
            }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 25; i++) {
            new Thread(()->{
                System.out.println(HungerySingleton.getInstance());
            }).start();
        }
    }
}
