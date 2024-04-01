package org.example;

import com.github.javafaker.Faker;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;

import java.util.*;
import java.util.stream.Collectors;

import java.util.Random;


public class Main {
    public static void main(String[] args) {
        Location location = new Location();
        Faker faker = new Faker();
        Random random = new Random();
        Graph g = GraphBuilder.empty().buildGraph();

        List<Person> personList = new ArrayList<>();
        for(int i=0; i<30; i++){
            personList.add(new Person(faker.name().firstName(),random.nextInt(54)+16, random.nextBoolean(), random.nextBoolean(),location, i ));
            g.addVertex(i);
        }

        List<Person> drivers = new LinkedList<>();
        drivers = personList.stream().filter(e -> e.getIsDriver()).sorted(Comparator.comparingInt(Person::getAge)).toList();

        System.out.println("Drivers: ");
        for (Person person : drivers) {
            System.out.println(person.getName() + " age: " + person.getAge() + " id: " + person.getMyNumber());
            person.printAllStops();
        }


        TreeSet<Person> passengers = personList.stream()
                .filter(e -> !e.getIsDriver())
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Person::getName))));

        System.out.println("\nPassengers: ");
        for (Person person : passengers) {
            System.out.println(person.getName() + " age: " + person.getAge() + " id: " + person.getMyNumber());
            person.printAllStops();
        }

        GreedyCarpool greedyCarpool = new GreedyCarpool(drivers,passengers);
        GraphAlgorithm graphAlgorithm = new GraphAlgorithm(drivers, passengers, g);

    }
}