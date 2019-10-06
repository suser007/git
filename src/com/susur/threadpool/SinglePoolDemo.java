package com.susur.threadpool;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SinglePoolDemo {
   private static class Task implements Runnable{
       @Override
       public void run() {
           System.out.println(Thread.currentThread().getName()+" is running!");
           try {
               TimeUnit.SECONDS.sleep(new Random().nextInt(5));
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
   }

    public static void main(String[] args) {
       //创建固定大小线程池
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //创建10个任务
        for (int i = 0; i < 10; i++) {
            Runnable task = new Task();//创建任务
            threadPool.execute(task);//把任务交给pool并执行
        }
        threadPool.shutdown();//关闭线程池
    }
}
