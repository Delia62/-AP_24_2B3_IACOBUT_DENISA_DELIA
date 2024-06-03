package org.example;

public class MyProgram {
    @Test
    public static void test1() { }
    public static void test2() { }
    @Test
    public static void test3() {
        throw new RuntimeException("Boom");
    }
    public static void test4() { }
    @Test
    public static void test5() { }
    public static void test6() { }
    @Test
    public static void test7() {
        throw new RuntimeException("Crash");
    }
    public static void test8() { }

    public static void test9(String text1, int value, String text2) { }
    public static void test10(int value) { }
    @Test
    public static void test11(int value1, int value2) { }
}
