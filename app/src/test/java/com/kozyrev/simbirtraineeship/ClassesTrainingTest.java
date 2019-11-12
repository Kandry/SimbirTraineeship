package com.kozyrev.simbirtraineeship;

import com.kozyrev.simbirtraineeship.classes.ClassesTask6.Abiturient;
import com.kozyrev.simbirtraineeship.classes.ClassesTask6.Faculty;
import com.kozyrev.simbirtraineeship.classes.ClassesTask6.Teacher;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ClassesTrainingTest {

    @Test
    public void collectionTask0_emptyLists() {

        Faculty faculty = new Faculty("Информатика");
        Teacher teacher = faculty.getTeacher();

        List<String> exams = Arrays.asList("Программирование", "Высшая математика");

        Abiturient abiturient1 = new Abiturient("Пупкин", "Ваcилий", "Иванович", exams);
        Abiturient abiturient2 = new Abiturient("Иванов", "Иван", "Иванович", exams);

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

        assertEquals(0, 0);
    }
}
