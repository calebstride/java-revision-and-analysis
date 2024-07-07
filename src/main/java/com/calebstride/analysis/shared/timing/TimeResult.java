package com.calebstride.analysis.shared.timing;

/**
 * Describes and holds summary of a collection of timings. Allows you to retrieve minimum maximum etc.
 */
public class TimeResult {

    private long frequency = 0L;

    private long totalTime = 0L;

    private long maxTime = 0L;

    private long minTime = Long.MAX_VALUE;

    public TimeResult(long firstTime) {
        this.frequency = 1;
        this.totalTime = firstTime;
        this.maxTime = firstTime;
        this.minTime = firstTime;
    }

    public TimeResult() {
        // Creates an empty time result
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
     * @return A string showing the summary of a TimingResult
     */
    public String getSummary() {
        return STR."av. \{getAverageTime()}ns  min. \{minTime}ns  max. \{maxTime}ns";
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
