package com.kozyrev.simbirtraineeship.classes.ClassesTask6;

import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private String facultyName;
    private List<Abiturient> abiturients = new ArrayList<>();
    private Teacher teacher;

    public Faculty(String facultyName){
        this.facultyName = facultyName;
        teacher = new Teacher("Андропов", "Ваилий", "Иванович");
    }

    public void registration(Abiturient abiturient){
        abiturients.add(abiturient);
        abiturient.setReg(true);
        System.out.println("Абитуриент " + abiturient.toString() + " зарегистрировался на факультете " + this.toString());
    }

    public void atFaculty(double minAverage){
        for (Abiturient abiturient: abiturients) {
            double averageAbiturient = abiturient.averageMark();
            if (averageAbiturient > minAverage) System.out.println("Абитуриент " + abiturient.toString() + " прошёл на факультет " + this.toString() + ". Средний бал: " + averageAbiturient);
            else System.out.println("Абитуриент " + abiturient.toString() + " не прошёл на факультет " + this.toString() + ". Средний бал: " + averageAbiturient);
        }
    }

    public Teacher getTeacher() {
        return teacher;
    }

    @Override
    public String toString() {
        return facultyName;
    }
}
