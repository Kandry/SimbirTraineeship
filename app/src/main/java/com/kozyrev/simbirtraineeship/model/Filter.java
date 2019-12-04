package com.kozyrev.simbirtraineeship.model;

public class Filter {

    private String name;
    private boolean isActive = true;

    public Filter(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
