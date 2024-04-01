package org.example;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;

import java.util.Random;


    public class Main {
        public static void main(String[] args) {
            Location location = new Location();
            Faker faker = new Faker();
            Random random = new Random();

            String firstName;
            String lastName;

            List<Person> listOfPersons = new ArrayList<>();

            for (int i = 0; i < 15; i++) {
                firstName = faker.name().fullName();
                lastName = faker.name().firstName();
                int age = random.nextInt(54) + 16;
                boolean car = random.nextBoolean();
                boolean drive = random.nextBoolean();
                listOfPersons.add(new Person(firstName, lastName, age, car, drive, location));
            }

            List<Person> drivers;
            drivers = listOfPersons.stream().filter(Person::getiDrive).sorted(Comparator.comparingInt(Person::getAge)).toList();

            System.out.println("Drivers: ");
            for (Person person : drivers) {
                System.out.println(person.getFirstName() + " and has age: " + person.getAge());
                person.outputAllStops();
            }


            TreeSet<Person> passangers = listOfPersons.stream()
                    .filter(e -> !e.getiDrive())
                    .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Person::getFirstName))));

            System.out.println("\nPassangers: ");
            for (Person person : passangers) {
                System.out.println(person.getFirstName() + " and has age: " + person.getAge());
                person.outputAllStops();
            }

            Problem alg = new Problem(drivers ,passangers);
            alg.greedyAlgorithm();
        }
    }