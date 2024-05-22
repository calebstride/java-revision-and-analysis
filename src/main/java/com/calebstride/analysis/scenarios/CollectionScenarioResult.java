package com.calebstride.analysis.scenarios;

public class CollectionScenarioResult {

    private final TimeResult timeToAdd;

    private final TimeResult timeToRemove;

    private final TimeResult timeToContains;

    private final TimeResult timeToSize;

    public CollectionScenarioResult(TimeResult timeToAdd, TimeResult timeToRemove, TimeResult timeToContains, TimeResult timeToSize) {
        this.timeToAdd = timeToAdd;
        this.timeToRemove = timeToRemove;
        this.timeToContains = timeToContains;
        this.timeToSize = timeToSize;
    }

    public TimeResult getTimeToAdd() {
        return timeToAdd;
    }

    public TimeResult getTimeToRemove() {
        return timeToRemove;
    }

    public TimeResult getTimeToContains() {
        return timeToContains;
    }

    public TimeResult getTimeToSize() {
        return timeToSize;
    }
}
