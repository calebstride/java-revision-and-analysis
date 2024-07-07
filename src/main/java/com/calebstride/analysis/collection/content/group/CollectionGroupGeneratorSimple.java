package com.calebstride.analysis.collection.content.group;

import com.calebstride.analysis.scenarios.ScenarioConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/**
 * Generate a collection group. This class creates a group of values where the list of scenarios are evenly spread
 * throughout the main collection. It misses some scenarios, like values that aren't in the array for remove and
 * contains
 *
 * @param <U> The type of value in the collection
 */
public class CollectionGroupGeneratorSimple<U> implements CollectionGroupGenerator<U> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionGroupGeneratorSimple.class);

    /**
     * Create a group where each scenario will run a range of good / bad scenarios. E.g. contains will look for elements
     * and start as well as end.
     *
     * @param newCollection An empty new collection
     * @param scenarioConfig The configuration for the scenario
     * @param generator The generator that will generate a value for each index
     * @return A group where the scenario collection contains values that will be evenly spread throughout the list
     */
    @Override
    public CollectionGroup<U> generateGroup(Supplier<Collection<U>> newCollection, ScenarioConfig<U> scenarioConfig,
            IntFunction<U> generator) {
        int combinedLength = scenarioConfig.collectionSize() + (scenarioConfig.scenarioFreq() * 2);
        int valueGap = combinedLength / scenarioConfig.scenarioFreq();
        Collection<U> collection = newCollection.get();
        Collection<U> addCollection = newCollection.get();
        Collection<U> removeCollection = newCollection.get();
        Collection<U> containsCollection = newCollection.get();
        for (int i = 0; i < combinedLength; i++) {
            int remainder = i % valueGap;
            U value = generator.apply(i);
            if (remainder == 0 && addCollection.size() < scenarioConfig.scenarioFreq()) {
                addCollection.add(value);
            } else if (remainder == 1 && removeCollection.size() < scenarioConfig.scenarioFreq()) {
                removeCollection.add(value);
            } else if (remainder == 2 && containsCollection.size() < scenarioConfig.scenarioFreq()) {
                containsCollection.add(value);
                collection.add(value);
            } else {
                collection.add(value);
            }
        }
        if (scenarioConfig.scenarioFreq() != addCollection.size() || scenarioConfig.scenarioFreq() != removeCollection.size()
                || scenarioConfig.scenarioFreq() != containsCollection.size()) {
            LOGGER.error("The size of a scenario collection is not equal to the provided scenario frequency {}",
                    scenarioConfig.scenarioFreq());
            throw new RuntimeException(
                    "The size of the scenario collection is not equal to the provided scenario frequency");
        }
        return new CollectionGroup<>(collection, addCollection, removeCollection, containsCollection);
    }
}
