package ru.kpfu.itis.group903.idrisov.daniyar.image.downloader.utils;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ThreadPool {

    private Deque<Runnable> tasks;

    private PoolWorker[] pool;

    private class PoolWorker extends Thread {

        private Runnable task;

        @Override
        public void run() {

            while (true) {
                synchronized (tasks) {
                    task = tasks.poll();
                    if (task == null) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            throw new IllegalStateException();
                        }
                    }
                }
                if (task != null) {
                    task.run();
                }
            }
        }
    }

    public void submit(Runnable task) {
        synchronized(tasks) {
            tasks.add(task);
            tasks.notifyAll();
        }
    }

    public ThreadPool(int threadsCount) {

        this.tasks = new ConcurrentLinkedDeque<>();
        this.pool = new PoolWorker[threadsCount];
        for (int i = 0; i < this.pool.length; i++) {
            this.pool[i] = new PoolWorker();
            this.pool[i].start();
        }

    }

}