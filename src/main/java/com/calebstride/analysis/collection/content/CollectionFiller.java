package com.calebstride.analysis.collection.content;

import com.calebstride.analysis.collection.content.group.CollectionGroup;
import com.calebstride.analysis.scenarios.ScenarioConfig;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Used to provide methods to fill a collection with values
 * @param <U> The object type the collection holds
 */
public interface CollectionFiller<U> {

    /**
     * Fills the given collection with size values
     *
     * @param newCollection A supplier to return a new collection
     * @param scenarioConfig The configuration for the scenarios
     * @return The collection with the new values inserted
     */
    CollectionGroup<U> fillCollection(Supplier<Collection<U>> newCollection, ScenarioConfig<U> scenarioConfig);


    /**
     * Generate a value based off an index
     *
     * @param index The index signifying the xth element to be generated
     * @return The generated value
     */
    U generateValue(int index);

    /**
     * Get a nice name for the type
     */
    String typeName();
}
