package by.aston.view;

import java.util.Scanner;

public class KeyboardInput {
    private void fillKeyboard() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < array.length; i++) {
            System.out.print("Введите значение через пробел " + i + ": ");
            array[i] = scanner.nextInt();
        }
    }
}