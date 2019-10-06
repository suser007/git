package com.susur.concurrenttools;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/***
 * Semaphore:信号量
 * 停车场
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
//        创建信号量、
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    //        请求资源
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    semaphore.acquire();
                    System.out.println("汽车"+ finalI +"停车成功");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int val = new Random().nextInt(6);
                try {
                    //        使用资源
                    TimeUnit.SECONDS.sleep(val);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //        释放资源
                semaphore.release();
                System.out.println("汽车"+ finalI +"停留了"+val+"秒之后离开停车场"+semaphore.availablePermits());
            }).start();
        }
    }
}
