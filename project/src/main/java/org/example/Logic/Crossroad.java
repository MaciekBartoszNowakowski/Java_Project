package org.example.Logic;


import org.example.fileHandling.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

public class Crossroad {
    private InputData input;

    private OutputData output;

    private Lane northLane = new Lane(WorldDirection.NORTH);
    private Lane westLane = new Lane(WorldDirection.WEST);
    private Lane southLane = new Lane(WorldDirection.SOUTH);
    private Lane eastLane = new Lane(WorldDirection.EAST);

    private Map<WorldDirection, Lane> laneMap = new HashMap<>();

    private int currentStep = 0;
    private final String outputFilePath;

    public Crossroad(InputData input, OutputData output, String outputFilePath) {
        this.input = input;
        this.output = output;
        this.outputFilePath = outputFilePath;

        laneMap.put(WorldDirection.NORTH, northLane);
        laneMap.put(WorldDirection.WEST, westLane);
        laneMap.put(WorldDirection.SOUTH, southLane);
        laneMap.put(WorldDirection.EAST, eastLane);

    }

    public void simulate() {

        while (input.remaining()) {
            Command currentCommand = input.getCurrentCommand();
            if (currentCommand instanceof AddVehicleCommand addVehicleCommand) {
                switch (addVehicleCommand.getStartRoad()) {
                    case "north" -> northLane.addVehicle(new Vehicle(addVehicleCommand, currentStep));
                    case "south" -> southLane.addVehicle(new Vehicle(addVehicleCommand, currentStep));
                    case "east" -> eastLane.addVehicle(new Vehicle(addVehicleCommand, currentStep));
                    case "west" -> westLane.addVehicle(new Vehicle(addVehicleCommand, currentStep));
                }
            } else if (currentCommand instanceof StepCommand stepCommand) {
                SingleStep step = new SingleStep();
                currentStep += 1;
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
                    importance = importantLane.importance(currentStep);
                }

                WorldDirection currentDirection = importantLane.getLaneName();
                if (importantLane.lookUpVehicle()!= null){
                    WorldDirection destinationDirection = importantLane.lookUpVehicle().getDestinationDirection();
                    for (Lane lane : this.laneMap.values()) {
                        lane.blockLane();
                    }
                    if (currentDirection.left() == destinationDirection) {
                        importantLane.letLane();
                        this.laneMap.get(currentDirection.left()).conditionalLight();
                    } else if (currentDirection.across() == destinationDirection) {
                        importantLane.letLane();

                        WorldDirection oppositeDirection = currentDirection.across();
                        WorldDirection leftDirection = currentDirection.left();
                        Vehicle oppositeVehicle = this.laneMap.get(oppositeDirection).lookUpVehicle();
                        Vehicle leftVehicle = this.laneMap.get(leftDirection).lookUpVehicle();
                        if (oppositeVehicle == null) {
                            this.laneMap.get(leftDirection).conditionalLight();
                        }
                        else if (leftVehicle == null) {
                            if (oppositeVehicle.getDestinationDirection() != currentDirection.right()) {
                                this.laneMap.get(oppositeDirection).letLane();
                            }
                        } else {
                            if (oppositeVehicle.getDestinationDirection() == currentDirection) {
                                if (leftVehicle.getDestinationDirection() == currentDirection.right()) {
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
                    else {
                        importantLane.conditionalLight();


                        WorldDirection oppositeDirection = currentDirection.across();
                        WorldDirection leftDirection = currentDirection.left();
                        WorldDirection rightDirection = currentDirection.right();
                        Vehicle oppositeVehicle = this.laneMap.get(oppositeDirection).lookUpVehicle();
                        Vehicle leftVehicle = this.laneMap.get(leftDirection).lookUpVehicle();
    //                    Vehicle rightVehicle = this.laneMap.get(rightDirection).lookUpVehicle();

    //                    if (rightVehicle == null || rightVehicle.getDestinationDirection()==oppositeDirection){
                        this.laneMap.get(rightDirection).conditionalLight();

                        if (oppositeVehicle == null) {
                            this.laneMap.get(leftDirection).conditionalLight();
                        }
                        else if (leftVehicle == null) {
                            if (oppositeVehicle.getDestinationDirection() != currentDirection.right()) {
                                this.laneMap.get(oppositeDirection).letLane();
                            }
                        } else {
                            if (oppositeVehicle.getDestinationDirection() == currentDirection) {
                                if (leftVehicle.getDestinationDirection() == currentDirection.right()) {
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
                }

//                obsluga
//                for (Lane lane : this.lanes) {
//                    if (lane.getLaneName() == currentDirection || lane.getLaneName().across() == currentDirection) {
//                        lane.letLane();
//                    } else {
//                        lane.blockLane();
//                    }
//                }
                for (Lane lane : this.laneMap.values()) {
                    Vehicle goingVehicle = lane.getVehicle();
                    if (goingVehicle != null) {
                        step.addVehicle(goingVehicle.getVehicleId());
                    }
                }


                output.addStep(step);

            }

        }

        Writer.write(outputFilePath, output);


    }


}
