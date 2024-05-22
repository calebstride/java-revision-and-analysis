package com.calebstride.analysis.scenarios;

/**
 * The config for running the scenarios
 * @param collectionSize The size of the collections
 * @param scenarioFreq The number of times to run each scenario
 */
public record ScenarioConfig(int collectionSize, int scenarioFreq) {
}
