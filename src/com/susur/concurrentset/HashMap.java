package com.susur.concurrentset;

import java.util.concurrent.ConcurrentHashMap;

/***
 * HashMap: 数组+链表+红黑树 jdk1.8
 */
public class HashMap {
    java.util.HashMap m;
    ConcurrentHashMap hashMap = new ConcurrentHashMap();//分段加锁，缩小Synchronized锁的粒度
}
