package com.calebstride.analysis.scenarios;

import com.calebstride.analysis.collection.content.CollectionFiller;
import com.calebstride.analysis.collection.content.CollectionGroup;
import org.apache.commons.lang3.time.StopWatch;
import org.openjdk.jol.info.GraphLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * Runs the test scenarios on the collections
 */
public class CollectionTestScenarios {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionTestScenarios.class);

    private CollectionTestScenarios() {
        // Static class
    }

    /**
     * Run all the scenarios on the given collection a given number of times
     *
     * @param newCollectionSupplier A supplier to provide a new instance of the collection
     * @param scenarioConfig        The configuration for the scenario
     * @param collectionFiller      The class to use to fill the collection
     * @param <T>                   The type the collection contains
     * @return The result from all the scenarios
     */
    public static <T> CollectionScenarioResult runScenarios(Supplier<Collection<T>> newCollectionSupplier,
            ScenarioConfig scenarioConfig, CollectionFiller<T> collectionFiller) {
        // Create the collection as well as the collection for running scenarios with
        CollectionGroup<Collection<T>, T> collectionGroup = collectionFiller.fillCollection(newCollectionSupplier,
                scenarioConfig);
        long arraySize = GraphLayout.parseInstance(collectionGroup.collection()).totalSize();
        CollectionScenarioResult result = new CollectionScenarioResult(timeAdd(collectionGroup),
                timeRemove(collectionGroup), timeContains(collectionGroup), timeSize(collectionGroup), arraySize,
                newCollectionSupplier.get().getClass().getSimpleName());
        LOGGER.info(result.summary( "unknown"));
        return result;
    }

    private static <T> TimeResult timeAdd(CollectionGroup<Collection<T>, T> collectionGroup) {
        return genericAction(collectionGroup, Collection::add);
    }

    private static <T> TimeResult timeRemove(CollectionGroup<Collection<T>, T> collectionGroup) {
        return genericAction(collectionGroup, Collection::remove);
    }

    private static <T> TimeResult timeContains(CollectionGroup<Collection<T>, T> collectionGroup) {
        return genericAction(collectionGroup, Collection::contains);
    }

    private static <T> TimeResult timeSize(CollectionGroup<Collection<T>, T> collectionGroup) {
        return genericAction(collectionGroup, (col, _) -> col.size());
    }

    /**
     * Run a generic action a number of times. This runs the timing every time is it run
     *
     * @param collectionGroup The group of the collection for running scenarios and the base collection
     * @param scenarioAction  The scenario action being done
     * @param <T>             The type the collection contains
     * @return The timing result for the scenario
     */
    private static <T> TimeResult genericAction(CollectionGroup<Collection<T>, T> collectionGroup,
            BiConsumer<Collection<T>, T> scenarioAction) {
        StopWatch stopWatch = new StopWatch();
        TimeResult addTimeResult = new TimeResult();
        Collection<T> scenarioCollection = collectionGroup.scenarioCollection();
        for (T value : scenarioCollection) {
            stopWatch.start();
            scenarioAction.accept(collectionGroup.collection(), value);
            stopWatch.stop();
            addTimeResult.updateTime(stopWatch.getTime(TimeUnit.NANOSECONDS));
            stopWatch.reset();
        }
        return addTimeResult;
    }
}
