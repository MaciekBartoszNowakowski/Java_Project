package org.example.Logic;

import java.util.LinkedList;
import java.util.List;

public class Lane {
    LinkedList<Vehicle> vehicles = new LinkedList<>();

    private final String laneName;

    public Lane(String laneName){
        this.laneName = laneName;
    };

    public int carsAmount(){
        return this.vehicles.size();
    }

    public void addCar(Vehicle newVehicle){
        this.vehicles.add(newVehicle);
    }

    public Vehicle getVehicle(){
        return this.vehicles.removeFirst();
    }

    public Vehicle lookUpVehicle(){
        if (this.carsAmount()!=0) {
            return this.vehicles.get(0);
        }
        else{
            return null;
        }
    }

}
