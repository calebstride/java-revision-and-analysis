package com.calebstride.analysis.scenarios;

import org.apache.commons.lang3.time.StopWatch;

import java.util.Collection;
import java.util.function.Consumer;

public class CollectionTestScenarios {

    private CollectionTestScenarios() {
        // Static class
    }

    public static <T> CollectionScenarioResult runScenarios(Collection<T> collection, int times) {
        return new CollectionScenarioResult(timeAdd(collection, times), timeRemove(collection, times),
                timeContains(collection, times), timeSize(collection, times));
    }

    private static <T> TimeResult timeAdd(Collection<T> collection, int times) {
        T valueToAdd = collection.iterator().next();
        return genericAction(collection, times, col -> col.add(valueToAdd));
    }

    private static <T> TimeResult timeRemove(Collection<T> collection, int times) {
        T valueToRemove = collection.iterator().next();
        return genericAction(collection, times, col -> col.remove(valueToRemove));
    }

    private static <T> TimeResult timeContains(Collection<T> collection, int times) {
        T valueToCheck = collection.iterator().next();
        return genericAction(collection, times, col -> col.contains(valueToCheck));
    }

    private static <T> TimeResult timeSize(Collection<T> collection, int times) {
        return genericAction(collection, times, Collection::size);
    }

    private static <T> TimeResult genericAction(Collection<T> collection, int times,
            Consumer<Collection<T>> scenarioAction) {
        StopWatch stopWatch = new StopWatch();
        TimeResult addTimeResult = new TimeResult();
        for (int i = 0; i < times; i++) {
            stopWatch.start();
            scenarioAction.accept(collection);
            stopWatch.stop();
            addTimeResult.updateTime(stopWatch.getTime());
        }
        return addTimeResult;
    }
}
