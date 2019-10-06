package com.susur.threadpool;

import java.awt.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/***
 *  线程池：：集中管理线程
 *  void execute(Runnable command);
 *  1.目的：：减少系统维护线程而造成的开销
 *           线程的运行和创建分开,(解耦)
 *           线程可以复用
 *  2.线程池的种类::利用Executors.class，，创建不同种类的线程池
 *  static ExecutorService newFixedThreadPool(int nThreads)  固定大小的线程池
 *  static ExecutorService newCachedThreadPool()    可变大小的线程池
 *  static ExecutorService newSingleThreadExecutor()  单线程线程池
 *  static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) 可调度线程池
 *  ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);
 *
 *
 *  3.核心类：：ThreadPoolExecutor.class
 *  public ThreadPoolExecutor(int corePoolSize,         **默认线程数**
 *                               int maximumPoolSize,   **最大线程数**
 *                               long keepAliveTime,    **新创建线程保持活动的时间**  60L
 *                               TimeUnit unit,         **单位**                    TimeUnit.SECONDS
 *                               BlockingQueue<Runnable> workQueue, **阻塞队列**new SynchronousQueue<Runnable>()
 *                               ThreadFactory threadFactory,       **线程工厂**
 *                               RejectedExecutionHandler handler)  **拒绝策略**
 *
 *                                    corePoolSize:核心线程池大小(初始)
 *                                    maxPoolSize:最大线程数
 *
 *                               ****************************************
 *                               **当线程池全部使用了，又来一个请求线程   **
 *                               **   1.等待               2.创建       **
 *                               ****************************************
 *
 *                    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
 *                    private static final int COUNT_BITS = Integer.SIZE - 3;                 ***32-3，因为5种可能取值，2^3=8 > 5   计数位
 *                    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;            ***线程池的容量:1*(2^29)-1 = 2^29-1  公式::λ<<n = λ*(2^n)
 * 4.线程池状态：：// runState is stored in the high-order bits    COUNT_BITS = 29               ***然后将3位标志位左移到32位的最高位上面
 *                    private static final int RUNNING    = -1 << COUNT_BITS; 运行状态，默认状态
 *                    private static final int SHUTDOWN   =  0 << COUNT_BITS; 关闭状态，拒绝接受新任务，正在执行已有任务，shutdown()
 *                    private static final int STOP       =  1 << COUNT_BITS; 停止状态，既不接收新任务，也不执行已有任务，中断正在处理的任务，shutdownNow()
 *                    private static final int TIDYING    =  2 << COUNT_BITS; 整理状态，运行完线程
 *                    private static final int TERMINATED =  3 << COUNT_BITS; 结束状态，terminated()
 *
 *                     ┍┉┉┉┉┉┉┉┉┉┑                              ┍┉┉┉┉┉┉┉┉┉┉┑
 *                     ┋ RUNNING ┋    ===== shutdown() =====>   ┋ SHUTDOWN ┋
 *                     ┕┉┉┉┉┉┉┉┉┉┙                              ┕┉┉┉┉┉┉┉┉┉┉┙
 *                          ↓                                        ↓
 *                          ↓                                        ↓
 *                     shutdownNow()                           队列中的任务为空
 *                          ↓                                        ↓
 *                          ↓                                        ↓
 *                      ┍┉┉┉┉┉┉┑                                ┍┉┉┉┉┉┉┉┉┉┑                        ┍┉┉┉┉┉┉┉┉┉┉┉┉┑
 *                      ┋ STOP ┋    == thread are executed ==>  ┋ TIDYING ┋   == terminated() ==>  ┋ TERMINATED ┋
 *                      ┕┉┉┉┉┉┉┙                                ┕┉┉┉┉┉┉┉┉┉┙                        ┕┉┉┉┉┉┉┉┉┉┉┉┉┙
 *
 *  public void execute(Runnable command) {
 *         if (command == null)
 *             throw new NullPointerException();
 *         int c = ctl.get();    //获取线程池状态值
 *         if (workerCountOf(c) < corePoolSize) { //       当(运行线程数 < corePoolSize){
 *             if (addWorker(command, true))                    分配一个线程处理
 *                 return;
 *             c = ctl.get();
 *         }                                                }
 *         if (isRunning(c) && workQueue.offer(command)) { //当前线程池处于运行状态 && 写入阻塞队列
 *             int recheck = ctl.get();                     //再次获取状态码
 *             if (! isRunning(recheck) && remove(command)) //如果
 *                 reject(command);                         //拒绝执行
 *             else if (workerCountOf(recheck) == 0)
 *                 addWorker(null, false);                  //执行任务
 *         }
 *         else if (!addWorker(command, false))             //尝试新建线程，失败就拒绝
 *             reject(command);
 *  }
 *
 * 5.创建线程的方式：：New Thread();             真正意义的线程
 *                    implements Runnable      只是实现Runnable，但是还是要传到Thread里面去
 *  Future :: 框架 把一些事情放一边去做，然后给你一个票据，用它来取结果就行
 *
 */
public class ThreadPool {
    Executor e;
    Future f;
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    public static void main(String[] args) {
        System.out.println("COUNT_BITS: "+COUNT_BITS);
        System.out.println("CAPACITY:   "+CAPACITY);
        System.out.println("CAPACITY:   "+action(Integer.toBinaryString(CAPACITY)));
        System.out.println("RUNNING:    "+action(Integer.toBinaryString(RUNNING)));
        System.out.println("SHUTDOWN:   "+action(Integer.toBinaryString(SHUTDOWN)));
        System.out.println("STOP:       "+action(Integer.toBinaryString(STOP)));
        System.out.println("TIDYING:    "+action(Integer.toBinaryString(TIDYING)));
        System.out.println("TERMINATED: "+action(Integer.toBinaryString(TERMINATED)));
    }
    public static String action(String num){
        StringBuffer sb = new StringBuffer(num);
        while(true){
            if(sb.length()<32) {
                StringBuffer newSb = new StringBuffer("0");
                newSb.append(sb);
                sb = newSb;
            } else{
                return new String(sb);
            }
        }

    }
}
