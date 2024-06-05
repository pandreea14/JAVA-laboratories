package org.example.homework;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class TestRunner {

	private static int totalTests = 0;
	private static int passedTests = 0;
	private static int failedTests = 0;

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java org.example.TestRunner <class/folder/jar-path>");
			return;
		}

		File file = new File(args[0]);
		if (!file.exists()) {
			System.out.println("File or directory not found: " + args[0]);
			return;
		}

		try {
			if (file.isDirectory()) {
				processDirectory(file);
			} else if (file.isFile()) {
				if (file.getName().endsWith(".jar")) {
					processJar(file);
				} else if (file.getName().endsWith(".class")) {
					processClassFile(file, file.getParent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		printStatistics();
	}

	private static void processDirectory(File dir) throws IOException, ClassNotFoundException {
		File[] files = dir.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					processDirectory(file);
				} else if (file.getName().endsWith(".class")) {
					processClassFile(file, dir.getAbsolutePath());
				} else if (file.getName().endsWith(".jar")) {
					processJar(file);
				}
			}
		}
	}

	private static void processJar(File jarFile) throws IOException, ClassNotFoundException {
		JarFile jar = new JarFile(jarFile);
		Enumeration<JarEntry> entries = jar.entries();
		URL[] urls = { new URL("jar:file:" + jarFile.getAbsolutePath() + "!/") };
		URLClassLoader cl = URLClassLoader.newInstance(urls);
		while (entries.hasMoreElements()) {
			JarEntry entry = entries.nextElement();
			if (entry.getName().endsWith(".class")) {
				String className = entry.getName().replace('/', '.').replace(".class", "");
				processClass(className, cl);
			}
		}
	}

	private static void processClassFile(File classFile, String classpath) throws IOException, ClassNotFoundException {
		String className = classFile.getAbsolutePath()
				.replace(classpath + File.separator, "")
				.replace(File.separator, ".")
				.replace(".class", "");
		URL[] urls = { new File(classpath).toURI().toURL() };
		URLClassLoader cl = URLClassLoader.newInstance(urls);
		processClass(className, cl);
	}

	private static void processClass(String className, ClassLoader classLoader) throws ClassNotFoundException {
		Class<?> clazz = classLoader.loadClass(className);
		if (clazz.isAnnotationPresent(Test.class)) {
			System.out.println("Class: " + clazz.getName());
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				if (method.isAnnotationPresent(Test.class)) {
					invokeTestMethod(clazz, method);
				}
			}
		}
	}

	private static void invokeTestMethod(Class<?> clazz, Method method) {
		totalTests++;
		System.out.println("Invoking test method: " + method.getName());
		try {
			Object instance = null;
			if (!Modifier.isStatic(method.getModifiers())) {
				Constructor<?> constructor = clazz.getConstructor();
				instance = constructor.newInstance();
			}
			Object[] args = generateMockArguments(method.getParameterTypes());
			method.setAccessible(true);
			method.invoke(instance, args);
			passedTests++;
		} catch (Exception e) {
			e.printStackTrace();
			failedTests++;
		}
	}

	private static Object[] generateMockArguments(Class<?>[] parameterTypes) {
		Object[] args = new Object[parameterTypes.length];
		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i] == int.class) {
				args[i] = 0;
			} else if (parameterTypes[i] == String.class) {
				args[i] = "test";
			} else {
				args[i] = null;
			}
		}
		return args;
	}

	private static void printStatistics() {
		System.out.println("Total tests: " + totalTests);
		System.out.println("Passed tests: " + passedTests);
		System.out.println("Failed tests: " + failedTests);
	}
}
