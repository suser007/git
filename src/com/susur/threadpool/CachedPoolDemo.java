package com.susur.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CachedPoolDemo {
   private static class Task implements Runnable{
       @Override
       public void run() {
           System.out.println(Thread.currentThread().getName()+" is running!");
           try {
               TimeUnit.SECONDS.sleep(2);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
   }

    public static void main(String[] args) {
      //创建可变大小的线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            Runnable task = new Task();//创建任务
            pool.execute(task);//交给线程池
        }
        pool.shutdown();
    }
}
