package org.example.fileHandling;

import java.util.ArrayList;
import java.util.List;

public class SingleStep {
    private List<String> vehicles;

    public SingleStep() {
        this.vehicles = new ArrayList<>();
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public void addVehicle(String vehicleId) {
        this.vehicles.add(vehicleId);
    }

}
