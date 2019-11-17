package com.smarltines.buhwarfull.model;

public class RegisterModel {

    private String day;
    private String description;

    public RegisterModel(String day, String description) {
        this.day = day;
        this.description = description;
    }

    public RegisterModel() {
        this.day = "";
        this.description = "";
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
