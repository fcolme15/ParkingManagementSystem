package com.francisco;

public enum VehicleSize{
    SMALL(1),
    LARGE(2),
    OVERSIZE(3);

    private final int size;

    VehicleSize(int size){
        this.size = size;
    }

    public int getSize(){
        return this.size;
    }
}
