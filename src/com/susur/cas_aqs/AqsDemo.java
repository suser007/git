package com.susur.cas_aqs;
/***
 * 用AQS模板写一个锁
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * AqsDemo为我自定义的锁
 * 现成的东西有 ReetrantLock::可重入的互斥锁
 */
public class AqsDemo implements Lock {

    private Helper helper = new Helper();

    private class Helper extends AbstractQueuedSynchronizer{
        /**
         * 1.获取锁
         * 2.释放锁
         */
        @Override
        public boolean tryAcquire(int arg){
            int state = getState();
            if(state == 0){
                //利用CAS原理修改state
                if(compareAndSetState(0,arg)){
                    //设置当前线程拥有资源
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            }else if(getExclusiveOwnerThread() == Thread.currentThread()){//可重入性的支持：：锁的嵌套
                /***
                 * 表明当前线程，是由 setExclusiveOwnerThread 最后设置的线程
                 */
                setState(getState()+arg);
            }
            return false;
        }
        @Override
        public boolean tryRelease(int arg){
            int state = getState()-arg;
            boolean flag = false;//初始化，释放失败
            if(state == 0){
                setExclusiveOwnerThread(null);
                return true;
            }
            setState(0);
            return super.tryRelease(arg);
        }

        public Condition newConditionObject(){
            return new ConditionObject();
        }
    }
    //获得锁
    @Override
    public void lock() {
        helper.tryAcquire(1);//以独占模式获取锁
    }

    //获取锁定，除非当前线程是 interrupted 。
    @Override
    public void lockInterruptibly() throws InterruptedException {
        helper.acquireInterruptibly(1);
    }

//    只有在调用时才可以获得锁。
    @Override
    public boolean tryLock() {
        return helper.tryAcquire(1);
    }

//    如果在给定的等待时间内是空闲的，并且当前的线程尚未得到 interrupted，则获取该锁。
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return helper.tryAcquireNanos(1,unit.toNanos(time));
    }

//    释放锁。
    @Override
    public void unlock() {
        helper.tryRelease(1);
    }

//    返回一个新Condition绑定到该实例Lock实例。条件线程
    @Override
    public Condition newCondition() {
        return helper.newConditionObject();
    }
}
