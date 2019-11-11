package com.kozyrev.simbirtraineeship.collections;

import androidx.annotation.NonNull;

import java.util.Map;
public class Student implements Comparable {

    private  String firstName, lastName, patronymic;
    private Integer trainingCourse, groupNumber, bornYear;
    private Map<String, Integer> marks;

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

    @Override
    public int compareTo(@NonNull Object o) {
        Student student2 = (Student) o;

        int trainingCourseCompare = trainingCourse.compareTo(student2.getTrainingCourse());
        int groupCompare = groupNumber.compareTo(student2.getGroupNumber());
        int lastNameCompare = lastName.compareTo(student2.getLastName());
        int firstNameCompare = firstName.compareTo(student2.getFirstName());

        return (trainingCourseCompare != 0 ? trainingCourseCompare :
                                (groupCompare != 0 ? groupCompare :
                                            (lastNameCompare != 0 ? lastNameCompare :
                                                    (firstNameCompare != 0 ? firstNameCompare : patronymic.compareTo(student2.getPatronymic())))));
    }

    @Override
    public String toString(){
        return "\n Курс: " + trainingCourse + " | Группа: "  + groupNumber + " | " + lastName + " " + firstName + " " + patronymic + "| Предмет/Оценка: " + marks.toString() + " | Год рождения: "  + bornYear;
    }
}
