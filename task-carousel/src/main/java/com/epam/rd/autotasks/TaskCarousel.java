package com.epam.rd.autotasks;

import java.util.Arrays;

public class TaskCarousel {
    private final int capacity;
    private int count;
    private Task[] tasks;

    public TaskCarousel(int capacity) {
        this.capacity = capacity;
        tasks = new Task[0];
    }

    public boolean addTask(Task task) {
        if (task == null || tasks.length >= capacity || task.isFinished()) {
            return false;
        }
        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = task;
        return true;
    }

    public boolean execute() {
        if (isEmpty()) return false;
        count %= tasks.length;
        tasks[count].execute();
        if (tasks[count].isFinished()) {
            Task[] copyArray = new Task[tasks.length - 1];
            System.arraycopy(tasks, 0, copyArray, 0, count);
            System.arraycopy(tasks, count + 1, copyArray, count, tasks.length - count - 1);
            tasks = copyArray;
        } else count++;
        return true;
    }

    public boolean isFull() {
        return (tasks.length == capacity);
    }

    public boolean isEmpty() {
        return (tasks.length == 0);
    }

}
