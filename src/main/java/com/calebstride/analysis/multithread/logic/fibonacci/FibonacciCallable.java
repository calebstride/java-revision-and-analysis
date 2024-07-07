package com.calebstride.analysis.multithread.logic.fibonacci;

import java.util.concurrent.Callable;

public class FibonacciCallable implements Callable<Integer> {

    private final int fibonacciValue;

    public FibonacciCallable(int fibonacciValue) {
        this.fibonacciValue = fibonacciValue;
    }

    @Override
    public Integer call() {
        return Fibonacci.fibonacci(fibonacciValue + 100_000_000);
    }

}
