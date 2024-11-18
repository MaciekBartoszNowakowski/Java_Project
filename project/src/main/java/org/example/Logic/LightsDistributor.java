package org.example.Logic;

import java.util.HashMap;
import java.util.Map;

public class LightsDistributor {
    private final Lane northLane;
    private final Lane westLane;
    private final Lane southLane;
    private final Lane eastLane;

    private final Map<WorldDirection, Lane> laneMap;

    public LightsDistributor(Map<WorldDirection, Lane> laneMap) {
        this.laneMap = laneMap;
        northLane = laneMap.get(WorldDirection.NORTH);
        westLane = laneMap.get(WorldDirection.WEST);
        southLane = laneMap.get(WorldDirection.SOUTH);
        eastLane = laneMap.get(WorldDirection.EAST);
    }

    protected void compareLeftAndOppositeLanes(WorldDirection currentDirection, int currentStep) {
        WorldDirection oppositeDirection = currentDirection.across();
        WorldDirection leftDirection = currentDirection.left();
        Vehicle oppositeVehicle = this.laneMap.get(oppositeDirection).lookUpVehicle();
        Vehicle leftVehicle = this.laneMap.get(leftDirection).lookUpVehicle();
        if (oppositeVehicle == null) {
            this.laneMap.get(leftDirection).conditionalLight();
        } else if (leftVehicle == null) {
            if (oppositeVehicle.getDestinationDirection() != currentDirection.right()) {
                this.laneMap.get(oppositeDirection).letLane();
            }
        } else {
            if (oppositeVehicle.getDestinationDirection() == currentDirection) {
                if (leftVehicle.getDestinationDirection() == currentDirection) {
                    if (this.laneMap.get(leftDirection).importance(currentStep) > this.laneMap.get(oppositeDirection).importance(currentStep)) {
                        this.laneMap.get(leftDirection).conditionalLight();
                    } else {
                        this.laneMap.get(oppositeDirection).letLane();
                    }

                } else {
                    this.laneMap.get(oppositeDirection).letLane();
                }
            } else {
                this.laneMap.get(leftDirection).conditionalLight();
                this.laneMap.get(oppositeDirection).conditionalLight();
            }
        }

    }


    public void setupLights(int currentStep) {
        Lane importantLane = this.northLane;
        int importance = importantLane.importance(currentStep);

        if (importance < this.westLane.importance(currentStep)) {
            importantLane = this.westLane;
            importance = importantLane.importance(currentStep);
        }

        if (importance < this.southLane.importance(currentStep)) {
            importantLane = this.southLane;
            importance = importantLane.importance(currentStep);
        }

        if (importance < this.eastLane.importance(currentStep)) {
            importantLane = this.eastLane;
        }

        WorldDirection currentDirection = importantLane.getLaneName();

        for (Lane lane : this.laneMap.values()) {
            lane.blockLane();
        }
        if (importantLane.lookUpVehicle() != null) {
            WorldDirection destinationDirection = importantLane.lookUpVehicle().getDestinationDirection();
            if (currentDirection.left() == destinationDirection) {
                importantLane.letLane();
                this.laneMap.get(currentDirection.left()).conditionalLight();
            } else if (currentDirection.across() == destinationDirection) {
                importantLane.letLane();
                this.compareLeftAndOppositeLanes(currentDirection, currentStep);
            } else {
                importantLane.conditionalLight();
                WorldDirection rightDirection = currentDirection.right();
                Vehicle rightVehicle = laneMap.get(rightDirection).lookUpVehicle();
                if (rightVehicle != null && rightVehicle.getDestinationDirection() == currentDirection.left()) {
                    laneMap.get(rightDirection).letLane();
                    laneMap.get(currentDirection.left()).conditionalLight();
                } else {
                    this.laneMap.get(rightDirection).conditionalLight();
                    this.compareLeftAndOppositeLanes(currentDirection, currentStep);
                }

            }
        }
    }


}
