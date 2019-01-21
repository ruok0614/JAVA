/*
 * Copyright (C) 2012, 2013, 2016 RICOH Co., Ltd. All rights reserved.
 */
package ThreadPool;


import jpl.ch14.ex09.DispThreadGroup;

/**
 * Simple Thread Pool class.
 *
 * This class can be used to dispatch an Runnable object to
 * be exectued by a thread.
 *
 * [Instruction]
 *  Implement one constructor and three methods.
 *  Don't forget to write a Test program to test this class. 
 *  Pay attention to @throws tags in the javadoc.
 *  If needed, you can put "synchronized" keyword to methods.
 *  All classes for implementation must be private inside this class.
 *  Don't use java.util.concurrent package.
 *  まずは例外系を作る。
 */
public class ThreadPool {

    private final int queueSize;
    private final int numberOfThreads;
    private final List<Runnable> queue;


    /**
     * Constructs ThreadPool.
     *
     * @param queueSize the max size of queue
     * @param numberOfThreads the number of threads in this pool.
     *
     * @throws IllegalArgumentException if either queueSize or numberOfThreads
     *         is less than 1
     */
    public ThreadPool(int queueSize, int numberOfThreads) {
        if ((queueSize < 1) || numberOfThreads < 1) {
            throw new AssertionError("Not Implemented Yet");
        }
        this.numberOfThreads = numberOfThreads;
        this.queueSize = queueSize;
    }

    /**
     * Starts threads.
     *
     * @throws IllegalStateException if threads has been already started.
     */
    public void start() {
        // スタートを並列に読んだときに通らないようにする。
        synchronized (this) {
            if(!this.isActive)
                throw new AssertionError("Not Implemented Yet");
            this.isActive = fales;
        }

        for(){
            Thread thread = new DispatchThread(queue);
            thread.start();
        }
    }

    /**
     * Stop all threads and wait for their terminations.
     *
     * @throws IllegalStateException if threads has not been started.
     */
    public void stop() {
       	throw new AssertionError("Not Implemented Yet");
    }

    /**
     * Executes the specified Runnable object, using a thread in the pool.
     * run() method will be invoked in the thread. If the queue is full, then
     * this method invocation will be blocked until the queue is not full.
     * 
     * @param runnable Runnable object whose run() method will be invoked.
     *
     * @throws NullPointerException if runnable is null.
     * @throws IllegalStateException if this pool has not been started yet.
     */
    public synchronized void dispatch(Runnable runnable) {
        if(runnable == null)
            throw new NullPointerException();

    }
    /*
    ランナブルが入ったキューを
     */
    private static class DispatchThread extends Thread{
        DispatchThread(List<Runnable> queue){
            this = queue
        }
        // スリープ入れる
        while(ture){
            Runnable runnable = null;
            synchronized (this.queue){
                if(this.queue.size() > 0){
                    runnable = this.queue.remove(0);
                }else{
                    ??.wait();
                }
            }
            if(runnable != null){
                runnable.run();
            }
        }
    }
}

/**
 * キューがいっぱいのときはwqit,notifyを使う
 * ストップを読んだらすべてのすれっとを止める。
 * すれっとを削除するのをどのタイミングでやるのか
 * 　キューが0だけだめ。
 * 　
 */

