package com.kozyrev.simbirtraineeship.classes.ClassesTask6;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Abiturient {
    private String lastName, firstName, patronymic;
    private boolean isReg = false;
    private Map<String, Exam> exams;

    public Abiturient(String lastName, String firstName, String patronymic, List<String> examNames){
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.exams = new HashMap<>();
        for (String examName : examNames){
            exams.put(examName, new Exam(examName));
        }
    }

    public void examIsDone(String examName){
        exams.get(examName).setDone(true);

        System.out.println(this.toString() + " сдал экзамен " + examName);
    }

    public double averageMark(){
        double average = 0;
        for (Map.Entry<String, Exam> entry : exams.entrySet()) {
            average += entry.getValue().getMark();
        }
        return average / exams.size();
    }

    public void setExams(Map<String, Exam> exams) {
        this.exams = exams;
    }

    public Map<String, Exam> getExams() {
        return exams;
    }

    public void setReg(boolean reg) {
        isReg = reg;
    }

    public boolean isReg() {
        return isReg;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + patronymic;
    }
}
