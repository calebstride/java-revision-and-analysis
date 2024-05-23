package com.calebstride.analysis.scenarios.results;

/**
 * Record to hold all the scenarios tested for a collection timing. As well as the size of the collection.
 *
 * @param timeToAdd      The result for adding elements to the collection
 * @param timeToRemove   The result for removing elements from the collection
 * @param timeToContains The timing result for checking if the collection contains an element
 * @param timeToSize     The result for checking the collection size
 * @param sizeInBytes    The size of the collection in bytes
 * @param collectionName The name of the collection class
 */
public record CollectionScenarioResult(TimeResult timeToAdd, TimeResult timeToContains, TimeResult timeToRemove,
                                       TimeResult timeToSize, long sizeInBytes, String collectionName) {

    /**
     * @param contentType The content type of the collection
     * @return A string showing the summary of a collection scenario
     */
    public String summary(String contentType) {
        return STR."""
        The summary for the \{collectionName} of type \{contentType}:
        \{collectionName} Size - \{sizeInBytes} bytes
        Add time     : \{timeToAdd.getSummary()}
        Remove time  : \{timeToRemove.getSummary()}
        Contains time: \{timeToContains.getSummary()}
        Size time    : \{timeToSize.getSummary()}
        """;
    }

    /**
     * Create some text for a comparison between times. This is not very pretty.
     * @param scenarioResult The result to compare to
     * @param contentType The type that both collections contain
     * @return The block of text with a comparison of the times
     */
    public String compare(CollectionScenarioResult scenarioResult, String contentType) {
        return STR."""
        The comparison of the \{collectionName} against \{scenarioResult.collectionName} of type \{contentType}:
        \{collectionName} Size - \{sizeInBytes} bytes against \{scenarioResult.collectionName}s \{scenarioResult.sizeInBytes} bytes
        Add: \{compareTimes(timeToAdd.getAverageTime(), scenarioResult.timeToAdd.getAverageTime(), collectionName,
                scenarioResult.collectionName)}
        Remove: \{compareTimes(timeToRemove.getAverageTime(), scenarioResult.timeToRemove.getAverageTime(),
                collectionName, scenarioResult.collectionName)}
        Contains: \{compareTimes(timeToContains.getAverageTime(), scenarioResult.timeToContains.getAverageTime(),
                collectionName, scenarioResult.collectionName)}
        Size: \{compareTimes(timeToSize.getAverageTime(), scenarioResult.timeToSize.getAverageTime(), collectionName,
                scenarioResult.collectionName)}
        """;
    }

    private String compareTimes(long firstTime, long secondTime, String firstName, String secondName) {
        if (firstTime == secondTime) {
            return STR."\{firstName} and \{secondName} were equal, with an average time: \{secondTime}";
        }
        double factor;
        String description;
        if (firstTime < secondTime) {
            factor = ((double) firstTime / secondTime);
            description = "quicker";
        } else {
            factor = ((double) secondTime / firstTime);
            description = "slower";
        }
        int times = (int) (1.0 / factor);
        String percent = String.format("%.02f", factor * 100.0);
        return STR."\{firstName} was \{description} with an average time: \{firstTime}ns compared to \{secondName}s: \{secondTime}ns (\{percent}%) (\{times}x)";
    }
}
