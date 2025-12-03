package com.francisco;

public enum VehicleSize{
    SMALL(1),
    LARGE(2),
    OVERSIZE(3);

    private final int size;

    VehicleSize(int size){
        this.size = size;
    }

    //Convert the integer input into Enum representation
    public static VehicleSize fromInt(int input){
        VehicleSize[] sizes = VehicleSize.values();

        if (input >= 0 && input <= sizes.length){ //Check bounds
            return sizes[input - 1];
        }
        return null;
    }
}
