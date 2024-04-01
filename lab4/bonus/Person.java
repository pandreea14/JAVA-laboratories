package org.example;

import java.util.*;

public class Person {
    private final String name;
    private final int age;

    private boolean hasACar;

    private boolean isDriver;

    int myNumber;

    Map<Integer, MapValue> stops = new HashMap<>();
    Location location;
    List<Integer> currentAndDestination = new ArrayList<>();


    Person(String name, int age, boolean hasACar, boolean isDriver, Location location, int myNumber) {
        this.name = name;
        this.age = age;
        this.hasACar = hasACar;
        this.isDriver = isDriver;
        this.location = location;
        this.myNumber = myNumber;

        condition(age, hasACar, isDriver);
        myStops();
    }

    private void condition(int age, boolean hasACar, boolean isDriver) {
        if (age < 18) {
            this.hasACar = false;
            this.isDriver = false;
        } else if (!hasACar) {
            this.isDriver = false;
        }
    }

    private void myStops() {
        Random random = new Random();
        int numberOfLocations;
        if (getIsDriver()) {
            numberOfLocations = random.nextInt(location.getNumberOfLocations() - 2) + 2;
        } else {
            numberOfLocations = 2;
        }

        int[] visited = new int[location.getNumberOfLocations()+2];
        int nr=0;
        for (int i = 0; i < numberOfLocations; i++) {
            Integer id = random.nextInt(location.getNumberOfLocations());
            if(getIsDriver()){
                while(visited[id]==1){
                    id=random.nextInt(location.getNumberOfLocations());
                }
                stops.put(id,new MapValue(location.getNameOfLocation(id),nr));
                visited[id] = 1;
                nr++;
            }else{
                while(visited[id]==1){
                    id=random.nextInt(location.getNumberOfLocations());
                }
                currentAndDestination.add(id);
                visited[id]=1;
            }
        }
    }

    public void printAllStops() {

        System.out.println("this is my route: ");
        if (getIsDriver()) {
            for (Map.Entry<Integer, MapValue> entry : stops.entrySet()) {
                Integer key = entry.getKey();
                MapValue values = entry.getValue();

                System.out.print(values.getName() + " id=" + values.getId() + " ");
            }
        } else {
            System.out.print(location.getNameOfLocation(currentAndDestination.get(0)) + " ");
            System.out.println(location.getNameOfLocation(currentAndDestination.get(1)));

        }


        System.out.print('\n');
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean getHasACar() {
        return hasACar;
    }

    public boolean getIsDriver() {
        return isDriver;
    }

    public int getMyNumber(){
        return myNumber;
    }

    public Map<Integer, MapValue> getStops() {
        return stops;
    }

    public List<Integer> getCurrentAndDestination() {
        return currentAndDestination;
    }

    public int getId(Integer id){
        MapValue values = stops.get(id);
        return values.getId();
    }
}

