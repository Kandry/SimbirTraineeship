package com.kozyrev.simbirtraineeship.training;

public class ElementaryTraining {

    public double averageValue(int firstValue, int secondValue){
        return (firstValue + secondValue) / 2;
    }

    public double complicatedAmount(int firstValue, int secondValue, int thirdValue){
        return (firstValue * 2) + (secondValue - 3) + (thirdValue * thirdValue);
    }

    public int changeValue(int value){
        return  value > 3 ? value + 10 : value - 10;
    }

    public int swapNumbers(int value){
        String valueToStr = String.valueOf(value);
        if ((value > 9) && (valueToStr.length() < 6)){
            StringBuilder buf = new StringBuilder(valueToStr);
            int bufSize = buf.length();
            char firstChar = buf.charAt(0);
            char lastChar = buf.charAt(bufSize - 1);
            buf.setCharAt(0, lastChar);
            buf.setCharAt(bufSize - 1, firstChar);
            value = Integer.valueOf(buf.toString());
        }
        return value;
    }

    public int zeroEvenNumber(int value){
        String valueToStr = String.valueOf(value);
        if ((value > 9) && (valueToStr.length() < 6)){
            StringBuilder buf = new StringBuilder(valueToStr);
            int number;

            for (int i = 0; i < buf.length(); i++) {
                number = (int) buf.charAt(i);
                if ((number / 2 > 0) && (number % 2 != 1)){
                    buf.setCharAt(i, '0');
                }
            }

            value = Integer.valueOf(buf.toString());
        }
        return value;
    }
}
