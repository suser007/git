package com.susur.volatiledemo;

import java.util.concurrent.TimeUnit;

/***
 * 双线程读写操作
 */
public class ReadAndUpdater {
    final static int MAX  = 3;
    static volatile int init_value = 0;

    public static void main(String[] args){
        //线程：：：读
        new Thread(()->{
            int localValue = init_value;
            while(localValue<MAX){
                if(localValue<MAX){
                    System.out.println("Reader: "+init_value);
                    localValue = init_value;
                }
            }
        },"Reader").start();

        //线程：：读，自增
        new Thread(()->{
            int localValue = init_value;
            while (localValue<MAX){
                System.out.println("Updater:"+(++localValue));
                init_value = localValue;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"updater").start();
    }
}
