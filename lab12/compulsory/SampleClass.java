package org.example;

public class SampleClass {
    @Test
    public static void testMethod1() {
        System.out.println("Test Method 1 executed");
    }

    @Test
    public static void testMethod2() {
        System.out.println("Test Method 2 executed");
    }

    public static void nonTestMethod() {
        System.out.println("This is not a test method");
    }

    public void instanceMethod() {
        System.out.println("This is an instance method");
    }
}

