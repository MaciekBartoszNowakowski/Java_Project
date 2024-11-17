package org.example.Logic;

import java.util.LinkedList;
import java.util.List;

public class Lane {
    LinkedList<Vehicle> vehicles = new LinkedList<>();

    private final WorldDirection laneName;

    private Lights currentLight = Lights.RED;

    public Lane(WorldDirection laneName){
        this.laneName = laneName;
    };

    public int carsAmount(){
        return this.vehicles.size();
    }

    public void addVehicle(Vehicle newVehicle){
        this.vehicles.add(newVehicle);
    }

    public Vehicle getVehicle(){
        if (currentLight == Lights.GREEN && this.carsAmount()!=0) {
            return this.vehicles.removeFirst();
        }
        else{
            return null;
        }
    }

    public void blockLane(){
        currentLight = currentLight.block();
    }

    public void letLane(){
        currentLight = currentLight.let();
    }


    public Vehicle lookUpVehicle(){
        if (this.carsAmount()!=0) {
            return this.vehicles.get(0);
        }
        else{
            return null;
        }
    }

    public int importance(int currentStep){
        Vehicle currentVehicle = this.lookUpVehicle();
        if(currentVehicle!=null) {
            return this.carsAmount() * 2 + currentVehicle.waitingSteps(currentStep);
        }
        else{
            return 0;
        }
    }

    public WorldDirection getLaneName(){
        return laneName;
    }


}
