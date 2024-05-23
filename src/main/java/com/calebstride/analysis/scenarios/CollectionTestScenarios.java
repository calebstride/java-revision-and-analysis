package com.calebstride.analysis.scenarios;

import com.calebstride.analysis.collection.content.group.CollectionGroup;
import com.calebstride.analysis.scenarios.results.CollectionScenarioResult;
import com.calebstride.analysis.scenarios.results.TimeResult;
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
     * @param <T>                   The type the collection contains
     * @return The result from all the scenarios
     */
    public static <T> CollectionScenarioResult runScenarios(Supplier<Collection<T>> newCollectionSupplier,
            ScenarioConfig<T> scenarioConfig) {
        // Create the collection as well as the collection for running scenarios with
        CollectionGroup<T> collectionGroup = scenarioConfig.collectionFiller()
                .fillCollection(newCollectionSupplier, scenarioConfig);
        long arraySize = GraphLayout.parseInstance(collectionGroup.collection()).totalSize();
        CollectionScenarioResult result = new CollectionScenarioResult(timeAdd(collectionGroup),
                timeRemove(collectionGroup), timeContains(collectionGroup), timeSize(collectionGroup), arraySize,
                newCollectionSupplier.get().getClass().getSimpleName());
        LOGGER.info(result.summary(scenarioConfig.collectionFiller().typeName()));
        return result;
    }

    private static <T> TimeResult timeAdd(CollectionGroup<T> collectionGroup) {
        return genericAction(collectionGroup.collection(), collectionGroup.add(), Collection::add);
    }

    private static <T> TimeResult timeRemove(CollectionGroup<T> collectionGroup) {
        return genericAction(collectionGroup.collection(), collectionGroup.remove(), Collection::remove);
    }

    private static <T> TimeResult timeContains(CollectionGroup<T> collectionGroup) {
        return genericAction(collectionGroup.collection(), collectionGroup.contains(), Collection::contains);
    }

    private static <T> TimeResult timeSize(CollectionGroup<T> collectionGroup) {
        return genericAction(collectionGroup.collection(), collectionGroup.add(), (col, _) -> col.size());
    }

    /**
     * Run a generic action a number of times. This runs the timing every time is it run
     *
     * @param collection         The base collection
     * @param scenarioCollection The collection to use for this scenario
     * @param scenarioAction     The scenario action being done
     * @param <T>                The type the collection contains
     * @return The timing result for the scenario
     */
    private static <T> TimeResult genericAction(Collection<T> collection, Collection<T> scenarioCollection,
            BiConsumer<Collection<T>, T> scenarioAction) {
        StopWatch stopWatch = new StopWatch();
        TimeResult addTimeResult = new TimeResult();
        for (T value : scenarioCollection) {
            stopWatch.start();
            // This probably isn't the best way to run different methods and might add some overhead. But it should be
            // a constant overhead so comparisons shouldn't be effected.
            scenarioAction.accept(collection, value);
            stopWatch.stop();
            addTimeResult.updateTime(stopWatch.getTime(TimeUnit.NANOSECONDS));
            stopWatch.reset();
        }
        return addTimeResult;
    }
}
