package com.kozyrev.simbirtraineeship.collections;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CollectionsBlock<T extends Comparable> {

    private static final String ERROR_MESSAGE = "Один или несколько параметров метода равны null";

    public List<T> collectionTask0(@NonNull List<T> firstList, @NonNull List<T> secondList) throws NullPointerException{
        if ((firstList == null) || (secondList == null)) throw new NullPointerException(ERROR_MESSAGE);

        List<T> sorted = new ArrayList<T>(firstList.size() + secondList.size());
        sorted.addAll(firstList);
        sorted.addAll(secondList);
        Collections.sort(sorted, Collections.<T>reverseOrder());
        return sorted;
    }

    public List<T> collectionTask1(@NonNull List<T> inputList) throws NullPointerException{
        if (inputList == null) throw new NullPointerException(ERROR_MESSAGE);

        List<T> extendedList = new LinkedList<T>();
        for (int i = 0; i < inputList.size(); i++) {
            extendedList.add(inputList.get(i));
            extendedList.addAll(inputList.subList(0, i));
        }
        return extendedList;
    }

    public boolean collectionTask2(@NonNull List<T> firstList, @NonNull List<T> secondList) throws NullPointerException{
        if ((firstList == null) || (secondList == null)) throw new NullPointerException(ERROR_MESSAGE);

        Set<T> firstSet = new HashSet<T>(firstList);
        Set<T> secondSet = new HashSet<T>(secondList);
        return firstSet.size() == secondSet.size();
    }

    public List<T> collectionTask3(@NonNull List<T> inputList, int n) throws NullPointerException{
        if (inputList == null) throw new NullPointerException(ERROR_MESSAGE);

        Collections.rotate(inputList, n);
        return inputList;
    }

    public List<String> collectionTask4(@NonNull List<String> inputList, @NonNull String a, @NonNull String b) throws NullPointerException{
        if ((inputList == null) || (a == null) || (b == null)) throw new NullPointerException(ERROR_MESSAGE);

        for (int i = 0; i < inputList.size(); i++) {
            if (inputList.get(i).equals(a)) inputList.set(i, b);
        }

        return inputList;
    }
}