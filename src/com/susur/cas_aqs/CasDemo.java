package com.susur.cas_aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CasDemo {
    private static AtomicInteger atomicInteger = new AtomicInteger(100);
    //设置版本号,从期望值到设置的值修改的同时，版本号也会+1;
    private static AtomicStampedReference asr = new AtomicStampedReference(100,1);
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            atomicInteger.compareAndSet(100,110);
//            System.out.println("running:"+atomicInteger.get());
        });
//        System.out.println("before:"+atomicInteger.get());
        t1.start();
//        System.out.println("after: "+atomicInteger.get());

        Thread t2 = new Thread(()->{
            atomicInteger.compareAndSet(110,100);
//            System.out.println("running:"+atomicInteger.get());
        });
        t2.start();

        Thread t3 = new Thread(()->{
            atomicInteger.compareAndSet(100,120);
//            System.out.println("running:"+atomicInteger.get());
        });
        t3.start();
        //如上会出现线程不安全的问题
        TimeUnit.SECONDS.sleep(2);
        System.out.println("later::"+atomicInteger.get());

        Thread tf1 = new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(0);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(asr.compareAndSet(100,110,asr.getStamp(),asr.getStamp()+1));
            System.out.println(asr.toString());
            System.out.println(asr.compareAndSet(110,120,asr.getStamp(),asr.getStamp()+1));
            System.out.println(asr.compareAndSet(120,130,asr.getStamp(),asr.getStamp()+1));
            System.out.println(asr.getStamp()+"  "+asr.getReference());
        });

        Thread tf2 = new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(asr.getStamp()+"  "+asr.getReference());
            System.out.println(asr.compareAndSet(130,100,asr.getStamp(),asr.getStamp()+1));
        });
        tf1.start();
        tf2.start();
    }

}
