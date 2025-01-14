package by.aston.view;

import by.aston.model.Book;
import by.aston.model.Car;
import by.aston.model.MainCollection;
import by.aston.model.Vegetable;
import by.aston.service.sorting.AnotherSorting;
import by.aston.service.sorting.CustomSort;
import by.aston.service.sorting.ShellSorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sorting {
    private static CustomSort<?> customSort;
    private static int choiceSortingSequence;

    public static void sortingProcess() {
        chooseSortingType();
        if (customSort == null) return;
    }

    // выбор типа сортировки
    private static void chooseSortingType() {
        customSort = null;
        choiceSortingSequence = -1;

        boolean flag = false;
        OUTER:
        while (!flag) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nВыберите способ сортировки");
            System.out.println("1. Сортировка Шелла");
            System.out.println("2. Сортировка [другой вариант]");
            System.out.println("0. Вернуться назад");
            int scannerChoice = scanner.nextInt();
            if (scannerChoice == 0) {
                return;
            }
            switch (scannerChoice) {
                case 1:
                    customSort = new ShellSorting<>();
                    flag = true;
                    break;
                case 2:
                    System.out.println("\nСортировка [другой вариант] пока недоступна\n"
                            + "Автоматический возврат назад");
                    new AnotherSorting<>();
                    continue;
                default:
                    System.out.println("Неверный выбор!");
                    continue;
            }
            choiceSortingSequence();
            if (choiceSortingSequence == -1) flag = false;
        }

    }

    // выбор последовательности сортировки
    private static void choiceSortingSequence() {

        boolean flag = false;
        OUTER:
        while (!flag) {
            System.out.println("\nВыберите последовательность сортировки");
            System.out.println("1. Сначала автобомиль");
            System.out.println("2. Сначала книга");
            System.out.println("3. Сначала корнеплод");
            System.out.println("0. Вернуться назад");
            Scanner scanner = new Scanner(System.in);
            int scannerChoice = scanner.nextInt();
            if (scannerChoice == 0) {
                return;
            }
            switch (scannerChoice) {
                case 1:
                    boolean flagCase1 = false;
                    OUTERcase:
                    while (!flagCase1) {
                        System.out.println("\n1. Потом книга и в конце корнеплод");
                        System.out.println("2. Потом корнеплод и в конце книга");
                        System.out.println("0. Назад");
                        int switchCaseChoice = scanner.nextInt();
                        if (switchCaseChoice == 0) {
                            break OUTERcase;
                        }
                        switch (switchCaseChoice) {
                            case 1:
                                choiceSortingSequence = 1;
                                flag = true;
                                flagCase1 = true;
                                break;
                            case 2:
                                choiceSortingSequence = 2;
                                flag = true;
                                flagCase1 = true;
                                break;
                            default:
                                System.out.println("Неверный выбор, попробуйте еще раз!");
                                flagCase1 = false;
                        }
                    }
                    break;

                case 2:
                    boolean flagCase2 = false;
                    OUTERcase:
                    while (!flagCase2) {
                        System.out.println("\n1. Потом автомобиль и в конце корнеплод");
                        System.out.println("2. Потом корнеплод и в конце автомобиль");
                        System.out.println("0. Назад");
                        int switchCaseChoice = scanner.nextInt();
                        if (switchCaseChoice == 0) {
                            break OUTERcase;
                        }
                        switch (switchCaseChoice) {
                            case 1:
                                choiceSortingSequence = 3;
                                flag = true;
                                flagCase2 = true;
                                break;
                            case 2:
                                choiceSortingSequence = 4;
                                flag = true;
                                flagCase2 = true;
                                break;
                            default:
                                System.out.println("Неверный выбор, попробуйте еще раз!");
                                flagCase2 = false;
                        }
                    }
                    break;

                case 3:
                    boolean flagCase3 = false;
                    OUTERcase:
                    while (!flagCase3) {
                        System.out.println("\n1. Потом автомобиль и в конце книга");
                        System.out.println("2. Потом книга и в конце автомобиль");
                        System.out.println("0. Назад");
                        int switchCaseChoice = scanner.nextInt();
                        if (switchCaseChoice == 0) {
                            break OUTERcase;
                        }
                        switch (switchCaseChoice) {
                            case 1:
                                choiceSortingSequence = 5;
                                flag = true;
                                flagCase3 = true;
                                break;
                            case 2:
                                choiceSortingSequence = 6;
                                flag = true;
                                flagCase3 = true;
                                break;
                            default:
                                System.out.println("Неверный выбор, попробуйте еще раз!");
                                flagCase3 = false;
                        }
                    }
                    break;

                default:
                    System.out.println("Неверный выбор, попробуйте еще раз!");
                    continue;
            }
            sorting(customSort, choiceSortingSequence);
        }
    }


    // сортировка. Сначала три коллекции заполняются своими значениями и сортируются, а потом объединяются и присваиваются главной коллекции
    private static void sorting(CustomSort customSort, int sequence) {
        if (choiceSortingSequence == -1) return;
        System.out.println(message());

        List<Car> carList = sortingAndSequenceOfCars(MainCollection.getMainList(), customSort);
        List<Book> bookList = sortingAndSequenceOfBooks(MainCollection.getMainList(), customSort);
        List<Vegetable> rootVegetableList = sortingAndSequenceOfRootVegetable(MainCollection.getMainList(), customSort);

        MainCollection.clearMainList();

        List mainListAfterSort = MainCollection.getMainList();

        switch (sequence) {
            case 1:
                mainListAfterSort = unification(1, carList, bookList, rootVegetableList);
                break;
            case 2:
                mainListAfterSort = unification(2, carList, bookList, rootVegetableList);
                break;
            case 3:
                mainListAfterSort = unification(3, carList, bookList, rootVegetableList);
                break;
            case 4:
                mainListAfterSort = unification(4, carList, bookList, rootVegetableList);
                break;
            case 5:
                mainListAfterSort = unification(5, carList, bookList, rootVegetableList);
                break;
            case 6:
                mainListAfterSort = unification(6, carList, bookList, rootVegetableList);
                break;
        }

        MainCollection.fillMainCollection(mainListAfterSort);
        String s = MainCollection.getMainList().toString();
        System.out.println(s);

    }

    // разбиение на три коллекции и их сортировка
    private static List<Car> sortingAndSequenceOfCars(List mainList, CustomSort customSort) {
        List<Car> carList = new ArrayList<>();

        for (Object o : mainList) {
            if (o != null && o instanceof Car) {
                carList.add((Car) o);
            }
        }

        customSort.sort(carList
                , (car1, car2)
                        -> ((Car) car1).getYearRelease() - ((Car) car2).getYearRelease());

        return carList;
    }
    public static List<Book> sortingAndSequenceOfBooks(List mainList, CustomSort customSort) {
        List<Book> bookList = new ArrayList<>();

        for (Object o : mainList) {
            if (o != null && o instanceof Book) {
                bookList.add((Book) o);
            }
        }

        customSort.sort(bookList
                , (book1, book2)
                        -> ((Book) book1).getNumberOfPages() - ((Book) book2).getNumberOfPages());

        return bookList;
    }
    private static List<Vegetable> sortingAndSequenceOfRootVegetable(List mainList, CustomSort customSort) {
        List<Vegetable> rootVegetableList = new ArrayList<>();

        for (Object o : mainList) {
            if (o != null && o instanceof Vegetable) {
                rootVegetableList.add((Vegetable) o);
            }
        }

        customSort.sort(rootVegetableList
                , (rootVegetable1, rootVegetable2)
                        -> ((Vegetable) rootVegetable1).getType().compareTo(((Vegetable) rootVegetable2).getType()));

        return rootVegetableList;
    }

    // объединение обратно в одну коллекцию, с учетом выбранной последовательности
    private static List unification(int choice, List<Car> carList, List<Book> bookList, List<Vegetable> rootVegetableList) {
        List unionList = new ArrayList();

        if (choice == 1) {
            unionList.addAll(carList);
            unionList.addAll(bookList);
            unionList.addAll(rootVegetableList);
        }
        if (choice == 2) {
            unionList.addAll(carList);
            unionList.addAll(rootVegetableList);
            unionList.addAll(bookList);
        }
        if (choice == 3) {
            unionList.addAll(bookList);
            unionList.addAll(carList);
            unionList.addAll(rootVegetableList);
        }
        if (choice == 4) {
            unionList.addAll(bookList);
            unionList.addAll(rootVegetableList);
            unionList.addAll(carList);
        }
        if (choice == 5) {
            unionList.addAll(rootVegetableList);
            unionList.addAll(carList);
            unionList.addAll(bookList);
        }
        if (choice == 6) {
            unionList.addAll(rootVegetableList);
            unionList.addAll(bookList);
            unionList.addAll(carList);
        }

        return unionList;
    }

    private static String message() {
        String s = null;
        if (choiceSortingSequence == 1) s = "Ваш выбор: автомобиль, книга, корнеплод";
        if (choiceSortingSequence == 2) s = "Ваш выбор: автомобиль, корнеплод, книга";
        if (choiceSortingSequence == 3) s = "Ваш выбор: книга, автомобиль, корнеплод";
        if (choiceSortingSequence == 4) s = "Ваш выбор: книга, корнеплод, автомобиль";
        if (choiceSortingSequence == 5) s = "Ваш выбор: корнеплод, автомобиль, книга";
        if (choiceSortingSequence == 6) s = "Ваш выбор: корнеплод, книга, автомобиль";
        return s;
    }
}
