package com.calebstride.analysis.collection.content;

import java.util.Collection;

public record CollectionGroup<T extends Collection<U>, U>(T collection, T scenarioCollection){
}