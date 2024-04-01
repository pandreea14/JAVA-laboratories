package org.example;

import java.util.*;

public class Person {
    private final String firstName;
    private final String lastName;
    Location location;
    Map<Integer, MapValue> stops = new HashMap<>();
    List<Integer> currentDestination = new ArrayList<>();
    private final int age;

    private boolean hasACar;

    private boolean iDrive;

    String leaving;
    String destination;

    Person(String firstName, String lastName, int age, boolean hasACar, boolean iDrive, Location location) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.hasACar = hasACar;
        this.iDrive = iDrive;
        this.location = location;

        condition(age, hasACar);
        myRoadLocations();
    }

    private void condition(int age, boolean hasACar) {
        if (age < 18) {
            this.hasACar = false;
            this.iDrive = false;
        } else if (!hasACar) {
            this.iDrive = false;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public boolean getHasACar() {
        return hasACar;
    }

    public boolean getiDrive() {
        return iDrive;
    }

    private void myRoadLocations() {
        Random random = new Random();

        int numberOfLocations;

        // asigneaza random o ruta pentru locatiile prin care trece fiecare sofer
        //daca e pasager va avea doar punct de pornire si o destinatie
        if (getiDrive()) {
            numberOfLocations = random.nextInt(location.getNumberOfLocations() - 2) + 2;
        } else {
            numberOfLocations = 2;
        }

        // as putea sa fac un vector de visitable sa nu merg prin aceeasi locatie de 2 ori
        // momentan presupun ca soferul a uitat chesti pe drum si se intoarce, haotic
        int[] visited = new int[50];
        int nr = 0;
        for (int i = 0; i < numberOfLocations; i++) {
            int id = random.nextInt(location.getNumberOfLocations());
            if (getiDrive()) {
                while (visited[id] == 1) {
                    id = random.nextInt(location.getNumberOfLocations());
                }
                stops.put(id,new MapValue(location.getLocationName(id),nr));
                visited[id] = 1;
                nr++;
                MapValue values = new MapValue(location.getLocationName(id), nr);
                if (visited[id] == 0) {
                    stops.put(id, values);
                    visited[id] = 1;
                    nr++;
                }
            } else {
                while (visited[id] == 1) {
                    id = random.nextInt(location.getNumberOfLocations());
                }
                if (visited[id] == 0) {
                    //daca nu e vizitata locatia o adaug in lista de indexare a locatiilor
                    currentDestination.add(id);
                    visited[id] = 1;
                }
            }
        }
    }

    public void outputAllStops() {

        System.out.println("my destinations are: ");
        if (getiDrive()) {
            for (Map.Entry<Integer, MapValue> entry : stops.entrySet()) {
                MapValue values = entry.getValue();
                System.out.println(values.getName() + " id=" + values.getId() + " ");
            }
        } else {
            //daca e pasager ma intereseaza de unde pleaca si unde ajunge, atat
            System.out.print("Leaving from: " + location.getLocationName(currentDestination.getFirst()) + " and I want to go to: ");
            System.out.println(location.getLocationName(currentDestination.getLast()));
        }
        System.out.println();
    }

    public Map<Integer, MapValue> getStops() {
        return stops;
    }

    public List<Integer> getListPassanger() {
        return currentDestination;
    }

    public int getId(Integer id) {
        MapValue values = stops.get(id);
        return values.getId();
    }

}
