package com.epam.rd.java.basic.topic05.task03;

public class Task {

    private final int numberOfThreads;
    private final int numberOfIterations;
    private final int pause;
    private int c1;
    private int c2;
    private final Thread[] threads;

    public Task(int numberOfThreads, int numberOfIterations, int pause) {
        this.threads = new Thread[numberOfThreads];
        this.numberOfThreads = numberOfThreads;
        this.numberOfIterations = numberOfIterations;
        this.pause = pause;
    }

    public void compare() {
        clearCounters();
        for (int i = 0; i < numberOfThreads; i++) {
            Thread t2 = new Thread(() -> {
                for (int j = 0; j < numberOfIterations; j++) {
                    performTask();
                }
            });
            threads[i] = t2;
            t2.start();
        }
        joinThreads();
    }

    public void compareSync() {
        clearCounters();
        for (int i = 0; i < numberOfThreads; i++) {
            Thread t2 = new Thread(() -> {
                for (int j = 0; j < numberOfIterations; j++) {
                    synchronized (this) {
                        performTask();
                    }
                }
            });
            threads[i] = t2;
            t2.start();
        }
        joinThreads();
    }

    private void performTask() {
        boolean a = c1 == c2;
        System.out.println(a + " " + c1 + " " + c2);
        c1++;
        try {
            Thread.sleep(pause);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        c2++;
    }

    private void joinThreads() {
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void clearCounters() {
        c1 = 0;
        c2 = 0;
    }

    public static void main(String[] args) {
        Task t = new Task(2, 5, 10);
        t.compare();
        System.out.println("~~~");
        t.compareSync();
    }
}
