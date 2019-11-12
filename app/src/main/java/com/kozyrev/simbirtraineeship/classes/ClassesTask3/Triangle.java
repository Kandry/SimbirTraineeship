package com.kozyrev.simbirtraineeship.classes.ClassesTask3;

public class Triangle {

    private double x1, x2, x3, y1, y2, y3;
    private double firstSide, secondSide, thirdSide;
    private double firstAngle, secondAngle, thirdAngle;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3, double firstAngle, double secondAngle, double thirdAngle) throws Exception{
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.firstAngle = firstAngle;
        this.secondAngle = secondAngle;
        this.thirdAngle = thirdAngle;

        if ((firstAngle + secondAngle + thirdAngle) != 180) throw new Exception("The sum of the angles of the triangle must be 180 degrees");

        firstSide = this.side(x1, x2, y1, y2);
        secondSide = this.side(x2, x3, y2, y3);
        thirdSide = this.side(x3, x1, y3, y1);
    }

    public double side(double x1, double x2, double y1, double y2){
        return Math.pow((Math.pow((x2 - x1), 2)+ Math.pow((x2 - x1), 2)), 0.5);
    }

    public double perimeter(){
        return firstSide + secondSide + thirdSide;
    }

    public double square(){
        double p = this.perimeter() / 2;
        return Math.sqrt(p * (p - firstSide) * (p - secondSide) * (p - thirdSide));
    }

    public double[] medianPoint(){
        return new double[]{(x1 + x2 + x3) / 3, (y1 + y2 + y3) / 3};
    }

    public double[] getPoints(){
        return new double[]{x1, y1, x2, y2, x3, y3};
    }

    public double[] getAngles(){
        return new double[]{firstAngle, secondAngle, thirdAngle};
    }

    public double[] getSides(){
        return new double[]{firstSide, secondSide, thirdSide};
    }

    @Override
    public String toString(){
        return "Sides: " + firstSide + ", " + secondSide + ", " + thirdSide + " | Angles: " + firstAngle + ", " + secondAngle + ", " + thirdAngle;
    }
}
