package org.example.homework;

public class SampleClass {
    @Test
    public static void testMethod1() {
        System.out.println("Test Method 1 executed");
    }

    @Test
    public static void testMethod2() {
        System.out.println("Test Method 2 executed");
    }

    @Test
    public void testMethodWithArgs(int x, String y) {
        System.out.println("Test Method with args executed: " + x + ", " + y);
    }

    public static void nonTestMethod() {
        System.out.println("This is not a test method");
    }

    public void instanceMethod() {
        System.out.println("This is an instance method");
    }
}
