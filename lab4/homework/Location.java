package org.example;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;

public class Location {
    private Map<Integer, String> location = new HashMap<>();
    int numberOfLocations;

    public Location() {
        setLocations();
    }

    private void setLocations() {
        Faker faker = new Faker();

        int idLocation = 0;
        for (int i = 0; i < 15; i++) {
            String streetAddress = faker.address().streetAddress();
            Integer id = idLocation;
            location.put(id, streetAddress);
            idLocation++;
        }

        numberOfLocations = idLocation;
    }

    public int getNumberOfLocations() {
        return numberOfLocations;
    }

    public String getLocationName(Integer id) {
        return location.get(id);
    }
}
