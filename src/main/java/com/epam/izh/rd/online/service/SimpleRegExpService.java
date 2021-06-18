package com.epam.izh.rd.online.service;

import java.io.*;
import java.util.regex.*;

public class SimpleRegExpService implements RegExpService {


    @Override
    public String maskSensitiveData() {
        String originalText = null;
        String maskedText;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/sensitive_data.txt"))) {
            originalText = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Pattern pattern = Pattern.compile("(\\d{4})\\s\\d{4}\\s\\d{4}\\s(\\d{4})");
        Matcher matcher = pattern.matcher(originalText);
        maskedText = matcher.replaceAll("$1 **** **** $2");
        return maskedText;
    }


    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String newText = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/sensitive_data.txt"))) {
            newText = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Pattern pattern = Pattern.compile("\\$\\{.*?}");
        Matcher matcher = pattern.matcher(newText);
        while (matcher.find()) {
            if (matcher.group().equals("${payment_amount}")) {
                newText = newText.replaceAll("\\$\\{payment_amount}", "" + (int)paymentAmount);
            } else if (matcher.group().equals("${balance}")) {
                newText = newText.replaceAll("\\$\\{balance}", "" + (int)balance);
            }
        }
        return newText;
    }
}
