package com.kozyrev.simbirtraineeship.classes.ClassesTask6;

import java.util.Map;

public class Teacher extends Human{

    public Teacher(String lastName, String firstName, String patronymic){
        super(lastName, firstName, patronymic);
    }

    public void setExamMark(Abiturient abiturient, String examName, int mark){
        Map<String, Exam> exams = abiturient.getExams();
        exams.get(examName).setMark(mark);
        System.out.println(this.toString() +
                " поставил абитуриенту " + abiturient.toString() +
                " оценку " + mark +
                " за экзамен " + examName);
    }
}
