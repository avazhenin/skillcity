package com.edu.skillcity.utils;

public enum Statuses {
    new_(0),
    viewed(1);

    private final int status;

    private Statuses(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }
}
