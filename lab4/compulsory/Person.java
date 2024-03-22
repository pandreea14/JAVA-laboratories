package org.example;

public class Person implements Comparable<Person> {
    String name;
    int age;
    boolean isDriver;

    public Person(String name, int age, boolean isDriver) {
        this.name = name;
        this.age = age;
        this.isDriver = isDriver;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public boolean isDriver() {
        return isDriver;
    }
    public void setDriver(boolean driver) {
        isDriver = driver;
    }

    @Override
    public int compareTo(Person other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isDriver=" + isDriver +
                '}';
    }
}
