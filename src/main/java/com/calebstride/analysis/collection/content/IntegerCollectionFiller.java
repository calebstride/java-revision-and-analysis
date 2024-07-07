package com.calebstride.analysis.collection.content;

import com.calebstride.analysis.collection.content.group.CollectionGroup;
import com.calebstride.analysis.collection.content.group.CollectionGroupGeneratorSimple;
import com.calebstride.analysis.collection.scenarios.ScenarioConfig;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Fills a collection with ordered Integer values
 */
public class IntegerCollectionFiller implements CollectionFiller<Integer> {

    // Separate out the simple generator as I might want to replace this later on
    private final CollectionGroupGeneratorSimple<Integer> groupGenerator =
            new CollectionGroupGeneratorSimple<>();

    /**
     * Fill the collection with incrementing integer values.
     *
     * @param newCollection  A supplier to return an empty collection
     * @param scenarioConfig The scenario configuration
     * @return The group of collections containing the collection and the values to use in scenarios
     */
    @Override
    public CollectionGroup<Integer> fillCollection(Supplier<Collection<Integer>> newCollection,
            ScenarioConfig<Integer> scenarioConfig) {
        return groupGenerator.generateGroup(newCollection, scenarioConfig, this::generateValue);
    }

    /**
     * Generates a sequential integer
     * @param index The index signifying the xth element to be generated
     * @return The new integer
     */
    @Override
    public Integer generateValue(int index) {
        return index + 1;
    }

    @Override
    public String typeName() {
        return "Integer";
    }
}
