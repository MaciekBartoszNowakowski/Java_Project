package org.example.Logic;

public class Vehicle {

    private final WorldDirection initialDirection;
    private final WorldDirection destinationDirection;

    private final int arrivalStep;

    private final String vehicleId;

    public Vehicle(String initialDirection, String destinationDirection, int arrivalStep, String vehicleId) {

        switch (initialDirection) {
            case "north" -> this.initialDirection = WorldDirection.NORTH;
            case "east" -> this.initialDirection = WorldDirection.EAST;
            case "south" -> this.initialDirection = WorldDirection.SOUTH;
            case "west" -> this.initialDirection = WorldDirection.WEST;
            default -> throw new IllegalArgumentException("Invalid direction: " + initialDirection);
        }

        switch (destinationDirection) {
            case "north" -> this.destinationDirection = WorldDirection.NORTH;
            case "east" -> this.destinationDirection = WorldDirection.EAST;
            case "south" -> this.destinationDirection = WorldDirection.SOUTH;
            case "west" -> this.destinationDirection = WorldDirection.WEST;
            default -> throw new IllegalArgumentException("Invalid direction: " + initialDirection);
        }

        this.vehicleId=vehicleId;
        this.arrivalStep=arrivalStep;
    }

    public int waitingSteps(int currentStep){
        return currentStep-this.arrivalStep;
    }

    public WorldDirection getInitialDirection() {
        return initialDirection;
    }

    public WorldDirection getDestinationDirection() {
        return destinationDirection;
    }

    public String getVehicleId() {
        return vehicleId;
    }
}
