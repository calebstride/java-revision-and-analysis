package com.calebstride.analysis.multithread.result;

import com.calebstride.analysis.multithread.threads.ExecutorServiceManager;
import com.calebstride.analysis.multithread.threads.ThreadConfig;
import com.calebstride.analysis.shared.result.ResultTableWrapper;

public class ThreadResultTable extends ResultTableWrapper<ExecutorServiceManager, ThreadConfig> {

    public ThreadResultTable() {
        super("Number of Threads", "Average time (ms)");
    }

    @Override
    public ResultTableWrapper<ExecutorServiceManager, ThreadConfig> withResult(ExecutorServiceManager result) {
        resultTable.addRow(result.getThreadConfig().numberOfThreads(), result.getTimeResult().getAverageTime());
        return this;
    }

    @Override
    public String resultTableTitle(ThreadConfig config) {
        return STR."Timing for \{config.numberOfTasks()} tasks being run across \{config.numberOfThreads()} threads";
    }
}
