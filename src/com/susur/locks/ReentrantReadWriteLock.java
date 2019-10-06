package com.susur.locks;

/***
 * 读写锁  write.unlock() 之前加读锁 read.lock()::锁降级
 *        read.unlock() 之前加上写锁 write.lock()::锁升级
 */

public class ReentrantReadWriteLock {
}
