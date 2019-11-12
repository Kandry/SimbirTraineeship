package com.kozyrev.simbirtraineeship.classes.ClassesTask7;

public class Human {
    private String lastName, firstName, patronymic;

    public Human(String lastName, String firstName, String patronymic){
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + patronymic;
    }
}
