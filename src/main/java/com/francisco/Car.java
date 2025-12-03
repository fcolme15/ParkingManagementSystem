package com.francisco;

public class Car {

    private final VehicleSize vehicleSize;
    private final String ownerName;
    private final String licensePlate;
    private final String make;
    private final String model;
    private final int year;
    private final String color; //Optional
    private int slotId; //Where the vehicle is parked

    //Constructor 1 - No car details
    Car(VehicleSize vehicleSize, String ownerName, String licensePlate){
        this.vehicleSize = vehicleSize;
        this.ownerName = ownerName;
        this.licensePlate = licensePlate;
        this.make = "Unknown";
        this.model = "Unknown";
        this.year = -1;
        this.color = "Unknown";
    }

    //Constructor 2 - includes car details
    Car(VehicleSize vehicleSize, String ownerName, String licensePlate, String make, String model, int year){
        this.vehicleSize = vehicleSize;
        this.ownerName = ownerName;
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = "Unknown";
    }

    //Constructor 3 - includes color
    Car(VehicleSize vehicleSize, String ownerName, String licensePlate, String make, String model, int year, String color){
        this.vehicleSize = vehicleSize;
        this.ownerName = ownerName;
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
    }

    //Return formatted vehicle info for parking lot removal
    public String toString(){
        return ownerName + "'s car: " + make + " " + model + " " + year;
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

    public String getLicensePlate(){
        return licensePlate;
    }

    //Class Setters:

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }
}
