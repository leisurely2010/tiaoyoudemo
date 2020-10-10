package com.ola.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程操作初始值为0的一个变量
 * 一个线程+1，一个线程-1
 *实现交替，来10轮
 *
 *         //线程操作资源类
 *         //判断/干活/通知
 *         //避免虚假通知
 */
class MyFood{
    int num=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    void increment() {
        lock.lock();
        try {
            while (num!=0){
                condition.await();
            }
            num++;
            condition.signalAll();
            System.out.println(Thread.currentThread().getName()+"线程生产后数量："+num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    void decrement() {
        lock.lock();
        try {
            while (num==0){
                condition.await();
            }
            num--;
            condition.signalAll();
            System.out.println(Thread.currentThread().getName()+"线程消费后数量："+num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class ProductLockDemo {
    public static void main(String[] args) {
        MyFood food=new MyFood();
        new Thread(()->{ for (int i = 0; i < 10; i++) { food.increment(); } },"A").start();
        new Thread(()->{ for (int i = 0; i < 10; i++) { food.decrement(); } },"B").start();
    }
}
