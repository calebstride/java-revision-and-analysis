package com.calebstride.analysis.collection.content.group;

import com.calebstride.analysis.collection.scenarios.ScenarioConfig;

import java.util.Collection;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/**
 * Generate a {@link CollectionGroup<U>}
 *
 * @param <U> The type the collection contains
 */
public interface CollectionGroupGenerator<U> {

    /**
     * Generates the group from the given configuration and new collection.
     *
     * @param newCollection An empty new collection
     * @param scenarioConfig The configuration for the scenario
     * @param generator The generator that will generate a value for each index
     * @return The generated collectionGroup
     */
    CollectionGroup<U> generateGroup(Supplier<Collection<U>> newCollection, ScenarioConfig<U> scenarioConfig,
            IntFunction<U> generator);

}
