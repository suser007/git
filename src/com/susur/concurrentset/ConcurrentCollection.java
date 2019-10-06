package com.susur.concurrentset;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.PriorityBlockingQueue;

/***
 * ConcurrentCollection.class 并发集合：非阻塞集合 、           阻塞式集合
 *                                     ConcurrentLinkedDeque、LinkedBlockingDeque
 *  ConcurrentLinkedDeque(非阻塞集合)：：当集合已满或为空时，增删改方法不能被立即执行，那么方法返回null或抛出异常，调用这个方法的线程不会被阻塞
 *  LinkedBlockingDeque(阻塞集合)：：当集合已满或为空时，增删改方法不能被立即执行，那么调用这个方法的线程会被阻塞，直到方法可以被执行
 *
 *  LinkedTransferQueue(生产消费)：：
 *  PriorityBlockingQueue(优先级)
 */
public class ConcurrentCollection {
    ConcurrentLinkedDeque d;
    LinkedBlockingDeque l;
    LinkedTransferQueue a;
    PriorityBlockingQueue p;
}
