package com.calebstride.analysis.scenarios.results;

import com.calebstride.analysis.scenarios.ScenarioConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionScenarioResultGroup {

    private List<CollectionScenarioResult> results = new ArrayList<>();

    public CollectionScenarioResultGroup withResult(CollectionScenarioResult collectionScenarioResult) {
        results.add(collectionScenarioResult);
        return this;
    }

    public void addResult(CollectionScenarioResult collectionScenarioResult) {
        results.add(collectionScenarioResult);
    }

    /**
     * Create a table of results to print to the console
     *
     * @param tableTitle     The title of the table
     * @param scenarioConfig The config used
     * @return The table string to be printed to the console
     */
    public String resultTable(String tableTitle, ScenarioConfig scenarioConfig) {
        StringBuilder resultTable = new StringBuilder(STR."""
        \n
        \{tableTitle} with \{scenarioConfig.collectionSize()} elements running each process \{scenarioConfig.scenarioFreq()} times

        """);

        resultTable.append(createRow("", "add (ns)", "remove (ns)", "contains (ns)", "size (ns)", "bytes"));
        for (CollectionScenarioResult result : results) {
            resultTable.append(createRow(result));
        }
        return resultTable.toString();
    }

    private String createRow(String name, String add, String remove, String contains, String size, String bytes) {
        return String.format("%10s | %10s | %15s | %15s | %10s | %10s |%n", name, add, remove, contains, size, bytes);
    }

    private String createRow(CollectionScenarioResult collectionScenarioResult) {
        return String.format("%10s | %10s | %15s | %15s | %10s | %10s |%n", collectionScenarioResult.collectionName(),
                collectionScenarioResult.timeToAdd().getAverageTime(),
                collectionScenarioResult.timeToRemove().getAverageTime(),
                collectionScenarioResult.timeToContains().getAverageTime(),
                collectionScenarioResult.timeToSize().getAverageTime(), collectionScenarioResult.sizeInBytes());
    }
}