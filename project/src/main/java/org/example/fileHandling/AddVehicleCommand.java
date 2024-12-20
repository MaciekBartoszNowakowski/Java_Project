package org.example.fileHandling;

import java.util.Objects;

public class AddVehicleCommand extends Command {
    private String vehicleId;
    private String startRoad;
    private String endRoad;

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setStartRoad(String startRoad) {
        this.startRoad = startRoad;
    }

    public void setEndRoad(String endRoad) {
        this.endRoad = endRoad;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getStartRoad() {
        return startRoad;
    }

    public String getEndRoad() {
        return endRoad;
    }

    @Override
    public String toString() {
        return "AddVehicleCommand{" +
                "vehicleId='" + vehicleId + '\'' +
                ", startRoad='" + startRoad + '\'' +
                ", endRoad='" + endRoad + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddVehicleCommand that = (AddVehicleCommand) o;
        return Objects.equals(vehicleId, that.vehicleId) && Objects.equals(startRoad, that.startRoad) && Objects.equals(endRoad, that.endRoad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleId, startRoad, endRoad);
    }
}
