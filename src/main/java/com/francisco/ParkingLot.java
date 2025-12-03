package com.francisco;

import java.util.*;

public class ParkingLot {

    //Variables containing parking lot capacities
    private final int parkingLotCapacity;
    private final int smallSpotsCapacity;
    private final int largeSpotsCapacity;
    private final int oversizeSpotsCapacity;

    //Variables containing parking lot current usage
    private int smallSpotsTaken;
    private int largeSpotsTaken;
    private int oversizeSpotsTaken;

    //Lists containing the parked cars
    private List<Car> smallSpots;
    private List<Car> largeSpots;
    private List<Car> oversizeSpots;

    //Constructor initializing a new parking lot with only total capacity
    ParkingLot(int parkingLotCapacity) {
        this.parkingLotCapacity = parkingLotCapacity;

        //Get the capacity for each size based on max capacity
        Map<String, Integer> spots = findNumberOfSpots();

        this.smallSpotsCapacity = spots.get("small");
        this.largeSpotsCapacity = spots.get("large");
        this.oversizeSpotsCapacity = spots.get("oversize");

        smallSpotsTaken = 0;
        largeSpotsTaken = 0;
        oversizeSpotsTaken = 0;

        //Create the lists holding the cars and initialize to null
        this.smallSpots = new ArrayList<>(Collections.nCopies(smallSpotsCapacity, null));
        this.largeSpots = new ArrayList<>(Collections.nCopies(largeSpotsCapacity, null));
        this.oversizeSpots = new ArrayList<>(Collections.nCopies(oversizeSpotsCapacity, null));
    }

    //Constructor initializing a new parking lot with individual capacities
    ParkingLot(int smallSpots, int largeSpots, int oversizeSpots) {
        this.parkingLotCapacity = smallSpots + largeSpots + oversizeSpots;
        this.smallSpotsCapacity = smallSpots;
        this.largeSpotsCapacity = largeSpots;
        this.oversizeSpotsCapacity = oversizeSpots;

        smallSpotsTaken = 0;
        largeSpotsTaken = 0;
        oversizeSpotsTaken = 0;

        //Create the lists holding the cars and initialize to null
        this.smallSpots = new ArrayList<>(Collections.nCopies(smallSpotsCapacity, null));
        this.largeSpots = new ArrayList<>(Collections.nCopies(largeSpotsCapacity, null));
        this.oversizeSpots = new ArrayList<>(Collections.nCopies(oversizeSpotsCapacity, null));
    }

    public String parkCar(Car car){
        if (car == null) {
            throw new IllegalArgumentException("Car parked cannot be null");
        }

        VehicleSize vehicleSize = car.getVehicleSize();
        int spotId = 0;
        switch (vehicleSize){
            case SMALL:
                if (smallSpotsTaken == smallSpotsCapacity){
                    throw new IllegalArgumentException("No more spaces available for the cars size: Small");
                }

                while(smallSpots.get(spotId) != null){
                    spotId++;
                }

                smallSpots.set(spotId, car);
                smallSpotsTaken++;
                break;
            case LARGE:
                if (largeSpotsTaken == largeSpotsCapacity){
                    throw new IllegalArgumentException("No more spaces available for the cars size: Large");
                }

                while(largeSpots.get(spotId) != null){
                    spotId++;
                }

                largeSpots.set(spotId, car);
                largeSpotsTaken++;
                break;
            case OVERSIZE:
                if (oversizeSpotsTaken == oversizeSpotsCapacity){
                    throw new IllegalArgumentException("No more spaces available for the cars size: Oversize");
                }

                while(oversizeSpots.get(spotId) != null){
                    spotId++;
                }

                oversizeSpots.set(spotId, car);
                oversizeSpotsTaken++;
                break;
        }

        car.setSlotId(spotId); //Set car obj to know its spotId
        return car.toString() + " has been parked at " + spotId;
    }

