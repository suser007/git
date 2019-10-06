package com.susur.cas_aqs;

import java.util.concurrent.atomic.AtomicInteger;

/***
 * Synchronized粒度太大，影响性能
 * CAS(CompareAndSwap)比较和交换::底层是一种无锁的原子算法，即乐观锁
 * 思想：：给你一个期望值，与你现有的值进行比较，如果相等进行修改，如果不相等表示你不能修改
 * CAS(V,E,N) if(V==E) {
 *     V=N
 * }
 * 适用于简单的数据计算
 * 适用于线程冲突少的
 * 优点：：CAS实现稍微复杂，无锁，不存在阻塞，CPU吞吐量高，性能好
 * 锁：：悲观锁（写的操作多，增删改，读的少）    lock机制
 *      乐观锁（读的多，写得少）                版本机制
 */
public class Cas {
    private static volatile int m = 0;
    private static volatile int n = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    public static void increase(){
        m++;
    }
    public static void increase0(){
        n++;
    }
    public static void increase1(){
        atomicInteger.incrementAndGet();  //调用了unsafe.class::getAndAddInt(,,); unsafe.cpp;汇编cmpxchg指令;
        //汇编里面一条指令，保证了原子性
    }
    public static void main(String[] args) throws InterruptedException {
//        Thread[] threads = new Thread[100];
//        for (int i = 0; i < 100; i++) {
//            threads[i] = new Thread(()->{});
//        }
        for (int i = 0; i < 1000000; i++) {
            new Thread(()->{
                Cas.increase();
            }).start();
        }
        Thread[] threads = new Thread[1000000];
        for (int i = 0; i < 1000000; i++) {
            threads[i] = new Thread(()->{
                Cas.increase0();
            });
            threads[i].start();
            threads[i].join();
        }
        for (int i = 0; i < 1000000; i++) {
            new Thread(()->{
//                Cas.increase();
                Cas.increase1();
            }).start();
        }
        System.out.println(m);
        System.out.println(n);
        System.out.println(atomicInteger.get());
    }
}
