package com.calebstride.analysis.collection.content;

import java.util.Collection;

public class IntegerCollectionFiller implements CollectionFiller<Integer> {


    @Override
    public <T extends Collection<Integer>> T fillCollection(T collection, int size) {
        for (int i = 0; i < size; i++) {
            collection.add(i);
        }
        return collection;
    }

}
