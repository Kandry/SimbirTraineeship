package com.kozyrev.simbirtraineeship.classes.ClassesTask6;

import java.util.Arrays;
import java.util.List;

public class University {

    private Faculty faculty = new Faculty("Информатика");
    private Teacher teacher = faculty.getTeacher();

    private List<String> exams = Arrays.asList("Программирование", "Высшая математика");

    private Abiturient abiturient1 = new Abiturient("Пупкин", "Ваcилий", "Иванович", exams);
    private Abiturient abiturient2 = new Abiturient("Иванов", "Иван", "Иванович", exams);

    public void init() {
        faculty.registration(abiturient1);
        faculty.registration(abiturient2);

        abiturient1.examIsDone(exams.get(0));
        abiturient1.examIsDone(exams.get(1));

        abiturient2.examIsDone(exams.get(0));
        abiturient2.examIsDone(exams.get(1));

        teacher.setExamMark(abiturient1, exams.get(0), 4);
        teacher.setExamMark(abiturient1, exams.get(1), 3);

        teacher.setExamMark(abiturient2, exams.get(0), 5);
        teacher.setExamMark(abiturient2, exams.get(1), 5);

        faculty.atFaculty(4);
    }
}
