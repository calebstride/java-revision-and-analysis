package com.calebstride.analysis.collection.content;

import java.util.Collection;

public interface CollectionFiller<U> {

    <T extends Collection<U>> T fillCollection(T collection, int size);

}
