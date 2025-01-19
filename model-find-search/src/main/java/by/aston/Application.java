package by.aston;

import by.aston.controller.UserInterfaceApplication;
import by.aston.view.impl.ConsoleInput;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        var consoleInput = new ConsoleInput(new Scanner(System.in));
        var ui = new UserInterfaceApplication(consoleInput);
        ui.run();
    }
}
