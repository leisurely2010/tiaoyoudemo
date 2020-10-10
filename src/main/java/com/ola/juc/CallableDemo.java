package com.ola.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Callable{

    @Override
    public Integer call() throws Exception {
        System.out.println("**********in call method");
        return 1024;
    }
}
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Thread线程要求一个runnable接口
        //FutureTask既实现了runnable接口，构造函数中又有了callable接口,
        //所以可以用来连接Callable和Thread

        FutureTask<Integer> futureTask=new FutureTask(new MyThread());
        Thread thread=new Thread(futureTask);
        thread.start();
        Integer result=futureTask.get();
        System.out.println(result);
    }
}
