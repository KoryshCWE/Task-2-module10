package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class User {
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class Main_User {
    public static List<User> readFromFile(String filename) {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean skipHeader = true;
            while ((line = br.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                String[] data = line.trim().split("\\s+");
                if (data.length == 2 && data[1].matches("\\d+")) { // Ensure the second part is a valid integer
                    User user = new User(data[0], Integer.parseInt(data[1]));
                    users.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void writeToJson(List<User> users, String filename) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(users);
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String inputFileName = "src/main/java/file.txt";
        String outputFileName = "src/main/java/org/example/user.json";

        List<User> users = readFromFile(inputFileName);
        writeToJson(users, outputFileName);
    }
}
