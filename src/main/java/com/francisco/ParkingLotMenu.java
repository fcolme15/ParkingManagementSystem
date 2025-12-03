package com.francisco;

import java.util.Scanner;

public class ParkingLotMenu {

    private ParkingLot parkingLot;
    private final Scanner scanner;
    private final FileManager fileManager;
    private String inputString;
    private int inputInt;
    private MenuOptions menuOption;

    private int parkingLotCapacity;

    ParkingLotMenu() {
        this.parkingLot = null;
        this.scanner = new Scanner(System.in);
        this.menuOption = null;
        fileManager = new FileManager();
    }

    public void run(){
        System.out.println("Welcome to the Parking Lot Management System");

        loadInitialParkingLot();
        parkingLotCapacity = parkingLot.getParkingLotCapacity();


        do {
            printMenu();
            inputInt = scanner.nextInt();
            scanner.nextLine();
            menuOption = MenuOptions.fromInt(inputInt);
            if (menuOption == null){
                System.out.println("null option");
            }
            switch(menuOption){
                case PARK:
                    parkCar();
                    break;
                case REMOVE:
                    removeCar();
                    break;
                case SEARCH:
                    searchCar();
                    break;
                case PRINT:
                    parkingLot.printParkingLot();
                    break;
                case SAVELOT:
                    System.out.println("Please type the file name: ");
                    inputString = scanner.nextLine().trim();
                    fileManager.saveParkingLot(inputString, parkingLot);
                    break;
                case EXIT:
                    inputString = "exit";
                    break;
                case NONE:
                    System.out.println("Invalid option");
                    System.out.println("Please try again");
                    System.out.println();
                    break;
            }
        } while(!inputString.equalsIgnoreCase("exit"));
    }

    private void printMenu(){
        System.out.println("Parking Lot Menu");
        System.out.println("Select from the following options using an integer:");
        System.out.println("1. Park a new car");
        System.out.println("2. Remove a parked car");
        System.out.println("3. Search for a cars parking lot ID");
        System.out.println("4. Print parking lot");
        System.out.println("5. Save Parking Lot");
        System.out.println("6. Exit");
        System.out.println("Selection: ");
    }

    private void parkCar(){
        Car newCar = buildCar();

        System.out.println("Enter the parking spot ID number to park the car: (-1 for automatic system)");
        inputInt = scanner.nextInt();
        scanner.nextLine();

        while (inputInt < -1 || inputInt >= parkingLotCapacity) {
            System.out.println("Invalid ID try again(0-" + (parkingLotCapacity-1) + " or -1 for automatic): ");
            inputInt = scanner.nextInt();
            scanner.nextLine();
        }

        String result;
        if (inputInt == -1) {
            try{
                result = parkingLot.parkCar(newCar);
                System.out.println(result);
            }
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        else{
            try{
                result = parkingLot.parkCar(newCar, inputInt);
                System.out.println(result);
            }
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }

    }

    private Car buildCar(){
        String ownerName, licensePlate;
        VehicleSize vehicleSize;

        System.out.println("Enter the owner name: ");
        ownerName = scanner.nextLine().trim();
        System.out.println("Enter the license plate: ");
        licensePlate = scanner.nextLine().trim();
        System.out.println("Enter the vehicle size: ");
        System.out.println("1.) Small(Small/Compact) 2.) Large(Full-Sized) 3.) Oversize(SUV/Truck)");
        int size = scanner.nextInt();
        scanner.nextLine();
        vehicleSize = VehicleSize.fromInt(size);
        if (vehicleSize == null){
            System.out.println("Coming out as null");
        }
        System.out.println("Enter more Car information? (Y/N)");
        inputString = scanner.nextLine().trim();
        if (inputString.equalsIgnoreCase("Y")) {
            String make, model;
            int year;

            System.out.println("Enter the make: ");
            make = scanner.nextLine().trim();
            System.out.println("Enter the model: ");
            model = scanner.nextLine().trim();
            System.out.println("Enter the year: ");
            year = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter Car color? (Y/N)");
            inputString = scanner.nextLine().trim();

            if (inputString.equalsIgnoreCase("y")) {
                System.out.println("Enter Car color");
                String color = scanner.nextLine().trim();

                return new Car(vehicleSize, ownerName, licensePlate, make, model, year, color);
            }
            else{
                return new Car(vehicleSize, ownerName, licensePlate, make, model, year);
            }


        }
        else{
            return new Car(vehicleSize, ownerName, licensePlate);
        }
    }

    private void removeCar(){
        System.out.println("Enter the parking lot ID number to remove the car");
        inputInt = scanner.nextInt();
        scanner.nextLine();

        while (inputInt < 0 || inputInt >= parkingLotCapacity) {
            System.out.println("Invalid ID try again(0-" + (parkingLotCapacity-1) + "): ");
            inputInt = scanner.nextInt();
            scanner.nextLine();
        }

        String result = parkingLot.removeVehicle(inputInt);
        System.out.println(result);
        System.out.println();
    }

    private void searchCar(){

    }

    private void loadInitialParkingLot(){
        System.out.println("Would you like to load in a saved Parking Lot? (Y/N) ");
        inputString = scanner.nextLine().trim();

        if (inputString.equalsIgnoreCase("Y")) {
            System.out.println("Please type the file name: ");
            inputString = scanner.nextLine().trim();

            parkingLot = fileManager.getParkingLot(inputString);
            if (parkingLot == null){
                System.out.println("Error object not found");
            }
            parkingLot.printParkingLot();
            System.out.println();
        }
        else{
            System.out.println("Please input the parking lot capacity: ");
            inputInt = scanner.nextInt();
            scanner.nextLine();

            while (inputInt <= 0) {
                System.out.println("Invalid, parking lot capacity must be greater than 0. Try again: ");
                inputInt = scanner.nextInt();
                scanner.nextLine();
            }

            parkingLot = new ParkingLot(inputInt);
            System.out.println("Parking lot of size " + inputInt + " has been created.");
            System.out.println();
        }
    }



}
