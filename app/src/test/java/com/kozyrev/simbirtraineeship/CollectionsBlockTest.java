package com.kozyrev.simbirtraineeship;

import com.kozyrev.simbirtraineeship.collections.CollectionsBlock;
import com.kozyrev.simbirtraineeship.collections.Student;
import com.kozyrev.simbirtraineeship.collections.StudentsBlock;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CollectionsBlockTest {

    private static CollectionsBlock<Integer> collectionsBlock;
    private static StudentsBlock studentsBlock;

    @BeforeClass
    public static void init() {
        collectionsBlock = new CollectionsBlock<>();
        studentsBlock = new StudentsBlock(100);
    }

    @Test
    public void collectionTask0_emptyLists() {
        List<Integer> averageValue = collectionsBlock.collectionTask0(
                Collections.<Integer>emptyList(),
                Collections.<Integer>emptyList()
        );
        List<Integer> expectedValue = Collections.emptyList();
        assertEquals(expectedValue, averageValue);
    }

    @Test
    public void collectionTask0_notEmptyFirstList() {
        List<Integer> firstList = Arrays.asList(3, 2, 1);
        List<Integer> secondList = Collections.emptyList();
        List<Integer> averageValue = collectionsBlock.collectionTask0(firstList, secondList);
        List<Integer> expectedValue = Arrays.asList(3, 2, 1);
        assertEquals(expectedValue, averageValue);
    }

    @Test
    public void collectionTask0_notEmptySecondList() {
        List<Integer> secondList = Arrays.asList(3, 2, 1);
        List<Integer> firstList = Collections.emptyList();
        List<Integer> averageValue = collectionsBlock.collectionTask0(firstList, secondList);
        List<Integer> expectedValue = Arrays.asList(3, 2, 1);
        assertEquals(expectedValue, averageValue);
    }

    @Test
    public void collectionTask0_notEmptyLists() {
        List<Integer> secondList = Arrays.asList(3, 2, 1);
        List<Integer> firstList = Arrays.asList(-4, -5, -6);
        List<Integer> averageValue = collectionsBlock.collectionTask0(firstList, secondList);
        List<Integer> expectedValue = Arrays.asList(3, 2, 1, -4, -5, -6);
        assertEquals(expectedValue, averageValue);
    }

    @Test
    public void collectionTask1_emptyList() {
        List<Integer> inputList = Collections.emptyList();
        List<Integer> averageValue = collectionsBlock.collectionTask1(inputList);
        List<Integer> expectedValue = Collections.emptyList();
        assertEquals(expectedValue, averageValue);
    }

    @Test
    public void collectionTask1_notEmptyList() {
        List<Integer> inputList = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> averageValue = collectionsBlock.collectionTask1(inputList);
        List<Integer> expectedValue = Arrays.asList(1, 2, 1, 3, 1, 2, 4, 1, 2, 3, 5, 1, 2, 3, 4);
        assertEquals(expectedValue, averageValue);
    }

    @Test
    public void collectionTask2_emptyFirstList() {
        List<Integer> firstList = Collections.emptyList();
        List<Integer> secondList = Arrays.asList(3, 2, 1);
        boolean averageValue = collectionsBlock.collectionTask2(firstList, secondList);
        boolean expectedValue = false;
        assertEquals(expectedValue, averageValue);
    }

    @Test
    public void collectionTask2_emptyLists() {
        List<Integer> firstList = Collections.emptyList();
        List<Integer> secondList = Collections.emptyList();
        boolean averageValue = collectionsBlock.collectionTask2(firstList, secondList);
        boolean expectedValue = true;
        assertEquals(expectedValue, averageValue);
    }

    @Test
    public void collectionTask2_setsAreEqual() {
        List<Integer> firstList = Arrays.asList(4, 1, 3, 2);
        List<Integer> secondList = Arrays.asList(3, 2, 1, 4, 2, 1);
        boolean averageValue = collectionsBlock.collectionTask2(firstList, secondList);
        boolean expectedValue = true;
        assertEquals(expectedValue, averageValue);
    }

    @Test
    public void collectionTask2_setsAreNotEqual() {
        List<Integer> firstList = Arrays.asList(4, 1, 3, 2, 5);
        List<Integer> secondList = Arrays.asList(3, 2, 1, 4, 2, 1);
        boolean averageValue = collectionsBlock.collectionTask2(firstList, secondList);
        boolean expectedValue = false;
        assertEquals(expectedValue, averageValue);
    }

    @Test
    public void collectionTask3_emptyList() {
        List<Integer> inputList = Collections.emptyList();
        int n = 3;
        List<Integer> averageValue = collectionsBlock.collectionTask3(inputList, n);
        List<Integer> expectedValue = Collections.emptyList();
        assertEquals(expectedValue, averageValue);
    }

    @Test
    public void collectionTask3_notEmptyList_rightShift() {
        List<Integer> inputList = Arrays.asList(4, 3, 2, 1);
        int n = 3;
        List<Integer> averageValue = collectionsBlock.collectionTask3(inputList, n);
        List<Integer> expectedValue = Arrays.asList(3, 2, 1, 4);
        assertEquals(expectedValue, averageValue);
    }

    @Test
    public void collectionTask3_notEmptyList_leftShift() {
        List<Integer> inputList = Arrays.asList(4, 3, 2, 1);
        int n = -3;
        List<Integer> averageValue = collectionsBlock.collectionTask3(inputList, n);
        List<Integer> expectedValue = Arrays.asList(1, 4, 3, 2);
        assertEquals(expectedValue, averageValue);
    }

    @Test
    public void collectionTask4_emptyList() {
        List<String> inputList = Collections.emptyList();
        String a = "Hello";
        String b = "Hi";
        List<String> averageValue = collectionsBlock.collectionTask4(inputList, a, b);
        List<String> expectedValue = Collections.emptyList();
        assertEquals(expectedValue, averageValue);
    }

    @Test
    public void collectionTask4_notEmptyList() {
        List<String> inputList = Arrays.asList("Hello", ",", " ", "world");
        String a = "Hello";
        String b = "Hi";
        List<String> averageValue = collectionsBlock.collectionTask4(inputList, a, b);
        List<String> expectedValue = Arrays.asList("Hi", ",", " ", "world");
        assertEquals(expectedValue, averageValue);
    }

    @Test(expected = NullPointerException.class)
    public void collectionTask4_checkNull() {
        List<String> inputList = null;
        String a = "Hello";
        String b = "Hi";
        List<String> averageValue = collectionsBlock.collectionTask4(inputList, a, b);
        List<String> expectedValue = Arrays.asList("Hi", ",", " ", "world");
        assertEquals(expectedValue, averageValue);
    }

    @Test
    public void studentTask0_emptyList(){
        StudentsBlock studentsBlock1 = new StudentsBlock(0);
        List<Student> averageValue = studentsBlock1.sortStudents();
        List<Student> expectedValue = Collections.emptyList();
        assertEquals(expectedValue, averageValue);
    }

    @Test
    public void studentTask0_notEmptyList(){
        List<Student> averageValue = studentsBlock.sortStudents();
        List<Student> expectedValue = Collections.emptyList();
        assertNotEquals(expectedValue, averageValue);
    }

    @Test
    public void studentTask1_emptyList(){
        StudentsBlock studentsBlock1 = new StudentsBlock(0);
        Map<Integer, Map<String, Double>> averageValue = studentsBlock1.averageMarksByGroup();
        Map<Integer, Map<String, Double>> expectedValue = new HashMap<>();
        assertEquals(expectedValue, averageValue);
    }

    @Test
    public void studentTask1_notEmptyList(){
        Map<Integer, Map<String, Double>> averageValue = studentsBlock.averageMarksByGroup();
        Map<Integer, Map<String, Double>> expectedValue = new HashMap<>();
        assertNotEquals(expectedValue, averageValue);
    }

    @Test
    public void studentTask2_emptyList() {
        StudentsBlock studentsBlock1 = new StudentsBlock(0);
        List<Student> averageValue = studentsBlock1.minMaxAgeStudents();
        List<Student> expectedValue = Collections.emptyList();
        assertEquals(expectedValue, averageValue);
    }

    @Test
    public void studentTask2_notEmptyList() {
        List<Student> averageValue = studentsBlock.minMaxAgeStudents();
        List<Student> expectedValue = Collections.emptyList();
        assertNotEquals(expectedValue, averageValue);
    }

    @Test
    public void studentTask3_emptyList() {
        StudentsBlock studentsBlock1 = new StudentsBlock(0);
        Map<Integer, Student> averageValue = studentsBlock1.bestMarksStudents();
        Map<Integer, Student> expectedValue = new HashMap<>();
        assertEquals(expectedValue, averageValue);
    }

    @Test
    public void studentTask3_notEmptyList() {
        Map<Integer, Student> averageValue = studentsBlock.bestMarksStudents();
        Map<Integer, Student> expectedValue = new HashMap<>();
        assertNotEquals(expectedValue, averageValue);
    }
}