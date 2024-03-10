package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class WordFrequencyCounter {

    public static Map<String, Integer> countWordFrequency(String fileName) {
        Map<String, Integer> wordFreq = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.trim().split("\\s+");
                for (String word : words) {
                    wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordFreq;
    }

    public static void main(String[] args) {
        String fileName = "src/main/java/org/example/words.txt";
        Map<String, Integer> wordFrequency = countWordFrequency(fileName);

        // Створення списку з елементів мапи
        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordFrequency.entrySet());

        // Сортування списку за значеннями (кількістю зустрічей слів) у зворотньому порядку
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Виведення відсортованих даних
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
