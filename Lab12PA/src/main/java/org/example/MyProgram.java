package org.example;

public class MyProgram {
    @Test
    public static void program1() { }
    public static void program2() { }
    @Test
    public static void program3() {
        throw new RuntimeException("Boom");
    }
    public static void program4() { }
    @Test
    public static void program5() { }
    public static void program6() { }
    @Test
    public static void program7() {
        throw new RuntimeException("Crash");
    }
    public static void program8() { }
}
