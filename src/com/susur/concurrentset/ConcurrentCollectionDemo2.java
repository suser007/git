package com.susur.concurrentset;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/***
 * ******阻塞集合*******
 *
 * 
 * 并发式集合 ：：遍历时可以进行增删改操作
 */
public class ConcurrentCollectionDemo2 {

    public static void main(String[] args) throws InterruptedException {
        //阻塞集合声明
        LinkedBlockingDeque<String> list = new LinkedBlockingDeque<>(3);

        Thread thread = new Thread(()->{
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    String str = new String("["+i+":"+j+"]  ");
                    try {
                        list.put(str.toString());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("client: put "+str+(new Date()));
                }
            }
        });
        thread.start();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                String str = list.take();
                System.out.println("main: take "+str+" size:"+list.size());
            }
            TimeUnit.SECONDS.sleep(3);
        }

        System.out.println("end");
    }
}
