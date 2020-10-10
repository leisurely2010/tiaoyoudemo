package com.ola.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个人卖30张票
 */
class Ticket {
    int num=30;
    public void sell(){
        Lock lock=new ReentrantLock();
        //线程操作资源的地方需要上锁
        lock.lock();
        try {
            if(num>0){
                System.out.println(Thread.currentThread().getName()+"线程卖出了"+num--+",还剩"+num);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class TicketLockDemo {
    public static void main(String[] args) {
        Ticket ticket=new Ticket();
        new Thread(()->{ for (int i=0;i<40;i++) ticket.sell(); },"A").start();
        new Thread(()->{ for (int i=0;i<40;i++) ticket.sell(); },"B").start();
        new Thread(()->{ for (int i=0;i<40;i++) ticket.sell(); },"C").start();
    }
}
