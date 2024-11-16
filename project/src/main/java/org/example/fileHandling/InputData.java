package org.example.fileHandling;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

public class InputData {
    private List<Command> commands;

    public List<Command> getCommands() {
        return this.commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public String toString() {
        return "InputData{" +
                "commands=" + commands +
                '}';
    }
}


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AddVehicleCommand.class, name = "addVehicle"),
        @JsonSubTypes.Type(value = StepCommand.class, name = "step")

})
abstract class Command {
    private String type;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Command{" +
                "type='" + type + '\'' +
                '}';
    }
}

class AddVehicleCommand extends Command {
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
}

class StepCommand extends Command {

    @Override
    public String toString() {
        return "StepCommand{}";
    }
}