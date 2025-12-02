package com.francisco;

public class Car {

    private final VehicleSize vehicleSize;
    private final String ownerName;
    private final String make;
    private final String model;
    private final int year;
    private String color; //Optional
    private int slotId; //Where the vehicle is parked

    //Constructor 1 - No color
    Car(VehicleSize vehicleSize, String ownerName, String make, String model, int year){
        this.vehicleSize = vehicleSize;
        this.ownerName = ownerName;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    //Constructor 2 - includes color
    Car(VehicleSize vehicleSize, String ownerName, String make, String model, int year, String color){
        this.vehicleSize = vehicleSize;
        this.ownerName = ownerName;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
    }

    //Return formatted vehicle info for parking lot printing
    public String toString(){
        return make + " " + model;
    }

    //Class Getters:

    public VehicleSize getVehicleSize() {
        return vehicleSize;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public int getSlotId() {
        return slotId;
    }

    //Class Setters:

    public void setColor(String color) {
        this.color = color;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }



}
