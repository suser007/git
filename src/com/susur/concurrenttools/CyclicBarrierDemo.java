package com.susur.concurrenttools;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/***
 * CyclicBarrier：栅栏的应用场景
 * 赛跑
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(8);
        for (int i = 0; i < 8; i++) {
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                    System.out.println("Thread name::"+Thread.currentThread().getName()+"准备好了");
                    barrier.await();//完成一个+1
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                    System.out.println("倒计时:3");
//                    TimeUnit.SECONDS.sleep(1);
//                    System.out.println("倒计时:2");
//                    TimeUnit.SECONDS.sleep(1);
//                    System.out.println("倒计时:1");
//                    TimeUnit.SECONDS.sleep(1);
//                    System.out.println("选手 "+Thread.currentThread().getName()+" 起跑");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println("选手 "+Thread.currentThread().getName()+" 起跑");
            },"play["+i+"]").start();
        }
    }
}
