package com.calebstride.analysis.shared.timing;

import org.apache.commons.lang3.time.StopWatch;
import java.util.concurrent.TimeUnit;

/**
 * Run methods wrapped in a timing
 */
public class TimeResultRunner {

    private TimeResultRunner() {
        // No-op
    }

    /**
     * Run the runnable method n times and return the average time in the timeResult
     * @param n The number of times to run
     * @param timeUnit The timeUnit to use for the average time
     * @param runnable The method to run
     * @return The average time for each method call
     */
    public static TimeResult runForAverage(int n, TimeUnit timeUnit, Runnable runnable) {
        StopWatch stopWatch = new StopWatch();
        TimeResult timeResult = new TimeResult(timeUnit);
        for (int i = 0; i < n; i++) {
            stopWatch.start();
            runnable.run();
            stopWatch.stop();
            timeResult.updateTime(stopWatch.getTime(timeUnit));
            stopWatch.reset();
        }
        return timeResult;
    }

}