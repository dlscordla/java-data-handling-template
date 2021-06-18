package com.epam.izh.rd.online.service;

import java.lang.String;
import java.lang.StringBuilder;

public class SimpleTextService implements TextService {

    @Override
    public String removeString(String base, String remove) {
        return base.replace(remove, "");
    }

    @Override
    public boolean isQuestionString(String text) {
        return text.endsWith("?");
    }

    @Override
    public String concatenate(String... elements) {
        StringBuilder fullPhrase = new StringBuilder(elements.length);
        for (String word : elements) {
            fullPhrase.append(word);
        }
        return fullPhrase.toString();
    }

    @Override
    public String toJumpCase(String text) {
        char[] lowerUpperText = text.toCharArray();
        for (int i = 0; i < lowerUpperText.length; i++) {
            if (i % 2 == 0) {
                lowerUpperText[i] = Character.toLowerCase(lowerUpperText[i]);
            } else {
                lowerUpperText[i] = Character.toUpperCase(lowerUpperText[i]);
            }
        }
        return String.valueOf(lowerUpperText);
    }

    @Override
    public boolean isPalindrome(String string) {
        if (string.equals("")) {
            return false;
        } else {
            String text = string.replaceAll("\\s", "");
            String reversedText = new StringBuilder(string).reverse().toString().replaceAll("\\s", "");
            return reversedText.equalsIgnoreCase(text);
        }
    }
}
