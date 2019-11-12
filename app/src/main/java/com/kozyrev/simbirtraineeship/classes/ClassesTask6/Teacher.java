package com.kozyrev.simbirtraineeship.classes.ClassesTask6;

import java.util.Map;

public class Teacher {
    private String lastName, firstName, patronymic;

    public Teacher(String lastName, String firstName, String patronymic){
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
    }

    public void setExamMark(Abiturient abiturient, String examName, int mark){
        Map<String, Exam> exams = abiturient.getExams();
        exams.get(examName).setMark(mark);
        System.out.println(this.toString() + " поставил абитуриенту " + abiturient.toString() + " оценку " + mark + " за экзамен " + examName);
    }

    @Override
    public String toString() {
        return  lastName + " " + firstName + " " + patronymic;
    }
}
