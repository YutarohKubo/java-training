/*
 * Copyright (C) 2012, 2013 RICOH Co., Ltd. All rights reserved.
 * Copyright (C) 2019 Yoshiki Shibata. All rights reserved.
 */
package ch14.ex10;

import java.util.ArrayDeque;

/**
 * Simple Thread Pool class.
 *
 * This class can be used to dispatch an Runnable object to
 * be exectued by a thread.<br><br>
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
    private ArrayDeque<Thread> threads;

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
        threads = new ArrayDeque<>(queueSize);
        for (int i = 0; i < numberOfThreads; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j < 10000; j++) {

                }
            }));
        }
    }

    /**
     * Starts threads.
     *
     * @throws IllegalStateException if threads has been already started.
     */
    public void start() {
        for (Thread thread : threads) {
            if (thread == null) {
                continue;
            }
            if (thread.isAlive()) {
                throw new IllegalStateException();
            }
            thread.start();
        }
    }

    /**
     * Stop all threads gracefully and wait for their terminations.
	 * All requests dispatched before this method is invoked must complete
	 * and this method also will wait for their completion.
     *
     * @throws IllegalStateException if threads has not been started.
     */
    public void stop() {
        for (Thread thread : threads) {
            if (thread == null) {
                continue;
            }
            if (!thread.isAlive()) {
                throw new IllegalStateException();
            }
            try {
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
    public void dispatch(Runnable runnable) {
        throw new AssertionError("Not Implemented Yet");
    }
}
