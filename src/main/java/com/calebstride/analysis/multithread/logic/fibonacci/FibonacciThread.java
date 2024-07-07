package com.calebstride.analysis.multithread.logic.fibonacci;

public class FibonacciThread extends Thread {

    @Override
    public void run() {
        Fibonacci.fibonacci();
    }

}
