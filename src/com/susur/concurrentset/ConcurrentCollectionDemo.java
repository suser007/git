package com.susur.concurrentset;

import java.util.concurrent.ConcurrentLinkedDeque;

/***
 * ******非阻塞*******
 *
 *
 * 并发式集合 ：：遍历时可以进行增删改操作
 */
public class ConcurrentCollectionDemo {

    public static void main(String[] args) throws InterruptedException {
        //非阻塞集合声明
        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
        //添加数据
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    list.add(Thread.currentThread().getName()+":Element "+ j);
                }
            });
            threads[i].start();
            threads[i].join();
        }
        System.out.println("after added size is :"+list.size());
        //移除数据
        Thread[] polls = new Thread[100];
        for (int i = 0; i < 100; i++) {
            polls[i] = new Thread(()->{
                for (int j = 0; j < 5000; j++) {
                    list.pollLast();
                    list.pollFirst();
                }
            });
            polls[i].start();
            polls[i].join();
        }
        System.out.println("after remove size is :"+list.size());
    }
}
