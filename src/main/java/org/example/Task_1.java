package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PhoneNumberValidator {
    public static void main(String[] args) {
        String fileName = "src/main/java/file.txt";
        List<String> phoneNumbers = extractPhoneNumbers(fileName);
        for (String phoneNumber : phoneNumbers) {
            System.out.println(phoneNumber);
        }
    }

    public static List<String> extractPhoneNumbers(String fileName) {
        List<String> phoneNumbers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (isValidPhoneNumber(line)) {
                    phoneNumbers.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        return phoneNumbers;
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}

