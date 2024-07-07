package com.calebstride.analysis.collection.contact;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

/**
 * A simple object that stores contact info
 */
public class Contact implements Comparable<Contact> {

    private static final Random random = new Random();

    private String addressLineOne;

    private String addressLineTwo;

    private String city;

    private String county;

    private String postCode;

    private int houseNumber;

    public Contact(String addressLineOne, String addressLineTwo, String city, String county, String postCode, int houseNumber) {
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.city = city;
        this.county = county;
        this.postCode = postCode;
        this.houseNumber = houseNumber;
    }

    /**
     * Simply generate a random contact.
     *
     */
    public static Contact generateRandom() {
        return new Contact(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(),
                UUID.randomUUID().toString(), UUID.randomUUID().toString(), random.nextInt(2000));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return houseNumber == contact.houseNumber && Objects.equals(addressLineOne, contact.addressLineOne) &&
                Objects.equals(addressLineTwo, contact.addressLineTwo) && Objects.equals(city, contact.city) &&
                Objects.equals(county, contact.county) && Objects.equals(postCode, contact.postCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressLineOne, addressLineTwo, city, county, postCode, houseNumber);
    }

    @Override
    public int compareTo(Contact contact) {
        if (this == contact) return 0;
        if (contact == null || getClass() != contact.getClass()) return -1;
        int number = Integer.compare(houseNumber, contact.houseNumber);
        if (number != 0) {
            return number;
        } else {
            return addressLineOne.compareTo(contact.addressLineOne);
        }
    }
}
