package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = generateRandomGroup(10); // Generate a random group of 10 persons

        List<Person> drivers = persons.stream()
                .filter(Person::isDriver)
                .toList();

        TreeSet<Person> passengers = persons.stream()
                .filter(person -> !person.isDriver())
                .collect(Collectors.toCollection(TreeSet::new));

        LinkedList<Person> sortedDriversByAge = new LinkedList<>(drivers);
        sortedDriversByAge.sort(Comparator.comparingInt(Person::getAge));

        System.out.println("Drivers sorted by age:");
        sortedDriversByAge.forEach(System.out::println);

        System.out.println("\nPassengers sorted by name:");
        passengers.forEach(System.out::println);
    }

    private static List<Person> generateRandomGroup(int size) {
        List<Person> persons = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            String name = "Person" + (i + 1);
            int age = random.nextInt(80) + 20; // Random age between 20 and 99
            boolean isDriver = random.nextBoolean();
            persons.add(new Person(name, age, isDriver));
        }

        return persons;
    }
}