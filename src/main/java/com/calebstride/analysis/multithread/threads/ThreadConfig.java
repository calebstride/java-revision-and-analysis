package com.calebstride.analysis.multithread.threads;

public record ThreadConfig(int numberOfThreads, int numberOfTasks) {

    public ThreadConfig(int numberOfThreads) {
        this(numberOfThreads, 1000);
    }

}
