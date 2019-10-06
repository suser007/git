package com.susur.cas_aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AqsDemoTest {
//    private static AqsDemo lock = new AqsDemo();
    private static ReentrantLock lock = new ReentrantLock();
    private static int m=0;
    private static int next() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        lock.lock();
        try{
            return m++;
        }finally {
            lock.unlock();
        }
//        return m++;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2000; i++) {
            new Thread(()->{
                try {
                    System.out.println(next());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
