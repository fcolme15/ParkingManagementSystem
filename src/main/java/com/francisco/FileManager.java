package com.francisco;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    private Gson gson;
    private ParkingLot parkingLot;
    private String path = "src/main/resources/";

    FileManager(){
        gson = new Gson();
    }

    public ParkingLot getParkingLot(String fileName){
        fileName = path + fileName + ".json";

        try (FileReader fileReader = new FileReader(fileName)) {
            System.out.println("Parking lot loaded from " + fileName);
            System.out.println();
            parkingLot = gson.fromJson(fileReader, ParkingLot.class);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found. " + fileName);
            parkingLot = null;
        }
        catch (IOException e){
            System.out.println("Error reading file." + e.getMessage());
            parkingLot = null;
        }
        return parkingLot;
    }

    public void saveParkingLot(String fileName, ParkingLot parkingLot){
        fileName = path + fileName + ".json";
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            String json = gson.toJson(parkingLot);
            fileWriter.write(json);
        }
        catch(IOException e){
            System.out.println("Error writing to file." + e.getMessage());
        }
    }
}
