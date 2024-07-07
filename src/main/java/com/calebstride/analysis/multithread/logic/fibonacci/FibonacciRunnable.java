package com.calebstride.analysis.multithread.logic.fibonacci;

public class FibonacciRunnable implements Runnable {

    @Override
    public void run() {
        Fibonacci.fibonacci();
    }

}
