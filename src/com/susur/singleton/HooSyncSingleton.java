package com.susur.singleton;



/***
 *
 *
 * 有且只有一个实例化的过程   and   提供返回实例对象的方法
 *
 *
 * 懒汉式+同步方法：：线程安全，但是加了synchronized，退化到了串行执行
 */
public class HooSyncSingleton {

    private static HooSyncSingleton instance = null;
    private HooSyncSingleton(){

    }
    //退化到了串行执行
    public synchronized HooSyncSingleton getInstance(){
        if(null == instance){
            instance =  new HooSyncSingleton();
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
