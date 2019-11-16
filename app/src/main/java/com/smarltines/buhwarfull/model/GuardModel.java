package com.smarltines.buhwarfull.model;

public class GuardModel {
    private String nameGuard;
    private String numberGuard;
    private String emailGuard;
    private String descriptionGuard;
    private String imgGuar;
    private boolean statusGuard;


    public GuardModel(String nameGuard, String numberGuard, String emailGuard, String descriptionGuard,String imgGuar,boolean statusGuard) {
        this.nameGuard = nameGuard;
        this.numberGuard = numberGuard;
        this.emailGuard = emailGuard;
        this.descriptionGuard = descriptionGuard;
        this.imgGuar = imgGuar;
        this.statusGuard = statusGuard;

    }

    public GuardModel() {
        this.nameGuard = "";
        this.numberGuard = "";
        this.emailGuard = "";
        this.descriptionGuard = "";
        this.imgGuar = "";
        this.statusGuard = false;

    }

    public String getNameGuard() {
        return nameGuard;
    }

    public void setNameGuard(String nameGuard) {
        this.nameGuard = nameGuard;
    }

    public String getNumberGuard() {
        return numberGuard;
    }

    public void setNumberGuard(String numberGuard) {
        this.numberGuard = numberGuard;
    }

    public String getEmailGuard() {
        return emailGuard;
    }

    public void setEmailGuard(String emailGuard) {
        this.emailGuard = emailGuard;
    }

    public String getDescriptionGuard() {
        return descriptionGuard;
    }

    public void setDescriptionGuard(String descriptionGuard) {
        this.descriptionGuard = descriptionGuard;
    }

    public String getImgGuar() {
        return imgGuar;
    }

    public void setImgGuar(String imgGuar) {
        this.imgGuar = imgGuar;
    }

    public boolean isStatusGuard() {
        return statusGuard;
    }

    public void setStatusGuard(boolean statusGuard) {
        this.statusGuard = statusGuard;
    }
}
