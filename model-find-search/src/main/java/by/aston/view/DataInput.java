package by.aston.view;

import java.io.IOException;
import java.util.Scanner;

public interface DataInput {
    private int[] array;
    public static void fillArray() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов: ");
        int length = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Выберите способ заполнения массива:");
        System.out.println("1. Заполнить вручную");
        System.out.println("2. Заполнить рандомно");
        System.out.println("3. Загрузить из файла");
        System.out.println("4. Выход");
        int choice = scanner.nextLine();

        ArrayFiller arrayFiller = new ArrayFiller();
        arrayFiller.fillChoice(length, choice);
        arrayFiller.printArray();
    }

    public void fillChoice(int length, int choice) {
        array = new int[length];
        switch (choice) {
            case 1:
                fillFromFile();
                break;
            case 2:
                fillRandom();
                break;
            case 3:
                fillKeyboard();
                break;
            case 4:
                System.out.println("Выход...");
                scanner.close();
                return;
            default:
                System.out.println("Некорректный выбор.");
        }
    }

    public void printArray() {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

}
