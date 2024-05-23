package com.calebstride.analysis.scenarios;

import com.calebstride.analysis.collection.content.CollectionFiller;

/**
 * The config for running the scenarios
 * @param collectionSize The size of the collections
 * @param scenarioFreq The number of times to run each scenario
 * @param <T> The type that fills the collection
 */
public record ScenarioConfig<T>(int collectionSize, int scenarioFreq, CollectionFiller<T> collectionFiller) {
}
