package com.susur.test;

import java.util.concurrent.TimeUnit;

public class SyncDemo00 extends Thread{

    /**
     * 修饰方法
     * synchronized修饰静态方法
     */
    public synchronized static void accessResources(){
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName()+" is running");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i=0;i<5;i++)
            new Thread(SyncDemo00::accessResources).start();
    }
}
