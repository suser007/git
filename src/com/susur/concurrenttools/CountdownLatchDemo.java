package com.susur.concurrenttools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/***
 * CountdownLatch：倒计时锁的应用场景
 * 机票预订
 */
public class CountdownLatchDemo {
    private static List<String> company = Arrays.asList("'东方航空'","'南方航空'","'海南航空'");
    private static List<String> fightList = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException{
        String origin = "【北京】";
        String dest   = "【上海】";

        CountDownLatch latch = new CountDownLatch(company.size());
        for (int i = 0; i < company.size(); i++) {
            String name = company.get(i);
            new Thread(()->{
                int val = new Random().nextInt(10);
                System.out.println(name +"公司查询成功 \n"+origin +" 到 "+dest+" 的机票："+val);
                try {
                    TimeUnit.SECONDS.sleep(val);
                    fightList.add(name+":"+val+"张");
                    latch.countDown();//完成一个-1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        latch.await();
        System.out.println("查询结果如下：");
        System.out.println(Arrays.toString(fightList.toArray()));
    }
}
