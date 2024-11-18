package org.example.Logic;


import org.example.fileHandling.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

public class Crossroad {
    private final InputData input;

    private OutputData output;

    private final Lane northLane = new Lane(WorldDirection.NORTH);
    private final Lane westLane = new Lane(WorldDirection.WEST);
    private final Lane southLane = new Lane(WorldDirection.SOUTH);
    private final Lane eastLane = new Lane(WorldDirection.EAST);

    private final Map<WorldDirection, Lane> laneMap = new HashMap<>();

    private int currentStep = 0;
    private final String outputFilePath;

    private final LightsDistributor lightsDistributor;

    public Crossroad(InputData input, OutputData output, String outputFilePath) {
        this.input = input;
        this.output = output;
        this.outputFilePath = outputFilePath;

        laneMap.put(WorldDirection.NORTH, northLane);
        laneMap.put(WorldDirection.WEST, westLane);
        laneMap.put(WorldDirection.SOUTH, southLane);
        laneMap.put(WorldDirection.EAST, eastLane);
        this.lightsDistributor = new LightsDistributor(this.laneMap);
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
                this.lightsDistributor.setupLights(currentStep);

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
