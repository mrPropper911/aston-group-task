package by.aston.view;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileInput {
    private void fillFromFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к файлу: ");
        String filePath = scanner.nextLine();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int index = 0;
            while ((line = br.readLine()) != null && index < array.length) {
                array[index++] = Integer.parseInt(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка данных: " + e.getMessage());
        }
    }
}
