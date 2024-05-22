package com.calebstride.analysis;

import com.calebstride.analysis.collection.content.IntegerCollectionFiller;
import com.calebstride.analysis.scenarios.CollectionScenarioResult;
import com.calebstride.analysis.scenarios.CollectionTestScenarios;
import com.calebstride.analysis.scenarios.ScenarioConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        final ScenarioConfig scenarioConfig = new ScenarioConfig(500000, 1000);

        // Create filler object for integers
        IntegerCollectionFiller integerCollectionFiller = new IntegerCollectionFiller();

        CollectionScenarioResult integerArrayResult = CollectionTestScenarios.runScenarios(ArrayList::new, scenarioConfig, integerCollectionFiller);
        CollectionScenarioResult integerSetResult = CollectionTestScenarios.runScenarios(HashSet::new, scenarioConfig, integerCollectionFiller);

        LOGGER.info("The results for the collection (size: {}) scenarios (n: {}): ",
                scenarioConfig.collectionSize(), scenarioConfig.scenarioFreq());
        LOGGER.info(integerArrayResult.summary("Integer"));
        LOGGER.info(integerSetResult.summary("Integer"));
        LOGGER.info(integerArrayResult.compare(integerSetResult, "Integer"));
    }
}