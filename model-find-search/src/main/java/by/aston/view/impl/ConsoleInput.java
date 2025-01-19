package by.aston.view.impl;

import by.aston.view.DataInput;

import java.util.Scanner;

public class ConsoleInput implements DataInput {
    private final Scanner scanner;

    public ConsoleInput(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showErrorMessage(String message) {
        System.err.println(message);
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }
}
