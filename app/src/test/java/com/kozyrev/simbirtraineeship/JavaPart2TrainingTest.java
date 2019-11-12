package com.kozyrev.simbirtraineeship;

import com.kozyrev.simbirtraineeship.training.JavaPart2Block;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaPart2TrainingTest {

    private static JavaPart2Block javaPart2Block;
    private List<JavaPart2Block.Shape> shapes = new ArrayList<>(Arrays.asList(new JavaPart2Block.Rectangle(5, 8), new JavaPart2Block.Square(7), new JavaPart2Block.Circle(6)));

    @BeforeClass
    public static void init(){
        javaPart2Block = new JavaPart2Block();
    }

    @Test
    public void lambdaTask0(){
        javaPart2Block.myClosure.run();
    }

    @Test
    public void lambdaTask0_times(){
        javaPart2Block.repeatTask(10, javaPart2Block.myClosure);
    }

    @Test
    public void movesTask0(){
        javaPart2Block.myWay();
    }

    @Test
    public void shapesTask0(){
        for (JavaPart2Block.Shape shape: shapes) {
            System.out.println(shape.getClass().getName());
            shape.perimeter();
            shape.area();
        }
    }
}
