package com.susur.cas_aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/***
 * concurrent  adj：并发的
 * queued  n:队列
 * AbstractQueuedSynchronizer 同步发生器 构建LOCK 帮助器 模板
 * 通过内置得到FIFO同步队列来完成线程争夺资源的管理工作 CLH等待队列 tryAcquire获取锁,(先进先出的双向链表)
 * 可以用此来实现独占锁和共享锁，提供获取锁和释放锁功能
 *  acquire(int arg) 独占模式,忽略中断
 *  acquireShared(int arg) 共享模式，忽略中断
 *  tryAcquire(int arg) 试图以独占模式
 *  tryAcquireShared(int arg)
 *
 *  release(int arg)独占释放
 *  releaseShared(int arg)共享释放
 *
 *
 *
 */
public class Aqs {
    AbstractQueuedSynchronizer a;
}
