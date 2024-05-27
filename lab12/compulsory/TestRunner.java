package org.example;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestRunner {
    public static void main(String[] args) {
        // Validate input
        if (args.length != 1) {
            System.out.println("Usage: java org.example.TestRunner <class-name>");
            return;
        }

        String className = args[0];

        try {
            // Load the class
            Class<?> clazz = Class.forName(className);

            System.out.println("Class: " + clazz.getName());
            System.out.println("Methods:");

            //Parcurge met
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println("  " + method);

                if (method.isAnnotationPresent(Test.class)) {
                    // Check if the method is static and has no parameters
                    if (Modifier.isStatic(method.getModifiers()) && method.getParameterCount() == 0) {
                        // Invoke the method
                        System.out.println("  ->  Invoking test method: " + method.getName());
                        try {
                            method.invoke(null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + className);
            e.printStackTrace();
        }
    }
}