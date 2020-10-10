package com.ola.juc;
/**
 * 两个线程操作初始值为0的一个变量
 * 一个线程+1，一个线程-1
 *实现交替，来10轮
 *
 *         //线程操作资源类
 *         //判断/干活/通知
 *         //避免虚假通知
 */
class Food{
    int num=0;
    synchronized void increment() throws InterruptedException {
        while(num!=0){
            wait();
        }
        num++;
        notifyAll();
        System.out.println(Thread.currentThread().getName()+"线程生产后数量："+num);
    }
    synchronized  void decrement() throws InterruptedException {
        while (num==0){
            wait();
        }

        num--;
        notifyAll();
        System.out.println(Thread.currentThread().getName()+"线程消费后数量："+num);
    }
}
public class ProductSynchronizedDemo {
    public static void main(String[] args) {
        Food food=new Food();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    food.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    food.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
    }


}
