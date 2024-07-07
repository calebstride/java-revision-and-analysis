package com.calebstride.analysis.collection.scenarios.results;

import com.calebstride.analysis.collection.scenarios.ScenarioConfig;
import com.calebstride.analysis.shared.result.ResultTable;
import com.calebstride.analysis.shared.result.ResultTableWrapper;

/**
 * Wrapper for a resultTable so that we can add the collection results to it easily.
 */
public class CollectionScenarioResultTable extends ResultTableWrapper<CollectionScenarioResult, ScenarioConfig<?>> {

    public CollectionScenarioResultTable() {
        super("", "add (ns)", "remove (ns)", "contains (ns)", "size (ns)", "bytes (KB)");
    }

    /**
     * Add a result to the group
     * @param collectionScenarioResult The single result to add
     * @return This object
     */
    @Override
    public CollectionScenarioResultTable withResult(CollectionScenarioResult collectionScenarioResult) {
        resultTable.addRow(collectionScenarioResult.collectionName(),
                collectionScenarioResult.timeToAdd().getAverageTime(),
                collectionScenarioResult.timeToRemove().getAverageTime(),
                collectionScenarioResult.timeToContains().getAverageTime(),
                collectionScenarioResult.timeToSize().getAverageTime(), collectionScenarioResult.sizeInBytes() / 1000);
        return this;
    }

    @Override
    public String resultTableTitle(ScenarioConfig<?> config) {
        return STR."\{config.collectionFiller()
                .typeName()} Collection with \{config.collectionSize()} elements running each process \{config.scenarioFreq()} times";
    }
}
