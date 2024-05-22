package com.calebstride.analysis;

import com.calebstride.analysis.collection.content.IntegerCollectionFiller;
import org.apache.commons.lang3.time.StopWatch;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {



        // Create collection of x objects
        IntegerCollectionFiller integerCollectionFiller = new IntegerCollectionFiller();

        ArrayList<Integer> integerArray = integerCollectionFiller.fillCollection(new ArrayList<>(), 50);

        LOGGER.info("Size of the array: {} bytes", GraphLayout.parseInstance(integerArray).totalSize());



    }
}