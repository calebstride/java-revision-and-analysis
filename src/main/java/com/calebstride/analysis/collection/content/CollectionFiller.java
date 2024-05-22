package com.calebstride.analysis.collection.content;

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
     * @param <T> The group of collections containing the collection and the values to use in scenarios
     */
    <T extends Collection<U>> CollectionGroup<T, U> fillCollection(Supplier<T> newCollection, ScenarioConfig scenarioConfig);


}
