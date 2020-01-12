/*
 * Copyright (C) 2012, 2013 RICOH Co., Ltd. All rights reserved.
 * Copyright (C) 2019 Yoshiki Shibata. All rights reserved.
 */
package ch14.ex10;

/**
 * Simple Thread Pool class.
 *
 * This class can be used to dispatch an Runnable object to
 * be executed by a thread.<br><br>
 *
 * [Instruction]
 * <ul>
 *  <li> Implement one constructor and three methods. </li>
 *  <li> Don't forget to write a Test program to test this class. </li>
 *  <li> Pay attention to @throws tags in the javadoc. </li>
 *  <li> If needed, you can put "synchronized" keyword to methods. </li>
 *  <li> All classes for implementation must be private inside this class. </li>
 *  <li> Don't use java.util.concurrent package. </li>
 *  <li> Don't use {@link java.lang.Thread#interrupt}  method to stop a thread</li>
 *  </ul>
 *
 *  @author Yoshiki Shibata
 */
public class ThreadPool {

    private int queueSize;
    private int numberOfThreads;
    private Thread[] threads;
    private volatile boolean[] fragStart;
    private Runnable[] runnables;

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
        if (queueSize < 1 || numberOfThreads < 1) {
            throw new IllegalArgumentException();
        }
        this.queueSize = queueSize;
        this.numberOfThreads = numberOfThreads;
        threads = new Thread[queueSize];
        runnables = new Runnable[queueSize];
        this.fragStart = new boolean[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> {
                while (fragStart[finalI]) {
                    if (runnables[finalI] != null) {
                        runnables[finalI].run();
                    }
                }
            });
        }
    }

    /**
     * Starts threads.
     *
     * @throws IllegalStateException if threads has been already started.
     */
    synchronized public void start() {
        for (int i = 0; i < numberOfThreads; i++) {
            if (threads[i] == null) {
                continue;
            }
            if (threads[i].isAlive()) {
                throw new IllegalStateException();
            }
            fragStart[i] = true;
            threads[i].start();
        }
    }

    /**
     * Stop all threads gracefully and wait for their terminations.
	 * All requests dispatched before this method is invoked must complete
	 * and this method also will wait for their completion.
     *
     * @throws IllegalStateException if threads has not been started.
     */
    synchronized public void stop() {
        for (int i = 0; i < numberOfThreads; i++) {
            Thread thread = threads[i];
            if (thread == null) {
                continue;
            }
            if (!thread.isAlive()) {
                throw new IllegalStateException();
            }
            try {
                fragStart[i] = false;

                thread.join();
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
     *
     * @throws NullPointerException if runnable is null.
     * @throws IllegalStateException if this pool has not been started yet.
     */
    synchronized public void dispatch(Runnable runnable) {
        if (runnable == null) {
            throw new NullPointerException();
        }
        if (!fragStart[0]) {
            throw new IllegalStateException();
        }
        for (int i = 0; i < numberOfThreads; i++) {
            if (this.runnables[i] == null) {
                this.runnables[i] = runnable;
                break;
            }
        }
    }
}
