package com.calebstride.analysis.shared.timing;

import java.util.concurrent.TimeUnit;

/**
 * Describes and holds summary of a collection of timings. Allows you to retrieve minimum maximum etc.
 */
public class TimeResult {

    private final TimeUnit timeUnit;

    private long frequency = 0L;

    private long totalTime = 0L;

    private long maxTime = 0L;

    private long minTime = Long.MAX_VALUE;

    public TimeResult(TimeUnit timeUnit, long firstTime) {
        this.frequency = 1;
        this.totalTime = firstTime;
        this.maxTime = firstTime;
        this.minTime = firstTime;
        this.timeUnit = timeUnit;
    }

    public TimeResult(TimeUnit timeUnit) {
        // Creates an empty time result
        this.timeUnit = timeUnit;
    }

    /**
     * Add a new recorded time to the existing result
     * @param newTime The new recorded time
     */
    public void updateTime(long newTime) {
        if (newTime > this.maxTime) {
            this.maxTime = newTime;
        }
        if (newTime < this.minTime) {
            this.minTime = newTime;
        }
        frequency++;
        this.totalTime += newTime ;
    }

    /**
     * Update the time using an existing timeResult
     * @param timeResult An existing timeResult
     */
    public void updateTime(TimeResult timeResult) {
        if (maxTime < timeResult.maxTime) {
            maxTime = timeUnit.convert(timeResult.maxTime, timeResult.timeUnit);
        }
        if (minTime > timeResult.minTime) {
            minTime = timeUnit.convert(timeResult.minTime, timeResult.timeUnit);
        }
        frequency += timeResult.frequency;
        totalTime += timeUnit.convert(timeResult.totalTime, timeResult.timeUnit);
    }

    /**
     * @return A string showing the summary of a TimingResult
     */
    public String getSummary() {
        String timeUnitString = switch (timeUnit) {
            case SECONDS -> "s";
            case MILLISECONDS -> "ms";
            case NANOSECONDS -> "ns";
            case MICROSECONDS -> "us";
            case MINUTES -> "m";
            default -> "";
        };
        return STR."av. \{getAverageTime()}\{timeUnitString}  min. \{minTime}\{timeUnitString}  max. \{maxTime}\{timeUnitString}";
    }

    public long getAverageTime() {
        return totalTime / frequency;
    }

    public long getMaxTime() {
        return maxTime;
    }

    public long getMinTime() {
        return minTime;
    }
}