    public String parkCar(Car car, int spotId){
        if (car == null) {
            throw new IllegalArgumentException("Car parked cannot be null");
        }

        VehicleSize vehicleSize = car.getVehicleSize();
        switch (vehicleSize){
            case SMALL:
                if (spotId >= smallSpotsCapacity){
                    throw new IllegalArgumentException("Parking spot Id selected not correct for the car size: Small");
                }
                else if (smallSpots.get(spotId) != null){
                    throw new IllegalArgumentException("Parking spot Id selected is taken");
                }

                smallSpots.set(spotId, car);
                smallSpotsTaken++;
                break;
            case LARGE:
                if (spotId < smallSpotsCapacity || spotId >= smallSpotsCapacity + largeSpotsCapacity){
                    throw new IllegalArgumentException("Parking spot Id selected not correct for the car size: Large");
                }
                else if (largeSpots.get(spotId - smallSpotsCapacity) != null){
                    throw new IllegalArgumentException("Parking spot Id selected is taken");
                }

                largeSpots.set(spotId - smallSpotsCapacity, car);
                largeSpotsTaken++;
                break;
            case OVERSIZE:
                if (spotId < smallSpotsCapacity + largeSpotsCapacity){
                    throw new IllegalArgumentException("Parking spot Id selected not correct for the car size: Oversize");
                }
                else if (largeSpots.get(spotId - smallSpotsCapacity - largeSpotsCapacity) != null){
                    throw new IllegalArgumentException("Parking spot Id selected is taken");
                }

                oversizeSpots.set(spotId - smallSpotsCapacity - largeSpotsCapacity, car);
                oversizeSpotsTaken++;
                break;
        }

        car.setSlotId(spotId); //Set car obj to know its spotId
        return car.toString() + " has been parked at " + spotId;
    }

    public String removeVehicle(int spotId){
        Car carRemoved = null;

        if (spotId < smallSpotsCapacity){
            carRemoved = smallSpots.get(spotId);
        }
        else if (spotId < smallSpotsCapacity + largeSpotsCapacity){
            carRemoved = largeSpots.get(spotId - smallSpotsCapacity);
        }
        else {
            carRemoved = oversizeSpots.get(spotId - smallSpotsCapacity - largeSpotsCapacity);
        }

        if (carRemoved == null) {
            return "No car is in the parking spot with Id: " + spotId;
        }
        else {
            return carRemoved.toString() + " has been removed";
        }
    }

    public void printParkingLot(){
        int spotsInRow = 5;
        List<Car> currentLot = smallSpots;
        char spotType = 'S';

        for (int spotId = 0; spotId < parkingLotCapacity; spotId++){
            if (currentLot == smallSpots && spotId >= smallSpotsCapacity){
                currentLot = largeSpots;
                spotType = 'L';
            }
            else if (currentLot == largeSpots && spotId >= largeSpotsCapacity){
                currentLot = oversizeSpots;
                spotType = 'O';
            }

            String spotStatus = currentLot.get(spotId) == null ? "Empty" : currentLot.get(spotId).getLicensePlate();
            System.out.printf("%c-%d-3d -> %s-10s", spotType, spotId, spotStatus);
        }
    }

    private Map<String, Integer> findNumberOfSpots() {
        Map<String, Integer> spots = new HashMap<>();

        int smallExtras = 0, largeExtras = 0;
        int evenSplit = parkingLotCapacity / 3;
        int odd = parkingLotCapacity % 3;

        //Count extra spots for small and large if not split evenly
        if (odd != 0){
            if (odd == 1){
                smallExtras += 1;
            }
            if (odd == 2){
                smallExtras += 1;
                largeExtras += 1;
            }
        }

        spots.put("small", evenSplit + smallExtras);
        spots.put("large", evenSplit + largeExtras);
        spots.put("oversize", evenSplit);

        return spots;
    }

    public int getParkingLotCapacity() {
        return parkingLotCapacity;
    }

    public int getSmallSpotsCapacity() {
        return smallSpotsCapacity;
    }

    public int getLargeSpotsCapacity() {
        return largeSpotsCapacity;
    }

    public int getOversizeSpotsCapacity() {
        return oversizeSpotsCapacity;
    }

    public List<Car> getSmallSpots() {
        return smallSpots;
    }

    public List<Car> getLargeSpots() {
        return largeSpots;
    }

    public List<Car> getOversizeSpots() {
        return oversizeSpots;
    }

}
