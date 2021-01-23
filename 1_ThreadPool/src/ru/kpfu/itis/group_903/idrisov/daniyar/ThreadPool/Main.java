package ru.kpfu.itis.group_903.idrisov.daniyar.ThreadPool;

public class Main {


    public static void main(String[] args) {

        ThreadPool threadPool = new ThreadPool(4);

        Runnable r1 = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(1 + " " + Thread.currentThread().getName());
            }
        };

        Runnable r2 = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(2 + " " + Thread.currentThread().getName());
            }
        };

        Runnable r3 = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(3 + " " + Thread.currentThread().getName());
            }
        };

        Runnable r4 = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(4 + " " + Thread.currentThread().getName());
            }
        };

        Runnable r5 = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(5 + " " + Thread.currentThread().getName());
            }
        };

        Runnable r6 = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(6 + " " + Thread.currentThread().getName());
            }
        };

        threadPool.submit(r1);
        threadPool.submit(r2);
        threadPool.submit(r3);
        threadPool.submit(r4);
        threadPool.submit(r5);
        threadPool.submit(r6);
    }

}

