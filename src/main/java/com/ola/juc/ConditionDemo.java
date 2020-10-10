package com.ola.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程按顺序调用A->B->C
 * A线程调用5次，B线程调用10次，C线程调用15次
 * 来10轮
 *
 * 线程操作资源类
 * 判断/干活/通知
 * 避免虚假通知
 */
class ShareData{
    int type=1;//1A线程2B线程3C线程
    Lock lock =new ReentrantLock();
    //一把锁配三把钥匙
    Condition c1=lock.newCondition();
    Condition c2=lock.newCondition();
    Condition c3=lock.newCondition();
    void print5()  {
        lock.lock();
        try {
            while(type!=1){
                c1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+i);
            }
            type=2;
            c2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    void print10()  {
        lock.lock();
        try {
            while(type!=2){
                c2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+i);
            }
            type=3;
            c3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    void print15()  {
        lock.lock();
        try {
            while(type!=3){
                c3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+i);
            }
            type=1;
            c1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class ConditionDemo {
    public static void main(String[] args) {
        ShareData ShareData=new ShareData();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                ShareData.print5();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                ShareData.print10();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                ShareData.print15();
            }
        },"C").start();
    }

}
