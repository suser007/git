package com.susur.singleton;



/***
 *
 *
 * 有且只有一个实例化的过程   and   提供返回实例对象的方法
 *
 *
 * 懒汉式::线程不安全
 */
public class HooSingleton {

    private static HooSingleton instance = null;
    private HooSingleton(){

    }
    //多个线程同时访问对象可能会创建多个实例对象
    public HooSingleton getInstance(){
        if(null == instance){
            instance =  new HooSingleton();
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
