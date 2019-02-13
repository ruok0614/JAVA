/*
 * Copyright (C) 2012, 2013, 2016 RICOH Co., Ltd. All rights reserved.
 */
package jpl.ThreadPool;


import jpl.ch14.ex09.DispThreadGroup;

import java.util.ArrayList;
import java.util.List;

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
    private List<Runnable> queue;
    private boolean isActive;
    private DispatchThread threads[];


    /**
     * Constructs ThreadPool.
     *
     * @param queueSize       the max size of queue
     * @param numberOfThreads the number of threads in this pool.
     * @throws IllegalArgumentException if either queueSize or numberOfThreads
     *                                  is less than 1
     */
    public ThreadPool(int queueSize, int numberOfThreads) {
        if ((queueSize < 1) || numberOfThreads < 1) {
            throw new IllegalArgumentException("Not Implemented Yet");
        }
        this.numberOfThreads = numberOfThreads;
        this.queueSize = queueSize;
        this.queue = new ArrayList<Runnable>();
        this.isActive = false;
        threads = new DispatchThread[numberOfThreads];
    }

    /**
     * Starts threads.
     *
     * @throws IllegalStateException if threads has been already started.
     */
    public synchronized void start() {
        // スタートを並列に読んだときに通らないようにする。
        synchronized (this) {
            if (this.isActive)
                throw new IllegalStateException("threads has been already started.");
        }

        for (int i = 0; i < numberOfThreads; i++) {
            try {
                threads[i] = new DispatchThread(queue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threads[i].start();
        }
        this.isActive = true;
    }

    /**
     * Stop all threads and wait for their terminations.
     *
     * @throws IllegalStateException if threads has not been started.
     */
    public synchronized void stop() {
        if(!isActive){
            throw new IllegalStateException("threads has not been started.");
        }
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i].stopThread();
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Executes the specified Runnable object, using a thread in the pool.
     * run() method will be invoked in the thread. If the queue is full, then
     * this method invocation will be blocked until the queue is not full.
     *
     * @param runnable Runnable object whose run() method will be invoked.
     * @throws NullPointerException  if runnable is null.
     * @throws IllegalStateException if this pool has not been started yet.
     */
    public synchronized void dispatch(Runnable runnable) {
        if (runnable == null)
            throw new NullPointerException("Not Implemented Yet");
        if (!this.isActive)
            throw new IllegalStateException("Not Implemented Yet");

        queue.add(runnable);
        synchronized (queue) {
            queue.notify();
        }
    }

    /*
    ランナブルが入ったキューを
     */
    private static class DispatchThread extends Thread {
        private List<Runnable> queue;
        private boolean isStop;

        DispatchThread(List<Runnable> queue) throws InterruptedException {
            this.queue = queue;
            isStop = false;
        }
        public void run() {
            // スリープ入れる
            while (!isStop || queue.size() > 0) {
                Runnable runnable = null;
                synchronized (this.queue) {
                    if (this.queue.size() > 0) {
                        runnable = this.queue.remove(0);
                    }else{
                        try {
                            this.queue.wait(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (runnable != null) {
                    runnable.run();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        void stopThread(){
            synchronized (queue){
                queue.notifyAll();
            }
                isStop = true;
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

