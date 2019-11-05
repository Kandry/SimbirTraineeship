package com.kozyrev.simbirtraineeship.training;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArraysTraining {

    public int[] sort(int[] valuesArray){
        boolean sorted = false;
        int number;

        while (!sorted){
            sorted = true;

            for (int i = 0; i < valuesArray.length - 1; i++){
                if (valuesArray[i] > valuesArray[i + 1]){
                    number = valuesArray[i];
                    valuesArray[i] = valuesArray[i + 1];
                    valuesArray[i + 1] = number;
                    sorted = false;
                }
            }
        }
        return valuesArray;
    }

    public int maxValue(int... values){
        if (values.length > 0){
            int max = values[0];
            for (int number : values) if (number > max) max = number;
            return max;
        }
        return 0;
    }

    public int[] reverse(int[] array){
        int length = array.length;
        int[] reverseArray = Arrays.copyOf(array, length);
        int number;
        for (int i = 0; i < length / 2; i++){
            number = reverseArray[i];
            reverseArray[i] = reverseArray[length - i - 1];
            reverseArray[length - i - 1] = number;
        }
        return reverseArray;
    }

    public int[] fibonacciNumbers(int numbersCount){
        if (numbersCount > 0){
            int first = 1;
            int second = 1;
            int sum;
            int[] fibonacci = new int[]{first};

            if (numbersCount == 1){
                return fibonacci;
            }

            fibonacci = Arrays.copyOf(fibonacci, 2);
            fibonacci[1] = second;

            if (numbersCount == 2) {
                return fibonacci;
            } else {

                for (int i = 3; i <= numbersCount; i++){
                    sum = first + second;
                    fibonacci = Arrays.copyOf(fibonacci, i);
                    fibonacci[i - 1] = sum;
                    first = second;
                    second = sum;
                }
                return fibonacci;
            }
        }
        return new int[]{};
    }

    public int maxCountSymbol(int[] array){
        int maxCount = 0;
        if (array.length > 0){
            Map<Integer, Integer> counts = new HashMap<>();
            int count = 0;
            for (int number : array) {
                if (counts.containsKey(number)){
                    count = counts.get(number);
                    counts.put(number, ++count);
                } else {
                    counts.put(number, 1);
                }
            }

            for (Map.Entry<Integer, Integer> entry: counts.entrySet()){
               if (entry.getValue() > maxCount) maxCount = entry.getValue();
            }
        }
        return maxCount;
    }
}
