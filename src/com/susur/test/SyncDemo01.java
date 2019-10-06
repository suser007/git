package com.susur.test;

import java.util.concurrent.TimeUnit;

public class SyncDemo01 {
    /**
     * 修饰方法
     * synchronized修饰非静态方法
     *
     * ***线程对堆栈分析***
     * 1.jconsole
     * 2.jstack 【线程ID】
     * ***JVM指令分析***
     * 1.javap -v 【类名】     （反编译）   代码块加锁：monitorEnter  ...  minitorExit ... return
     *                                     方法 加锁：flags:ACC_SYNCHRONIZED 标记
     *
     *
     */
    private Integer n = 3;

    public synchronized void accessResources11(){
        try {
            TimeUnit.MINUTES.sleep(2);
            System.out.println(Thread.currentThread().getName()+" is running");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SyncDemo01 syncDemo01 = new SyncDemo01();
        for (int i=0;i<5;i++)
            new Thread(syncDemo01::accessResources11).start();
    }
}
