package com.calebstride.analysis;

import com.calebstride.analysis.collection.content.IntegerCollectionFiller;
import com.calebstride.analysis.scenarios.results.CollectionScenarioResult;
import com.calebstride.analysis.scenarios.CollectionTestScenarios;
import com.calebstride.analysis.scenarios.ScenarioConfig;
import com.calebstride.analysis.scenarios.results.CollectionScenarioResultGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        final ScenarioConfig scenarioConfig = new ScenarioConfig(5000, 1000);

        // Create filler object for integers
        IntegerCollectionFiller integerFiller = new IntegerCollectionFiller();

        // Compare the timing of two different scenarios
        CollectionScenarioResult integerArrayResult = CollectionTestScenarios.runScenarios(ArrayList::new, scenarioConfig, integerFiller);
        CollectionScenarioResult integerSetResult = CollectionTestScenarios.runScenarios(HashSet::new, scenarioConfig, integerFiller);
        LOGGER.info("The results for the collection (size: {}) scenarios (n: {}): ",
                scenarioConfig.collectionSize(), scenarioConfig.scenarioFreq());
        String comparison = integerArrayResult.compare(integerSetResult, "Integer");
        LOGGER.info(comparison);

        // Create a table showing the timing of a few different collections
        String resultTable = new CollectionScenarioResultGroup()
                .withResult(integerArrayResult)
                .withResult(integerSetResult)
                .withResult(CollectionTestScenarios.runScenarios(TreeSet::new, scenarioConfig, integerFiller))
                .withResult(CollectionTestScenarios.runScenarios(LinkedList::new, scenarioConfig, integerFiller))
                .withResult(CollectionTestScenarios.runScenarios(ArrayDeque::new, scenarioConfig, integerFiller))
                .resultTable("Integer Collections", scenarioConfig);

        LOGGER.info(resultTable);
    }
}