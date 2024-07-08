package com.calebstride.analysis.multithread.threads;

import com.calebstride.analysis.multithread.logic.fibonacci.FibonacciCallable;
import com.calebstride.analysis.shared.timing.TimeResult;
import com.calebstride.analysis.shared.timing.TimeResultRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Manage the creation of the ExecutorService as well as the tasks to run.
 */
public class ExecutorServiceManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorServiceManager.class);

    private final ThreadConfig threadConfig;

    private final TimeResult timeResult;

    public ExecutorServiceManager(ThreadConfig threadConfig) {
        this.threadConfig = threadConfig;
        this.timeResult = new TimeResult(TimeUnit.MILLISECONDS);
    }

    /**
     * Create the threads and thread tasks, then run them and update the timeResult.
     * Usually it would be a good idea to reuse the executorService but in this case I want the timings to be consistent
     * and include the time to create the object
     */
    public void createAndRunThreads() {
        try(ExecutorService executorService = Executors.newFixedThreadPool(threadConfig.numberOfThreads())) {

            List<FibonacciCallable> allTasks = new ArrayList<>();
            for (int i = 0; i < threadConfig.numberOfTasks(); i++) {
                allTasks.add( new FibonacciCallable(i));
            }

            // Run all the tasks and return when all are finished
            timeResult.updateTime(TimeResultRunner.runForAverage(1, TimeUnit.MILLISECONDS,
                    () -> executeTasks(allTasks, executorService)));
        }
    }

    private void executeTasks(List<FibonacciCallable> allTasks, ExecutorService executorService) {
        try {
            executorService.invokeAll(allTasks);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.error("Interruption exception", e);
        }
    }

    public String getResultsString() {
        return STR."""
        \n
        The time it took to run \{threadConfig.numberOfTasks()} tasks across \{threadConfig.numberOfThreads()} threads:
        \{timeResult.getSummary()}
        """;
    }

    public ThreadConfig getThreadConfig() {
        return threadConfig;
    }

    public TimeResult getTimeResult() {
        return timeResult;
    }
}
