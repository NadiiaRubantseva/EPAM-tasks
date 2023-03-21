package com.epam.rd.autotasks;

public class CompleteByRequestTask implements Task {

    private boolean isFinished = false;
    private boolean isCompleted = false;

    @Override
    public void execute() {
        if (isCompleted) {
            isFinished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    public void complete() {
        isCompleted = true;
    }
}
