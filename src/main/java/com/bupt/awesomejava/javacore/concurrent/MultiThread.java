package com.bupt.awesomejava.javacore.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class myThread extends Thread{
    private int tid;
    public myThread (int tid){
        this.tid = tid;
    }
    @Override
    public void run() {
        //super.run();
        try {
            for(int i=0;i<10;i++){
                Thread.sleep(100);
                System.out.println(String.format("T1 %d %d",tid,i));
            }
        }catch (Exception e){
            e.getMessage();
        }
    }
}

public class MultiThread {
    /**
     * 一个myThread可以理解成一个任务(一个函数段)，cpu对于这10个任务随机执行;
     */
    public static void testThread(){
        for(int i=0;i<10;i++){
            new myThread(i).start();
        }

        for(int i=0;i<10;i++){
            final int tid = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for(int i=0;i<10;i++){
                            Thread.sleep(100);
                            System.out.println(String.format("T2 %d %d",tid,i));
                        }
                    }catch (Exception e){

                    }

                }
            }).start();
        }
    }

    /**
     * Synchronized可以修饰对象也可以修饰函数;
     */
    private static Object obj = new Object();
    public static void  testSynchronized01(){
        synchronized (obj){
            try{
                for(int i=0;i<10;i++){
                    Thread.sleep(100);
                    System.out.println(String.format("T3 1 %d",i));
                }
            }catch (Exception e){
                e.getMessage();
            }

        }
    }
    public static void testSynchronized02(){
        synchronized (obj){
            //synchronized (new Object()){
            try{
                for(int i=0;i<10;i++){
                    Thread.sleep(100);
                    System.out.println(String.format("T3 2 %d",i));
                }
            }catch (Exception e){
                e.getMessage();
            }

        }
    }
    public static void testSynchronized(){
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    testSynchronized01();
                    testSynchronized02();
                }
            }).start();
        }
    }

    /**
     *  阻塞队列
     */

    static class Producer implements Runnable{
        private BlockingQueue<String> blockingQueue ;

        Producer(BlockingQueue blockingQueue){
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            try{
                for(int i=0;i<10;i++){
                    Thread.sleep(100);
                    blockingQueue.put(String.valueOf(i));
                }
            }catch (Exception e){
                e.getMessage();
            }
        }
    }

    static class Consumer implements Runnable{
        private BlockingQueue<String> blockingQueue ;

        Consumer(BlockingQueue blockingQueue){
            this.blockingQueue = blockingQueue;
        }
        @Override
        public void run() {
            try{
                while (true){
                    System.out.println(Thread.currentThread().getName()+":"+blockingQueue.take());
                }
            }catch (Exception e){
                e.getMessage();
            }
        }
    }
    public static void testBlockingQueue(){
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);
        new Thread(new Producer(blockingQueue)).start();
        new Thread(new Consumer(blockingQueue),"consumer01").start();
        new Thread(new Consumer(blockingQueue),"consumer02").start();
    }

    /**
     * Atomic:保证变量操作的原子性;
     *
     */
    private static int count = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void sleep(int mills){
        try{
            Thread.sleep(mills);
        }catch (Exception e){
            e.getMessage();
        }
    }

    public static void testWithAtomic(){
        try{
            for(int i=0;i<10;i++){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sleep(1000);
                        for(int j=0;j<10;j++){
                            System.out.println("WithAtomic:"+atomicInteger.incrementAndGet());
                        }
                    }
                }).start();
            }
        }catch (Exception e){
            e.getMessage();
        }
    }

    public static void testWithoutAtomic(){
        try{
            for(int i=0;i<10;i++){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sleep(1000);
                        for(int j=0;j<10;j++){
                            count++;
                            System.out.println("WithoutAtomic:"+count);
                        }
                    }
                }).start();
            }
        }catch (Exception e){
            e.getMessage();
        }
    }

    public static void testAtomic(){
        //testWithAtomic();
        testWithoutAtomic();
    }

    /**
     * ThreadLocal:局部线程变量互不影响;
     * 线程可以共享变量、切换开销小;
     *
     */
    private static ThreadLocal<Integer> threadLocalUserIds = new ThreadLocal<>();
    private static int userId;
    public static void testThreadLocal(){
        for(int i=0;i<10;i++){
            int finali=i;
            try{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        threadLocalUserIds.set(finali);
                        sleep(1000);
                        System.out.println("threadLocalUserIds:"+threadLocalUserIds.get());
                    }
                }).start();

            }catch (Exception e){
                e.getMessage();
            }
        }

        for(int i=0;i<10;i++){
            int finali=i;
            try{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        userId = finali;
                        sleep(1000);
                        System.out.println("NonThreadLocal: "+userId);
                    }
                }).start();

            }catch (Exception e){
                e.getMessage();
            }
        }

    }

    /**
     *
     *  executor
     */

    public static void testExcutor(){
        ExecutorService service = Executors.newFixedThreadPool(2);

        service.submit(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100;i++){
                    sleep(100);
                    System.out.println("Executor1: "+i);
                }
            }
        });

        service.submit(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100;i++){
                    sleep(100);
                    System.out.println("Executor2: "+i);
                }
            }
        });
        service.shutdown();
        while (!service.isTerminated()){
            sleep(100);
            System.out.println("waite....");
        }

    }

    /**
     * future:异步处理的框架，异步等待返回的结果;
     *        可以设置等待的时间;
     *        线程间通信获取Exception:任务执行中抛出了异常，可以在结果中显示;
     */

    public static void testFuture(){
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                sleep(1000);
                return 1;
            }
        });
        service.shutdown();
        try{
            //输出任务的执行结果；
            //System.out.println(future.get());
            //设置任务的执行时间，未在设置时间内得到结果抛出异常；
            System.out.println(future.get(10000,TimeUnit.MILLISECONDS));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testThread();
        //testSynchronized();
        //testBlockingQueue();
        //testAtomic();
        //testThreadLocal();
        //testExcutor();
        //testFuture();
    }

}
