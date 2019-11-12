package com.kozyrev.simbirtraineeship.collections;

import com.kozyrev.simbirtraineeship.entities.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentsBlock {

    List<String> educationalSubjects = Arrays.asList("ООП", "Базы данных", "Паттерны ООП", "Параллельное программирование");
    List<Student> students;

    public StudentsBlock(){
        generateStudents();
    }

    private void generateStudents(){
        students = new ArrayList<>(8);
        students.set(0, generateStudent("Иван", "Иванов", "Иванович"));
        students.set(1, generateStudent("Петр", "Петров", "Петрович"));
        students.set(2, generateStudent("Василий", "Беспалов", "Александрович"));
        students.set(3, generateStudent("Антон", "Кондрашкин", "Андреевич"));
        students.set(4, generateStudent("Артем", "Ефимов", "Андреевич"));
        students.set(5, generateStudent("Евгений", "Еремин", "Михайлович"));
        students.set(6, generateStudent("Андрей", "Козырев", "Александрович"));
        students.set(7, generateStudent("Иван", "Иванов", "Иванович"));
    }

    private Student generateStudent(String firstName, String lastName, String patronymic){
        Map<String, Integer> marks = new HashMap<>(educationalSubjects.size());
        for (String subject: educationalSubjects) {
            marks.put(subject, 3 + (int)(Math.random() * 3));
        }
        return new Student(firstName, lastName, patronymic, 199 + (int)(Math.random() * 10), 1 + (int)(Math.random() * 3), 612 + (int)(Math.random() * 2), marks);
    }
}
