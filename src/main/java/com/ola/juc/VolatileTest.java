package com.ola.juc;

public class VolatileTest {
    static class Mynumber{
        volatile int num=10;
        void addto1205(){
            num=1205;
        }
    }
    public static void main(String[] args) {
        Mynumber number=new Mynumber();
        new Thread(()->{
            System.out.println("***********come in ");
            try {
                Thread.sleep(3000);
                number.addto1205();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        }

        ,"AAA").start();
        while (number.num==10){

        }
        System.out.println("game over!");
    }
}
