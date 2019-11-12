package com.kozyrev.simbirtraineeship.classes;

public class ClassesTask1 {
   private int a, b;

   public ClassesTask1(int a, int b){
      this.a = a;
      this.b = b;
   }

   public void printVariables(){
      System.out.println("Variables: " + a + ", " + b);
   }

   public void changeVariables(int a, int b){
      this.a = a;
      this.b = b;
   }

   public int sumVariables(){
      return a + b;
   }

   public int maxVariable(){
      return (a >= b) ? a : b;
   }
}
