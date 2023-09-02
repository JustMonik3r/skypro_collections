package pro.sky.skypro_collections;

import java.util.*;

public class Tasks {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(List.of(100, 13, 23, 33, 42, 43, 51, 52, 61, 74));
        List<String> strings = new ArrayList<>(List.of("у", "Карла", "у", "Клары", "украла", "кораллы", "Клара", "у", "Карлы", "украла", "кларнет"));
        task1(numbers);
        task2(numbers);
        task3(strings);
        task4(strings);
    }

    public static void task1(List<Integer> numbers) {
        List<Integer> result = new ArrayList<>();
        for (Integer i : numbers) {
            if (i % 2 != 0) {
                result.add(i);
            }
        }
        System.out.println(result);
    }

    public static void task2(List<Integer> numbers) {
        List<Integer> filteredNumbers = new ArrayList<>();
        for (Integer i : numbers) {
            if (i % 2 == 0) {
                filteredNumbers.add(i);
            }
        }
        Set<Integer> result = new TreeSet<>(filteredNumbers);
        System.out.println(result);
    }

    public static void task3(List<String> strings) {
        Set<String> uniqueWords = new HashSet<>(strings);
        System.out.println(uniqueWords);
    }

    public static void task4(List<String> strs) {
        Map<String, Integer> counter = new TreeMap<>();

        for (String strings : strs) {
            if (counter.containsKey(strings)) {
                counter.put(strings, counter.get(strings) + 1);
            } else {
                counter.put(strings, 1);
            }
        }
        System.out.println(counter);
    }
}
