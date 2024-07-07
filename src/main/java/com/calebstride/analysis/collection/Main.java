package com.calebstride.analysis.collection;

import com.calebstride.analysis.collection.content.CollectionFiller;
import com.calebstride.analysis.collection.content.ContactCollectionFiller;
import com.calebstride.analysis.collection.contact.Contact;
import com.calebstride.analysis.collection.scenarios.CollectionTestScenarios;
import com.calebstride.analysis.collection.scenarios.ScenarioConfig;
import com.calebstride.analysis.collection.scenarios.results.CollectionScenarioResult;
import com.calebstride.analysis.collection.scenarios.results.CollectionScenarioResultTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        // Create filler object for integers
        //CollectionFiller<Integer> filler = new IntegerCollectionFiller();
        //ScenarioConfig<Integer> scenarioConfig = new ScenarioConfig<>(500000, 1000, filler);

        // Create filler object for contact
        CollectionFiller<Contact> filler = new ContactCollectionFiller();
        ScenarioConfig<Contact> scenarioConfig = new ScenarioConfig<>(5000, 1000, filler);

        // Compare the timing of two different scenarios
        CollectionScenarioResult integerArrayResult = CollectionTestScenarios.runScenarios(ArrayList::new, scenarioConfig);
        CollectionScenarioResult integerSetResult = CollectionTestScenarios.runScenarios(HashSet::new, scenarioConfig);
        LOGGER.info("The results for the collection (size: {}) scenarios (n: {}): ",
                scenarioConfig.collectionSize(), scenarioConfig.scenarioFreq());
        String comparison = integerArrayResult.compare(integerSetResult, "Integer");
        LOGGER.info(comparison);

        // Create a table showing the timing of a few different collections
        String resultTable = new CollectionScenarioResultTable()
                .withResult(integerArrayResult)
                .withResult(CollectionTestScenarios.runScenarios(LinkedList::new, scenarioConfig))
                .withResult(integerSetResult)
                .withResult(CollectionTestScenarios.runScenarios(TreeSet::new, scenarioConfig))
                .withResult(CollectionTestScenarios.runScenarios(LinkedHashSet::new, scenarioConfig))
                .withResult(CollectionTestScenarios.runScenarios(ArrayDeque::new, scenarioConfig))
                .withResult(CollectionTestScenarios.runScenarios(Stack::new, scenarioConfig))
                .resultTableString(scenarioConfig);

        LOGGER.info(resultTable);

    }
}