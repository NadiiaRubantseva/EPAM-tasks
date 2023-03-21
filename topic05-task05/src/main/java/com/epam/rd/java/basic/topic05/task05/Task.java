package com.epam.rd.java.basic.topic05.task05;

import java.io.*;
import java.nio.file.*;

public class Task {
    public static final String FILE_NAME = "data.txt";
    private static RandomAccessFile raf;

    public static void createRAF(int numberOfThreads, int numberOfIterations, int pause) throws IOException {
        Thread[] threads = new Thread[numberOfThreads];
        File file = new File(FILE_NAME);
        resetContent(file);
        raf = new RandomAccessFile(file, "rw");
        fillLines(numberOfThreads, numberOfIterations, pause, threads);
        raf.close();
    }

    private static void fillLines(int numberOfThreads, int numberOfIterations, int pause, Thread[] threads) {
        for (int i = 0; i < numberOfThreads; i++) {
            final int line = i;
            threads[i] = new Thread(() -> fillLine(line, pause, numberOfIterations));
            threads[i].start();
        }
        joinThreads(threads);
    }

    private static void joinThreads(Thread[] threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void resetContent(File file) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();
    }

    private static void fillLine(int line, int pause, int numberOfIterations) {
        for (int j = 0; j < numberOfIterations; j++) {
            try {
                insertValue(line, j, numberOfIterations + 1);
                Thread.sleep(pause);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static synchronized void insertValue(int line, int j, int numberOfIterations) throws IOException {
        raf.seek((long) line * numberOfIterations + j);
        if (j != numberOfIterations) {
            raf.write(String.valueOf(line).getBytes());
        }
        raf.write('\n');
    }

    public static void main(String[] args) throws IOException {
        createRAF(5, 20, 10);

        Files.readAllLines(Paths.get(FILE_NAME))
                .stream()
                .forEach(System.out::println);
    }
}
