package com.kozyrev.simbirtraineeship.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassesTask2 {

    private int num;
    private List<Integer> list;

    public ClassesTask2(int num) throws Exception{
        this.num = num;
        if (num > 0) list = new ArrayList<>(num);
        else throw new Exception("The number of elements in the array must be greater than 0");
    }

    public void fillArray(){
        for (int i = 0; i < num; i++){
            list.add((int)(Math.random() * 10));
        }
    }

    public void randomReplaceArrayElements(){
        Collections.shuffle(list);
    }

    public int countElements(int targetValue){
        return Collections.frequency(list, targetValue);
    }

    public void printArray(){
        System.out.println("Массив: " + list.toString());
    }
}
