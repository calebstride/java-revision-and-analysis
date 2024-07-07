package com.calebstride.analysis.collection.content.group;

import java.util.Collection;

/**
 * Holds the collection to run the scenarios against (collection) and collections to use in the scenarios. For example,
 * the add() scenario needs a value to add, it will get one from the addRemoveCollection.
 *
 * @param collection The base list to perform actions on
 * @param add The collection containing values to use for adding
 * @param remove The collection containing values to use for removing
 * @param contains The collection containing values to use for contains
 * @param <U>        The type of the value the collection contains
 */
public record CollectionGroup<U>(Collection<U> collection, Collection<U> add, Collection<U> remove,
                                 Collection<U> contains) {
}