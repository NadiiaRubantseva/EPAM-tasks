package com.epam.rd.autotasks;

public class CountDownTask implements Task {
    private int value;
    private boolean isFinished;

    public CountDownTask(int value) {
        if (value <= 0) {
            this.value = 0;
            this.isFinished = true;
        } else {
            this.value = value;
            this.isFinished = false;
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public void execute() {
        if (isFinished) {
            return;
        }
        if (value > 0) {
            value--;
        }
        if (value == 0) {
            isFinished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
