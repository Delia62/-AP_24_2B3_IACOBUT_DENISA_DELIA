package org.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.io.File;

public class Analyzer {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, MalformedURLException {
        String classLocation = "C:\\Users\\Delia\\Documents\\GitHub\\-AP_24_2B3_IACOBUT_DENISA_DELIA\\Lab12PA\\target\\classes";
        MyClassLoader myClassLoader = new MyClassLoader();
        File path = new File(classLocation);
        if (path.exists()) {
            URL url = path.toURI().toURL();
            myClassLoader.addURL(url);
        }
        String className = "org.example.MyProgram";
        Class clazz = Class.forName(className);
        System.out.println(clazz.getName());
        System.out.println(clazz.getPackage());
        System.out.println(Arrays.toString(clazz.getMethods()));
        System.out.println(clazz.getSuperclass());

        int passed = 0, failed = 0;
        for (Method m : Class.forName(clazz.getName()).getMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                try {
                    m.invoke(null);
                    passed++;
                } catch (Throwable ex) {
                    System.out.printf("Test %s failed: %s %n",
                            m, ex.getCause());
                    failed++;
                }
            }
        }
        System.out.printf("Passed: %d, Failed %d%n", passed, failed);
    }
}

