package com.francisco;

import java.util.*;

public class ParkingLot {

    //Variables containing parking lot capacities
    private final int parkingLotCapacity;
    private final int smallSpotsCapacity;
    private final int largeSpotsCapacity;
    private final int oversizeSpotsCapacity;

    //Lists containing the parked cars
    private List<Car> smallSpots;
    private List<Car> largeSpots;
    private List<Car> oversizeSpots;

    ParkingLot(int parkingLotCapacity) {
        this.parkingLotCapacity = parkingLotCapacity;

        //Get the capacity for each size based on max capacity
        Map<String, Integer> spots = findNumberOfSpots();

        this.smallSpotsCapacity = spots.get("small");
        this.largeSpotsCapacity = spots.get("large");
        this.oversizeSpotsCapacity = spots.get("oversize");

        //Create the lists holding the cars and initialize to null
        this.smallSpots = new ArrayList<>(Collections.nCopies(smallSpotsCapacity, null));
        this.largeSpots = new ArrayList<>(Collections.nCopies(largeSpotsCapacity, null));
        this.oversizeSpots = new ArrayList<>(Collections.nCopies(oversizeSpotsCapacity, null));
    }

    ParkingLot(int smallSpots, int largeSpots, int oversizeSpots) {
        this.parkingLotCapacity = smallSpots + largeSpots + oversizeSpots;
        this.smallSpotsCapacity = smallSpots;
        this.largeSpotsCapacity = largeSpots;
        this.oversizeSpotsCapacity = oversizeSpots;

        //Create the lists holding the cars and initialize to null
        this.smallSpots = new ArrayList<>(Collections.nCopies(smallSpotsCapacity, null));
        this.largeSpots = new ArrayList<>(Collections.nCopies(largeSpotsCapacity, null));
        this.oversizeSpots = new ArrayList<>(Collections.nCopies(oversizeSpotsCapacity, null));
    }

    public void parkCar(Car car) {
        assert car != null : "car parked can't be null";
    }

    public void parkCar(Car car, int spot){
        assert(car != null);
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
