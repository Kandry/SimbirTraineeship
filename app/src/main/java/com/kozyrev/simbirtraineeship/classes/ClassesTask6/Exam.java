package com.kozyrev.simbirtraineeship.classes.ClassesTask6;

public class Exam {
    private boolean isDone = false;
    private String examName;
    private int mark;

    public Exam(String examName){
        this.examName = examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamName() {
        return examName;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getMark() {
        return mark;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
