package org.example.Logic;

import java.util.LinkedList;
import java.util.List;

public class Lane {
    LinkedList<Vehicle> vehicles = new LinkedList<>();

    private final WorldDirection laneName;

    private Lights currentLight = Lights.RED;

    public Lane(WorldDirection laneName) {
        this.laneName = laneName;
    }


    public int carsAmount() {
        return this.vehicles.size();
    }

    public void addVehicle(Vehicle newVehicle) {
        this.vehicles.add(newVehicle);
    }

    public Vehicle getVehicle() {
        if (this.carsAmount() != 0) {
            if (this.currentLight == Lights.GREEN) {
                return this.vehicles.removeFirst();
            } else if (this.currentLight == Lights.RIGHT) {
                Vehicle firstVehicle = this.lookUpVehicle();
                if (firstVehicle.getDestinationDirection() == this.laneName.right()) {
                    return this.vehicles.removeFirst();
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public void blockLane() {
        currentLight = currentLight.block();

    }

    public void letLane() {
        currentLight = currentLight.let();

    }

    public void conditionalLight() {
        currentLight = currentLight.turnRight();
    }


    public Vehicle lookUpVehicle() {
        if (this.carsAmount() != 0) {
            return this.vehicles.get(0);
        } else {
            return null;
        }
    }

    public int importance(int currentStep) {
        Vehicle currentVehicle = this.lookUpVehicle();
        if (currentVehicle != null) {
            return this.carsAmount() * 2 + currentVehicle.waitingSteps(currentStep);
        } else {
            return 0;
        }
    }

    public WorldDirection getLaneName() {
        return laneName;
    }


}
