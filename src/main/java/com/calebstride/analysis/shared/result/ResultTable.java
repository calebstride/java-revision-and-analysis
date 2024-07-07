package com.calebstride.analysis.shared.result;

import java.util.*;

public class ResultTable {

    private final List<String> headers;

    private final int columnNum;

    private final Map<Integer, Integer> columnMaxLength = new HashMap<>();

    private final List<List<Object>> rows = new ArrayList<>();

    public ResultTable(String... headers) {
        this.headers = Arrays.asList(headers);
        this.columnNum = headers.length;
        updateMaxColumnLength(this.headers);
    }

    public void addRow(Object... rowValues) {
        if (rowValues.length != columnNum) {
            throw new IllegalArgumentException("Number of values does not match number of columns");
        }
        List<Object> newRow = Arrays.asList(rowValues);
        updateMaxColumnLength(newRow);
        rows.add(newRow);
    }

    private void updateMaxColumnLength(List<?> rowValues) {
        for (int i = 0; i < rowValues.size(); i++) {
            int rowLength = rowValues.get(i).toString().length();
            columnMaxLength.compute(i, (_, val) -> val == null ? rowLength : Math.max(val, rowLength));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(rowToString(headers));
        for (List<Object> row : rows) {
            sb.append(rowToString(row));
        }
        return sb.toString();
    }

    public String rowToString(List<?> row) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row.size(); i++) {
            int maxColumnLength = columnMaxLength.get(i);
            sb.append(String.format(STR." %\{maxColumnLength}s | ", row.get(i)));
        }
        sb.append(System.lineSeparator());
        return sb.toString();
    }

}
