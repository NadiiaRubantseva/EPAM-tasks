package com.epam.rd.autotasks.sprintplanning.tickets;

public class Ticket {
    protected int id;
    protected String name;
    protected int estimate;
    protected boolean isCompleted;


    public Ticket(int id, String name, int estimate) {
        this.id = id;
        this.name = name;
        this.estimate = estimate;
        this.isCompleted = false;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void complete() {
        this.isCompleted = true;
    }

    public int getEstimate() {
        return this.estimate;
    }
}
