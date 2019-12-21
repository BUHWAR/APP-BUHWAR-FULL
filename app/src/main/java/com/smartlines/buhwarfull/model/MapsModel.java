package com.smartlines.buhwarfull.model;

public class MapsModel {
    private double latitude;
    private double longitude;

    public MapsModel() {
        this.latitude = 0;
        this.longitude = 0;
    }

    public MapsModel(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
