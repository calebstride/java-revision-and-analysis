package com.calebstride.analysis.collection.content;

import com.calebstride.analysis.scenarios.ScenarioConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Fills a collection with ordered Integer values
 */
public class IntegerCollectionFiller implements CollectionFiller<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntegerCollectionFiller.class);

    /**
     * Fill the collection with incrementing integer values.
     *
     * @param newCollection  A supplier to return an empty collection
     * @param scenarioConfig The scenario configuration
     * @param <T>            The type of the collection
     * @return The group of collections containing the collection and the values to use in scenarios
     */
    @Override
    public <T extends Collection<Integer>> CollectionGroup<T, Integer> fillCollection(Supplier<T> newCollection,
            ScenarioConfig scenarioConfig) {

        int combinedLength = scenarioConfig.collectionSize() + scenarioConfig.scenarioFreq();
        int valueGap = combinedLength / scenarioConfig.scenarioFreq();
        T collection = newCollection.get();
        T scenarioCollection = newCollection.get();
        for (int i = 0; i < combinedLength; i++) {
            if (i % valueGap == 0 && scenarioCollection.size() < scenarioConfig.scenarioFreq()) {
                scenarioCollection.add(i);
            } else {
                collection.add(i);
            }
        }
        if (scenarioConfig.scenarioFreq() != scenarioCollection.size()) {
            LOGGER.error("The size of the scenario collection {} is not equal to the provided scenario frequency {}",
                    scenarioCollection.size(), scenarioConfig.scenarioFreq());
            throw new RuntimeException("The size of the scenario collection is not equal to the provided scenario frequency");
        }
        return new CollectionGroup<>(collection, scenarioCollection);
    }

}
