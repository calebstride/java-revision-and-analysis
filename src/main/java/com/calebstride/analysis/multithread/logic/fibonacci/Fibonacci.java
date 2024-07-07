package com.calebstride.analysis.multithread.logic.fibonacci;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to hold fibonacci logic
 */
public class Fibonacci {

    private static final Logger LOGGER = LoggerFactory.getLogger(Fibonacci.class);

    private Fibonacci() {
        // No op
    }

    /**
     * A default call of fibonacci at position 10.
     *
     * @return The value in the fibonacci sequence at position 10
     */
    public static int fibonacci() {
        return fibonacci(10_000_000);
    }

    /**
     * Figure out the number in the fibonacci sequence at position n (1 indexed)
     *
     * @param n the index of the number you want to find in the fibonacci sequence
     * @return The number at position n
     */
    public static int fibonacci(int n) {
        if (n == 1) return 0;
        int num1 = 0;
        int num2 = 1;
        for (int i = 2; i < n; i++) {
            int num3 = num1 + num2;
            num1 = num2;
            num2 = num3;
        }
        LOGGER.trace("Fibonacci number is {}", num1);
        return num2;
    }

}
