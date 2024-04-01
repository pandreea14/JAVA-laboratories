package org.example;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;

public class Location{
    Map<Integer, String> streets = new HashMap<>();
    int numberOfLocations = 0;
    public Location(){
        generate();
    }

    private void generate(){
        Faker address = new Faker();
        for(int i = 0; i<50;i++){
            String streetAddress = address.address().streetAddress();
            Integer key = i;
            streets.put(key,streetAddress);
            numberOfLocations++;
        }
    }

    public int getNumberOfLocations() {
        return numberOfLocations;
    }

    public String getNameOfLocation(Integer key){
        return streets.get(key);
    }
}
