package by.aston.model;

import java.util.ArrayList;
import java.util.List;

public class MainCollection {
    // заполненная коллекция передается мне
    private final static List mainList = new ArrayList();
    private static boolean isSorted = false; // для бинарного поиска

    public static List getMainList(){
        return new ArrayList<>(mainList);
    }

    public static void clearMainList() {
        mainList.clear();
    }

    public static void fillMainCollection(List list){
        mainList.addAll(list);
    }

    public static void setIsSorted(boolean sorted) {
        isSorted = sorted;
    }
}
