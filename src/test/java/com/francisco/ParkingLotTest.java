package com.francisco;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    private ParkingLot parkingLot;

    @BeforeEach
    void setUp() {
        parkingLot = null;
    }

    @Test
    void testSmallCarParking(){
        Car car1 = new Car(VehicleSize.SMALL, "Me1", "123abc");
        Car car2 = new Car(VehicleSize.SMALL, "Me2", "123abc");
        Car car3 = new Car(VehicleSize.SMALL, "Me3", "123abc");

        parkingLot = new ParkingLot(9);

        parkingLot.parkCar(car1);
        parkingLot.parkCar(car2);
        parkingLot.parkCar(car3);

        int spotsTaken = parkingLot.getSmallSpotsTaken();
        assertEquals(3, spotsTaken);
    }

    @Test
    void testLargeCarParking(){
        Car car1 = new Car(VehicleSize.LARGE, "Me1", "123abc1");
        Car car2 = new Car(VehicleSize.LARGE, "Me2", "123abc2");
        Car car3 = new Car(VehicleSize.LARGE, "Me3", "123abc3");

        parkingLot = new ParkingLot(9);

        parkingLot.parkCar(car1);
        parkingLot.parkCar(car2);
        parkingLot.parkCar(car3);

        int spotsTaken = parkingLot.getLargeSpotsTaken();
        assertEquals(3, spotsTaken);
    }

    @Test
    void testOversizeCarParking(){
        Car car1 = new Car(VehicleSize.OVERSIZE, "Me1", "123abc1");
        Car car2 = new Car(VehicleSize.OVERSIZE, "Me2", "123abc2");
        Car car3 = new Car(VehicleSize.OVERSIZE, "Me3", "123abc3");

        parkingLot = new ParkingLot(9);

        parkingLot.parkCar(car1);
        parkingLot.parkCar(car2);
        parkingLot.parkCar(car3);

        int spotsTaken = parkingLot.getOversizeSpotsTaken();
        assertEquals(3, spotsTaken);
    }

    @Test
    void testAllSizeParking(){
        Car car1 = new Car(VehicleSize.SMALL, "Me1", "123abc1");
        Car car2 = new Car(VehicleSize.LARGE, "Me2", "123abc2");
        Car car3 = new Car(VehicleSize.OVERSIZE, "Me3", "123abc3");
        Car car4 = new Car(VehicleSize.LARGE, "Me4", "123abc4");
        Car car5 = new Car(VehicleSize.OVERSIZE, "Me5", "123abc5");

        parkingLot = new ParkingLot(9);

        parkingLot.parkCar(car1);
        parkingLot.parkCar(car2);
        parkingLot.parkCar(car3);
        parkingLot.parkCar(car4);
        parkingLot.parkCar(car5);

        int smallSpotsTaken = parkingLot.getSmallSpotsTaken();
        int largeSpotsTaken = parkingLot.getLargeSpotsTaken();
        int oversizeSpotsTaken = parkingLot.getOversizeSpotsTaken();

        //Check that each parking lot ArrayList has the correct number of cars
        assertEquals(1, smallSpotsTaken);
        assertEquals(2, largeSpotsTaken);
        assertEquals(2, oversizeSpotsTaken);
    }

    @Test
    void testRemoveCar(){
        Car car1 = new Car(VehicleSize.SMALL, "Me1", "123abc1");
        Car car2 = new Car(VehicleSize.SMALL, "Me2", "123abc2");
        Car car3 = new Car(VehicleSize.SMALL, "Me3", "123abc3");

        parkingLot = new ParkingLot(9);

        parkingLot.parkCar(car1);
        parkingLot.parkCar(car2);
        parkingLot.parkCar(car3);

        int spotsTaken = parkingLot.getSmallSpotsTaken();
        assertEquals(3, spotsTaken);

        //Take each small car out and make sure it is the correct size
        parkingLot.removeVehicle(0);
        spotsTaken = parkingLot.getSmallSpotsTaken();
        assertEquals(2, spotsTaken);

        parkingLot.removeVehicle(1);
        spotsTaken = parkingLot.getSmallSpotsTaken();
        assertEquals(1, spotsTaken);

        parkingLot.removeVehicle(2);
        spotsTaken = parkingLot.getSmallSpotsTaken();
        assertEquals(0, spotsTaken);
    }
}
