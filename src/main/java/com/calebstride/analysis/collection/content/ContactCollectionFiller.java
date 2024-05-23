package com.calebstride.analysis.collection.content;

import com.calebstride.analysis.collection.content.group.CollectionGroup;
import com.calebstride.analysis.collection.content.group.CollectionGroupGeneratorSimple;
import com.calebstride.analysis.contact.Contact;
import com.calebstride.analysis.scenarios.ScenarioConfig;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Fills a collection with random Contact values
 */
public class ContactCollectionFiller implements CollectionFiller<Contact> {

    // Separate out the simple generator as I might want to replace this later on
    private final CollectionGroupGeneratorSimple<Contact> groupGenerator = new CollectionGroupGeneratorSimple<>();

    /**
     * Fill the collection with random contact values.
     *
     * @param newCollection  A supplier to return an empty collection
     * @param scenarioConfig The scenario configuration
     * @return The group of collections containing the collection and the values to use in scenarios
     */
    @Override
    public CollectionGroup<Contact> fillCollection(Supplier<Collection<Contact>> newCollection,
            ScenarioConfig<Contact> scenarioConfig) {
        return groupGenerator.generateGroup(newCollection, scenarioConfig, this::generateValue);
    }

    /**
     * Generate a random contact
     *
     * @param index The index to place the element
     * @return The generated random contact
     */
    @Override
    public Contact generateValue(int index) {
        return Contact.generateRandom();
    }

    @Override
    public String typeName() {
        return "Contact";
    }
}
