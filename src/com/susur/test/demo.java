package com.susur.test;

import java.util.concurrent.TimeUnit;

public class demo {
    public static void main(String[] args) throws InterruptedException {
        Ticket t = new Ticket();
        for(int i=0;i<Ticket.MAX;i++){
//            TimeUnit.MINUTES.sleep(2);
            new Thread(t::run).start();
        }
    }
}
