package com.calebstride.analysis.multithread;

import com.calebstride.analysis.multithread.result.ThreadResultTable;
import com.calebstride.analysis.multithread.threads.ExecutorServiceManager;
import com.calebstride.analysis.multithread.threads.ThreadConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        // Create the manager and run the threads given the configuration
        ExecutorServiceManager twoThreadsManager = new ExecutorServiceManager(new ThreadConfig(2));
        twoThreadsManager.createAndRunThreads();
        LOGGER.info(twoThreadsManager.getResultsString());

        ExecutorServiceManager eightThreadsManager = new ExecutorServiceManager(new ThreadConfig(8));
        eightThreadsManager.createAndRunThreads();
        LOGGER.info(eightThreadsManager.getResultsString());

        // Compare the timing against the number of threads
        ThreadResultTable resultTable = new ThreadResultTable();
        for (int i = 1; i <= 20; i++) {
            ExecutorServiceManager threadManager = new ExecutorServiceManager(new ThreadConfig(i));
            threadManager.createAndRunThreads();
            resultTable.withResult(threadManager);
        }
        LOGGER.info(resultTable.resultTableString(new ThreadConfig(1)));
    }

}
