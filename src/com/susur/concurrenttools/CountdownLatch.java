package com.susur.concurrenttools;

/***
 * CountdownLatch n:倒计时锁
 *  countDownLatch这个类使一个线程等待其他线程各自执行完毕后再执行。
 * 是通过一个计数器来实现的，计数器的初始值是线程的数量。
 * 每当一个线程执行完毕后，计数器的值就-1，当计数器的值为0时，
 * 表示所有线程都执行完毕，然后在闭锁上等待的线程就可以恢复工作了。
 *
 *  闭锁上等待的线程，必须要等前面几个线程执行完才能执行
 *  CountDownLatch(5);表示前面需要执行完五个线程
 *  await();唤醒主线程继续执行
 *
 */
public class CountdownLatch {
}
