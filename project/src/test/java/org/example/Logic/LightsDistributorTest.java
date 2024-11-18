package org.example.Logic;

import org.example.fileHandling.AddVehicleCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

public class LightsDistributorTest {

    @Test
    void compareLeftAndOppositeLanes_LeftPriorityTest(){
        Map<WorldDirection, Lane> laneMap = new HashMap<>();
        laneMap.put(WorldDirection.NORTH, new Lane(WorldDirection.NORTH));
        laneMap.put(WorldDirection.WEST, new Lane(WorldDirection.WEST));
        laneMap.put(WorldDirection.SOUTH, new Lane(WorldDirection.SOUTH));
        laneMap.put(WorldDirection.EAST, new Lane(WorldDirection.EAST));

        LightsDistributor lightsDistributor= new LightsDistributor(laneMap);

        AddVehicleCommand command2 = new AddVehicleCommand();
        AddVehicleCommand command3 = new AddVehicleCommand();

        command2.setVehicleId("vehicle2");
        command2.setStartRoad("north");
        command2.setEndRoad("west");
        Vehicle v2 = new Vehicle(command2, 1);
        laneMap.get(WorldDirection.NORTH).addVehicle(v2);

        command3.setVehicleId("vehicle3");
        command3.setStartRoad("east");
        command3.setEndRoad("west");
        Vehicle v3 = new Vehicle(command3, 2);
        laneMap.get(WorldDirection.EAST).addVehicle(v3);

        lightsDistributor.compareLeftAndOppositeLanes(WorldDirection.WEST,3);
        assertEquals(Lights.RIGHT, laneMap.get(WorldDirection.NORTH).getCurrentLight());
        assertEquals(Lights.RED, laneMap.get(WorldDirection.EAST).getCurrentLight());



    }

    @Test
    void compareLeftAndOppositeLanes_AcrossPriorityTest(){
        Map<WorldDirection, Lane> laneMap = new HashMap<>();
        laneMap.put(WorldDirection.NORTH, new Lane(WorldDirection.NORTH));
        laneMap.put(WorldDirection.WEST, new Lane(WorldDirection.WEST));
        laneMap.put(WorldDirection.SOUTH, new Lane(WorldDirection.SOUTH));
        laneMap.put(WorldDirection.EAST, new Lane(WorldDirection.EAST));

        LightsDistributor lightsDistributor= new LightsDistributor(laneMap);

        AddVehicleCommand command2 = new AddVehicleCommand();
        AddVehicleCommand command3 = new AddVehicleCommand();

        command2.setVehicleId("vehicle2");
        command2.setStartRoad("north");
        command2.setEndRoad("west");
        Vehicle v2 = new Vehicle(command2, 2);
        laneMap.get(WorldDirection.NORTH).addVehicle(v2);

        command3.setVehicleId("vehicle3");
        command3.setStartRoad("east");
        command3.setEndRoad("west");
        Vehicle v3 = new Vehicle(command3, 1);
        laneMap.get(WorldDirection.EAST).addVehicle(v3);

        lightsDistributor.compareLeftAndOppositeLanes(WorldDirection.WEST,3);
        assertEquals(Lights.RED, laneMap.get(WorldDirection.NORTH).getCurrentLight());
        assertEquals(Lights.GREEN, laneMap.get(WorldDirection.EAST).getCurrentLight());
    }

    @Test
    void compareLeftAndOppositeLanes_BothRightAccessTest(){
        Map<WorldDirection, Lane> laneMap = new HashMap<>();
        laneMap.put(WorldDirection.NORTH, new Lane(WorldDirection.NORTH));
        laneMap.put(WorldDirection.WEST, new Lane(WorldDirection.WEST));
        laneMap.put(WorldDirection.SOUTH, new Lane(WorldDirection.SOUTH));
        laneMap.put(WorldDirection.EAST, new Lane(WorldDirection.EAST));

        LightsDistributor lightsDistributor= new LightsDistributor(laneMap);

        AddVehicleCommand command2 = new AddVehicleCommand();
        AddVehicleCommand command3 = new AddVehicleCommand();

        command2.setVehicleId("vehicle2");
        command2.setStartRoad("north");
        command2.setEndRoad("south");
        Vehicle v2 = new Vehicle(command2, 2);
        laneMap.get(WorldDirection.NORTH).addVehicle(v2);

        command3.setVehicleId("vehicle3");
        command3.setStartRoad("east");
        command3.setEndRoad("south");
        Vehicle v3 = new Vehicle(command3, 1);
        laneMap.get(WorldDirection.EAST).addVehicle(v3);

        lightsDistributor.compareLeftAndOppositeLanes(WorldDirection.WEST,3);
        assertEquals(Lights.RIGHT, laneMap.get(WorldDirection.NORTH).getCurrentLight());
        assertEquals(Lights.RIGHT, laneMap.get(WorldDirection.EAST).getCurrentLight());
    }

    @Test
    void setupLights_AllRight_Test(){
        Map<WorldDirection, Lane> laneMap = new HashMap<>();
        laneMap.put(WorldDirection.NORTH, new Lane(WorldDirection.NORTH));
        laneMap.put(WorldDirection.WEST, new Lane(WorldDirection.WEST));
        laneMap.put(WorldDirection.SOUTH, new Lane(WorldDirection.SOUTH));
        laneMap.put(WorldDirection.EAST, new Lane(WorldDirection.EAST));

        LightsDistributor lightsDistributor= new LightsDistributor(laneMap);

        AddVehicleCommand command1 = new AddVehicleCommand();
        AddVehicleCommand command2 = new AddVehicleCommand();
        AddVehicleCommand command3 = new AddVehicleCommand();
        AddVehicleCommand command4 = new AddVehicleCommand();
        command1.setVehicleId("vehicle1");
        command1.setStartRoad("south");
        command1.setEndRoad("east");
        Vehicle v1 = new Vehicle(command1, 0);
        laneMap.get(WorldDirection.SOUTH).addVehicle(v1);

        command2.setVehicleId("vehicle2");
        command2.setStartRoad("east");
        command2.setEndRoad("north");
        Vehicle v2 = new Vehicle(command2, 1);
        laneMap.get(WorldDirection.EAST).addVehicle(v2);

        command3.setVehicleId("vehicle3");
        command3.setStartRoad("north");
        command3.setEndRoad("west");
        Vehicle v3 = new Vehicle(command3, 2);
        laneMap.get(WorldDirection.NORTH).addVehicle(v3);

        command4.setVehicleId("vehicle4");
        command4.setStartRoad("west");
        command4.setEndRoad("south");
        Vehicle v4 = new Vehicle(command4, 3);
        laneMap.get(WorldDirection.WEST).addVehicle(v4);
        lightsDistributor.setupLights(4);

        assertEquals(Lights.RIGHT, laneMap.get(WorldDirection.NORTH).getCurrentLight());
        assertEquals(Lights.RIGHT, laneMap.get(WorldDirection.EAST).getCurrentLight());
        assertEquals(Lights.RIGHT, laneMap.get(WorldDirection.SOUTH).getCurrentLight());
        assertEquals(Lights.RIGHT, laneMap.get(WorldDirection.WEST).getCurrentLight());


    }


}
