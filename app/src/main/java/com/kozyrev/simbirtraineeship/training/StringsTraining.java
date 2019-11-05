package com.kozyrev.simbirtraineeship.training;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringsTraining {

    public String getOddCharacterString(String text){
        StringBuilder buf = new StringBuilder(text);
        int num = 0;
        int length = buf.length();
        for (int i = 0; i < length; i++){
            if (i % 2 == 0){
                buf.deleteCharAt(i - num);
                num++;
            }
        }
        return buf.toString();
    }

    public int[] getArrayLastSymbol(String text){
        int[] indexes = new int[]{};

        if (text.length() > 0) {
            int lastIndex = text.length() - 1;
            char lastChar = text.charAt(lastIndex);
            int index = text.indexOf(lastChar);

            while ((index > -1) && (index != lastIndex)) {
                indexes = Arrays.copyOf(indexes, indexes.length + 1);
                indexes[indexes.length - 1] = index;
                index = text.indexOf(lastChar, index + 1);
            }
        }
        return indexes;
    }

    public int getNumbersCount(String text){
        int count = 0;
        char[] letters = text.toCharArray();
        for (char letter : letters) {
            if (Character.isDigit(letter)) count++;
        }
        return count;
    }

    public String replaceAllNumbers(String text){
        StringBuilder buf = new StringBuilder(text);

        Map<Integer, String> numberNames = new HashMap<>();
        numberNames.put(0, "zero");
        numberNames.put(1, "one");
        numberNames.put(2, "two");
        numberNames.put(3, "three");
        numberNames.put(4, "four");
        numberNames.put(5, "five");
        numberNames.put(6, "six");
        numberNames.put(7, "seven");
        numberNames.put(8, "eight");
        numberNames.put(9, "nine");

        char currentChar;
        String numberName;
        for (int i = 0; i < buf.length(); i++){
            currentChar = buf.charAt(i);
            if (Character.isDigit(currentChar)){
                numberName = numberNames.get(Integer.valueOf(String.valueOf(currentChar)));
                buf.replace(i, i + 1, numberName);
                i += numberName.length() - 1;
            }
        }
        return buf.toString();
    }

    public String capitalReverse(String text){
        StringBuilder buf = new StringBuilder(text);
        char letter;
        for (int i = 0; i < buf.length(); i++){
            letter = buf.charAt(i);
            if(Character.isLowerCase(letter)){
                buf.setCharAt(i, Character.toUpperCase(letter));
            } else {
                buf.setCharAt(i, Character.toLowerCase(letter));
            }
        }
        return buf.toString();
    }
}
