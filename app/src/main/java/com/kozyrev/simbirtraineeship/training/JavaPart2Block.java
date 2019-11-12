package com.kozyrev.simbirtraineeship.training;

public class JavaPart2Block {
    /*
    3. Написать простое лямбда-выражение в переменной `myClosure`, лямбда-выражение должно выводить в консоль фразу "I love Java".
    Вызвать это лямбда-выражение. Далее написать функцию, которая будет запускать заданное лямбда-выражение заданное количество раз.
    Объявить функцию так: `public void repeatTask (int times, Runnable task)`.
    Функция должна запускать `times` раз лямбда-выражение `task` . Используйте эту функцию для печати "I love Java" 10 раз.
    */
    public Runnable myClosure = () -> System.out.println("I love java");

    public void repeatTask(int times, Runnable task){
        for (int i = 0; i < times; i++) task.run();
    }

    /*
    4. Условия: есть начальная позиция на двумерной плоскости, можно осуществлять последовательность шагов
    по четырем направлениям up, down, left, right. Размерность каждого шага равна 1.
    Задание:
  - Создать enum `Directions` с возможными направлениями движения
  - Создать метод, принимающий координаты и одно из направлений и возвращающий координату после перехода по направлению
  - Создать метод, осуществляющий несколько переходов от первоначальной координаты и выводящий координату после каждого перехода.
    Для этого внутри метода следует определить переменную `location` с начальными координатами (0,0) и  массив направлений,
    содержащий элементы [up, up, left, down, left, down, down, right, right, down, right],
    и програмно вычислить какие будут координаты у переменной `location` после выполнения этой последовательности шагов.
    Для вычисления результата каждого перемещения следует использовать созданный ранее метод перемещения на один шаг.
    */

    public enum Directions{
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }

    public int[] oneMove(int[] start, Directions direction){
        switch (direction){
            case UP:
                ++start[0];
                break;
            case DOWN:
                --start[0];
                break;
            case LEFT:
                --start[1];
                break;
            case RIGHT:
                ++start[1];
                break;
        }
        return start;
    }

    public void myWay(){
        int[] location = new int[]{0, 0};
        System.out.println("Location is: " + location[0] + ", " + location[1]);
        Directions[] directions = new Directions[]{Directions.UP,
                                                    Directions.UP,
                                                    Directions.LEFT,
                                                    Directions.DOWN,
                                                    Directions.LEFT,
                                                    Directions.DOWN,
                                                    Directions.DOWN,
                                                    Directions.RIGHT,
                                                    Directions.RIGHT,
                                                    Directions.DOWN,
                                                    Directions.RIGHT};
        for (Directions direction: directions) {
            location = this.oneMove(location, direction);
            System.out.println("Location is: " + location[0] + ", " + location[1]);
        }
    }

    /*
    5. Создать интерфейс Shape с двумя методами perimeter и area, выводящими периметр и площадь фигуры соответственно,
    после чего реализовать и использовать для вывода периметра и площади следующие классы, реализующие интерфейс Shape:
  - `Rectangle` - прямоугольник с двумя свойствами: ширина и длина
  - `Square` - квадрат с одним свойством: длина стороны
  - `Circle` - круг с одним свойством: диаметр круга
    */

    public interface Shape {

        public void perimeter();
        public void area();
    }

    public static class Rectangle implements Shape {

        private int width, height;

        public Rectangle(int width, int height){
            this.width = width;
            this.height = height;
        }

        @Override
        public void perimeter() {
            System.out.println((width + height) * 2);
        }

        @Override
        public void area() {
            System.out.println(width * height);
        }
    }

    public static class Square implements Shape {

        private int side;

        public Square(int side){
            this.side = side;
        }

        @Override
        public void perimeter() {
            System.out.println(side * 4);
        }

        @Override
        public void area() {
            System.out.println(side * side);
        }
    }

    public static class Circle implements Shape {

        private int diam;

        public Circle(int diam){
            this.diam = diam;
        }

        @Override
        public void perimeter() {
            System.out.println(diam * Math.PI);
        }

        @Override
        public void area() {
            System.out.println(0.25 * Math.PI * Math.pow(diam, 2));
        }
    }
}
