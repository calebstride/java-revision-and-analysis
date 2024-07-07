package com.calebstride.analysis.multithread;

import com.calebstride.analysis.multithread.threads.ExecutorServiceManager;
import com.calebstride.analysis.multithread.threads.ThreadConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        // Create the manager and run the threads given the configuration
        ExecutorServiceManager twoThreadsManager = new ExecutorServiceManager(new ThreadConfig(2, 1000));
        twoThreadsManager.createAndRunThreads();
        LOGGER.info(twoThreadsManager.getResults());

        ExecutorServiceManager eightThreadsManager = new ExecutorServiceManager(new ThreadConfig(8, 1000));
        eightThreadsManager.createAndRunThreads();
        LOGGER.info(eightThreadsManager.getResults());
    }

}
