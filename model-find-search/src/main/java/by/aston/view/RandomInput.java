package by.aston.view;

import java.util.Random;

public class RandomInput {
    public void fillRandom() {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }
    }
}