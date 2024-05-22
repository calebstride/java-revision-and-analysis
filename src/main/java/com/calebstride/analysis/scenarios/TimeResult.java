package com.calebstride.analysis.scenarios;

public class TimeResult {

    private long frequency = 0L;

    private long totalTime = 0L;

    private long maxTime = 0L;

    private long minTime = 0L;

    public TimeResult(long firstTime) {
        this.frequency = 1;
        this.totalTime = firstTime;
        this.maxTime = firstTime;
        this.minTime = firstTime;
    }

    public TimeResult() {
        // Creates an empty time result
    }

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
