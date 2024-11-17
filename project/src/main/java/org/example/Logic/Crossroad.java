package org.example.Logic;


import org.example.fileHandling.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Crossroad {
    private InputData input;

    private OutputData output;

    private Lane northLane = new Lane(WorldDirection.NORTH);
    private Lane westLane = new Lane(WorldDirection.WEST);
    private Lane southLane = new Lane(WorldDirection.SOUTH);
    private Lane eastLane = new Lane(WorldDirection.EAST);
    private ArrayList<Lane> lanes = new ArrayList<>(
            Arrays.asList(northLane, westLane, eastLane, southLane)
    );

    private int currentStep = 0;
    private final String outputFilePath;

    public Crossroad(InputData input, OutputData output, String outputFilePath) {
        this.input = input;
        this.output = output;
        this.outputFilePath = outputFilePath;
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

                for (Lane lane : this.lanes) {
                    if (lane.getLaneName() == currentDirection || lane.getLaneName().across() == currentDirection) {
                        lane.letLane();
                    } else {
                        lane.blockLane();
                    }
                }
                for (Lane lane : this.lanes) {
                    Vehicle goingVehicle = lane.getVehicle();
                    if (goingVehicle!=null){
                        step.addVehicle(goingVehicle.getVehicleId());
                    }
                }

                output.addStep(step);

            }

        }

        Writer.write(outputFilePath, output);


    }


}
