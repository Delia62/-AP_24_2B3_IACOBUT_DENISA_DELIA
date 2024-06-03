package app;
import comm.DisplayLocales;
import comm.Info;
import comm.SetLocale;

import java.util.Scanner;

public class LocaleExplore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DisplayLocales displayLocales = new DisplayLocales();
        SetLocale setLocale = new SetLocale();
        Info info = new Info();

        while (true) {
            System.out.println("Input command:");
            String command = scanner.nextLine();

            if (command.equals("locales")) {
                displayLocales.execute();
            } else if (command.startsWith("locale.set")) {
                String[] tokens = command.split(" ");
                if (tokens.length == 2) {
                    setLocale.execute(tokens[1]);
                } else {
                    System.out.println("Invalid command");
                }
            } else if (command.startsWith("info")) {
                String[] tokens = command.split(" ");
                if (tokens.length == 2) {
                    info.execute(tokens[1]);
                } else {
                    System.out.println("Invalid command");
                }
            } else {
                System.out.println("Unknown command");
            }
        }
    }
}

