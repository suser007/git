package com.susur.locks;

import java.util.concurrent.locks.ReentrantLock;

/***
 * 可重入的互斥锁：：如AqsDemo定义的锁  com.susur.cas_aqs.AqsDemo
 * ReentrantLock()                  创建一个 ReentrantLock的实例。                 ***默认非公平锁***
 * ReentrantLock(boolean fair)      根据给定的公平政策创建一个 ReentrantLock的实例。
 */
public class ReetrantLock {
    ReentrantLock a;
}
