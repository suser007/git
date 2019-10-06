package com.susur.test;

public class Ticket extends Thread{
    private static int index = 1;
    public static final int MAX = 50;
    @Override
    public void run(){
        while(index <= MAX){
            System.out.println(Thread.currentThread().getName()+"叫到号码是："+(index++));
        }
    }

}
