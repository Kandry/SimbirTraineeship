package com.kozyrev.simbirtraineeship.entities;

import java.util.Map;

public class Student {

    String firstName, lastName, patronymic;
    int bornYear, trainingCourse, groupNumber;
    Map<String, Integer> marks;

    public Student(String firstName, String lastName, String patronymic, int bornYear, int trainingCourse, int groupNumber, Map<String, Integer> marks){
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.bornYear = bornYear;
        this.trainingCourse = trainingCourse;
        this.groupNumber = groupNumber;
        this.marks = marks;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getPatronymic(){
        return patronymic;
    }

    public void setPatronymic(String patronymic){
        this.patronymic = patronymic;
    }

    public int getBornYear(){
        return bornYear;
    }

    public void setBornYear(int bornYear){
        this.bornYear = bornYear;
    }

    public int getTrainingCourse(){
        return trainingCourse;
    }

    public void setTrainingCourse(int trainingCourse){
        this.trainingCourse = trainingCourse;
    }

    public int getGroupNumber(){
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber){
        this.groupNumber = groupNumber;
    }

    public Map<String, Integer> getMarks(){
        return marks;
    }

    public void setMarks(Map<String, Integer> marks){
        this.marks = marks;
    }
}
