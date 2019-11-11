package com.kozyrev.simbirtraineeship.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StudentsBlock {
    private String TAG = "TAG";

    private List<String> educationalSubjects = Arrays.asList("ООП", "Базы данных", "Паттерны ООП", "Параллельное программирование");
    private List<String> firstNames = Arrays.asList("Иван", "Геннадий", "Василий", "Антон", "Артём", "Евгений", "Андрей");
    private List<String> lastNames = Arrays.asList("Иванов", "Альгашев", "Беспалов", "Кондрашкин", "Ефимов", "Еремин", "Козырев");
    private List<String> patronymics = Arrays.asList("Иванович", "Петрович", "Александрович", "Андреевич", "Михайлович", "Артёмович", "Геннадьевич");
    private List<Student> students;

    public StudentsBlock(int num){
        students = new LinkedList<Student>();
        generateStudents(num);
    }

    public List<Student> getStudents(){
        return students;
    }

    public List<Student> sortStudents(){
        System.out.println("before sort: " + students.toString() + "\n");

        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                return student1.compareTo(student2);
            }
        });
        System.out.println("after sort: " + students.toString() + "\n");
        return students;
    }

    public Map<Integer, Map<String, Double>> averageMarksByGroup(){
        Map<Integer, Integer> groupsCount = new HashMap<>();
        Map<String, Double> groupMarks;
        Map<Integer, Map<String, Double>> averageMarks = new HashMap<>();

        int count;
        int groupNumber;

        for (Student currentStudent : students){
            groupNumber = currentStudent.getGroupNumber();
            groupMarks = new HashMap<>();

            if (averageMarks.containsKey(groupNumber)){
                count = groupsCount.get(groupNumber);
                groupsCount.put(groupNumber, ++count);
                groupMarks = averageMarks.get(groupNumber);
            } else {
                groupsCount.put(groupNumber, 1);
            }

            groupMarks = sumGroupMarks(currentStudent.getMarks(), groupMarks);
            averageMarks.put(groupNumber, groupMarks);
        }
        averageMarks = averageGroupMarks(groupsCount, averageMarks);

        System.out.println("averageMarks: " + averageMarks.toString() + "\n");
        return averageMarks;
    }

    private Map<String, Double> sumGroupMarks(Map<String, Integer> studentMarks, Map<String, Double> groupMarks){
        double prevSumMark;

        for (Map.Entry<String, Integer> entry : studentMarks.entrySet()){
            prevSumMark = 0;
            if (groupMarks.containsKey(entry.getKey())) prevSumMark = groupMarks.get(entry.getKey());
            groupMarks.put(entry.getKey(), Double.valueOf(entry.getValue()) + prevSumMark);
        }
        return groupMarks;
    }

    private Map<Integer, Map<String, Double>> averageGroupMarks(Map<Integer, Integer> groupCount, Map<Integer, Map<String, Double>> averageMarks){
        Map<String, Double> groupMarks;

        for (Map.Entry<Integer, Integer> entry : groupCount.entrySet()) {
            int groupNum = entry.getKey();
            groupMarks = averageMarks.get(groupNum);

            for (Map.Entry<String, Double> entryMarks : groupMarks.entrySet()) {
                groupMarks.put(entryMarks.getKey(), entryMarks.getValue() / entry.getValue());
            }
            averageMarks.put(groupNum, groupMarks);
        }
        return averageMarks;
    }

    public List<Student> minMaxAgeStudents(){
        List<Student> minMaxAgeStudents = new LinkedList<>();

        if (students.size() > 0){
            Student minAgeStudent = students.get(0);
            Student maxAgeStudent = students.get(0);

            for (Student student: students) {
                if (student.getBornYear() > minAgeStudent.getBornYear()) minAgeStudent = student;
                if (student.getBornYear() < maxAgeStudent.getBornYear()) maxAgeStudent = student;
            }
            minMaxAgeStudents.add(minAgeStudent);
            minMaxAgeStudents.add(maxAgeStudent);
        }
        System.out.println("minMaxAgeStudents: " + minMaxAgeStudents.toString() + "\n");
        return minMaxAgeStudents;
    }

    public Map<Integer, Student> bestMarksStudents(){
        Map<Integer, Student> bestMarksStudents = new HashMap<>();
        Map<String, Integer> currentStudentMarks;
        Map<String, Integer> prevStudentMarks;

        int groupNumber, currentStudentSum, prevStudentSum;
        Student prevStudent;

        for (Student currentStudent: students) {
            groupNumber = currentStudent.getGroupNumber();

            if (bestMarksStudents.containsKey(groupNumber)){
                currentStudentSum = 0;
                prevStudentSum = 0;
                prevStudent = bestMarksStudents.get(groupNumber);
                currentStudentMarks = currentStudent.getMarks();
                prevStudentMarks = prevStudent.getMarks();

                for (Map.Entry<String, Integer> entryMarks : currentStudentMarks.entrySet()) {
                    currentStudentSum += entryMarks.getValue();
                    prevStudentSum += prevStudentMarks.get(entryMarks.getKey());
                }

                bestMarksStudents.put(groupNumber, (currentStudentSum > prevStudentSum) ? currentStudent : prevStudent);
            } else {
                bestMarksStudents.put(groupNumber, currentStudent);
            }
        }

        System.out.println("bestMarksStudents: " + bestMarksStudents.toString() + "\n");
        return bestMarksStudents;
    }

    private void generateStudents(int num){
        for (int i = 0; i < num; i++)
            students.add(generateStudent(firstNames.get((int)(Math.random() * firstNames.size())),
                                            lastNames.get((int)(Math.random() * lastNames.size())),
                                            patronymics.get((int)(Math.random() * patronymics.size()))));
    }

    private Student generateStudent(String firstName, String lastName, String patronymic){
        Map<String, Integer> marks = new HashMap<>(educationalSubjects.size());
        for (String subject: educationalSubjects) {
            marks.put(subject, 3 + (int)(Math.random() * 3));
        }
        int course = 1 + (int)(Math.random() * 2);
        return new Student(firstName, lastName, patronymic, 1990 + (int)(Math.random() * 10), course, 6000 + course * 100 + 21 + (int)(Math.random() * 2), marks);
    }
}