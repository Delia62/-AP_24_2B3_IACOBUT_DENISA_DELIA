package org.example;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.jar.JarFile;

public class Analyzer {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InvocationTargetException {
        String location = "C:\\Users\\Delia\\Documents\\GitHub\\-AP_24_2B3_IACOBUT_DENISA_DELIA\\Lab12PA\\target\\classes";
        analyze(location);
    }

    private static void analyze(String location) throws IOException, ClassNotFoundException, IllegalAccessException, InvocationTargetException {
        List<Class<?>> classes = new ArrayList<>();
        loadClasses(location, classes);

        for (Class clazz : classes) {
            printClassPrototype(clazz);
        }

        int passed = 0, failed = 0;
        for (Class clazz : classes) {
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Test.class)) {
                    try {
                        Object[] arguments = generateMockArguments(method.getParameterTypes());
                        method.invoke(null, arguments);
                        System.out.printf("Test %s passed.%n", method);
                        passed++;
                    } catch (Throwable ex) {
                        System.out.printf("Test %s failed: %s%n", method, ex.getCause());
                        failed++;
                    }
                }
            }
        }

        System.out.printf("Passed: %d, Failed: %d%n", passed, failed);
    }

    private static Object[] generateMockArguments(Class<?>[] parameterTypes) {
        Object[] arguments = new Object[parameterTypes.length];
        Random random = new Random();
        for (int i = 0; i < parameterTypes.length; i++) {
            Class parameterType = parameterTypes[i];
            if ((parameterType == int.class || parameterType == Integer.class)) {
                int value = random.nextInt(10);
                arguments[i] = value;
               System.out.println("Mocked int argument: " + value);
            }else if (parameterType == String.class) {
                String value = "MockString" + (i + 1);
                arguments[i] = value;
               System.out.println("Mocked String argument: " + value);
            } else {
                arguments[i] = null;
            }
        }

        return arguments;
    }

    private static void loadClasses(String location, List<Class<?>> classes) throws IOException, ClassNotFoundException {
        File file = new File(location);
        if (file.isDirectory()) {
            loadClassesFromDirectory(file.toPath(), classes);
        } else if (file.isFile() && file.getName().endsWith(".jar")) {
            loadClassesFromJar(file.toPath(), classes);
        } else {
            System.err.println("Invalid input. The input must be a folder or a JAR file.");
        }
    }

    private static void loadClassesFromDirectory(Path directory, List<Class<?>> classes) throws IOException, ClassNotFoundException {
        try (var stream = Files.walk(directory)) {
            stream.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".class"))
                    .map(path -> {
                        try {
                            return getClassFromPath(directory, path);
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .forEach(classes::add);
        }
    }

    private static void loadClassesFromJar(Path jarFile, List<Class<?>> classes) throws IOException, ClassNotFoundException {
        try (var jar = new JarFile(jarFile.toFile())) {
            var entries = jar.entries();
            while (entries.hasMoreElements()) {
                var entry = entries.nextElement();
                if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                    var className = entry.getName().replace('/', '.').replace(".class", "");
                    var clazz = Class.forName(className);
                    classes.add(clazz);
                }
            }
        }
    }

    private static Class<?> getClassFromPath(Path baseDirectory, Path classFile) throws ClassNotFoundException {
        var relativePath = baseDirectory.relativize(classFile);
        var className = relativePath.toString().replace(File.separator, ".").replace(".class", "");
        return Class.forName(className);
    }
    private static void printClassPrototype(Class<?> clazz) {
        System.out.println("Class: " + clazz.getName());
        System.out.println("Package: " + clazz.getPackage().getName());
        System.out.println("Prototype:");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.toString());
        }
        System.out.println("----------------------");
    }
}
