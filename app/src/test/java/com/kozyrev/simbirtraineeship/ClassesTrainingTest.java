package com.kozyrev.simbirtraineeship;

import com.kozyrev.simbirtraineeship.classes.ClassesTask1;
import com.kozyrev.simbirtraineeship.classes.ClassesTask2;
import com.kozyrev.simbirtraineeship.classes.ClassesTask3.Triangle;
import com.kozyrev.simbirtraineeship.classes.ClassesTask4.Time;
import com.kozyrev.simbirtraineeship.classes.ClassesTask5.AbonentBlock;
import com.kozyrev.simbirtraineeship.classes.ClassesTask6.Abiturient;
import com.kozyrev.simbirtraineeship.classes.ClassesTask6.Faculty;
import com.kozyrev.simbirtraineeship.classes.ClassesTask6.Teacher;
import com.kozyrev.simbirtraineeship.classes.ClassesTask6.University;
import com.kozyrev.simbirtraineeship.classes.ClassesTask7.Shop;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ClassesTrainingTest {

    @Test
    public void classesTask1() {
        ClassesTask1 classesTask1 = new ClassesTask1(10, 15);
        classesTask1.printVariables();
        classesTask1.changeVariables(7, -68);
        classesTask1.printVariables();
        System.out.println("Сумма переменных = " + classesTask1.sumVariables() + ", максимальное значение перенных = " + classesTask1.maxVariable());
    }

    @Test
    public void classesTask2() {
        try {
            ClassesTask2 classesTask2 = new ClassesTask2(10);
            classesTask2.fillArray();
            classesTask2.printArray();
            System.out.println("Количество вхождений элемента 1 = " + classesTask2.countElements(1));
            classesTask2.randomReplaceArrayElements();
            classesTask2.printArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void classesTask3() {
        try {
            Triangle triangle = new Triangle(0, 0, 5, 4, -2, -3, 60, 60, 60);
            System.out.println(triangle.toString() + " периметр: " + triangle.perimeter() + " площадь: " + triangle.square() + " точка пересечения медиан: [" + triangle.medianPoint()[0] + ", " + triangle.medianPoint()[1] + "]");
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void classesTask4() {
        try {
            Time time = new Time(15, 28, 13);
            time.changeTime(3600, 0, 0);
            System.out.println(time.toString());
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void classesTask5() {
        AbonentBlock abonentBlock = new AbonentBlock();
        abonentBlock.getAbonentsManyInCityTimeSpeak(100);
        abonentBlock.getAbonentsOutCitySpeaked();
        abonentBlock.sortAbonents();
    }

    @Test
    public void classesTask6() {
        University university = new University();
        university.init();
    }

    @Test
    public void classesTask7() {
        Shop shop = new Shop();
        shop.init();
    }

}
