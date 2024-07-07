package com.calebstride.analysis.shared.result;

/**
 * A wrapper for the result table. Adds some structure for creating result tables.
 *
 * @param <T> The type that is transformed into the result table rows
 * @param <U> The config used for the specific result table
 */
public abstract class ResultTableWrapper<T, U> {

    protected final ResultTable resultTable;

    protected ResultTableWrapper(String... headers) {
        this.resultTable = new ResultTable(headers);
    }

    /**
     * Add a result to the table
     * @param result The single result to add
     * @return This object
     */
    public abstract ResultTableWrapper<T, U> withResult(T result);

    /**
     * Return the title for the results table
     *
     * @param config The config used for this table
     * @return The table string to be printed to the console
     */
    public abstract String resultTableTitle(U config);

    /**
     * Return the table of results as a string
     *
     * @param config The config used for this table
     * @return The table string to be printed to the console
     */
    public String resultTableString(U config) {
        return STR."""
        \n
        \{resultTableTitle(config)}

        \{resultTable.toString()}
        """;
    }

}
