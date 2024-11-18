package org.example.Logic;

import org.example.fileHandling.AddVehicleCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LaneTest {

    @Test
    void carsAmountTest() {
        Lane lane = new Lane(WorldDirection.NORTH);
        AddVehicleCommand command1 = new AddVehicleCommand();
        AddVehicleCommand command2 = new AddVehicleCommand();
        command1.setVehicleId("vehicle1");
        command1.setStartRoad("east");
        command1.setEndRoad("north");
        Vehicle v1 = new Vehicle(command1, 0);

        command2.setVehicleId("vehicle2");
        command2.setStartRoad("north");
        command2.setEndRoad("west");
        Vehicle v2 = new Vehicle(command2, 0);


        assertEquals(0, lane.carsAmount());
        lane.addVehicle(v1);
        assertEquals(1, lane.carsAmount());
        lane.addVehicle(v2);
        assertEquals(2, lane.carsAmount());
    }
    @Test
    void importanceTest(){
        Lane lane = new Lane(WorldDirection.NORTH);
        AddVehicleCommand command1 = new AddVehicleCommand();
        AddVehicleCommand command2 = new AddVehicleCommand();
        AddVehicleCommand command3 = new AddVehicleCommand();
        command1.setVehicleId("vehicle1");
        command1.setStartRoad("east");
        command1.setEndRoad("north");
        Vehicle v1 = new Vehicle(command1, 3);

        command2.setVehicleId("vehicle2");
        command2.setStartRoad("north");
        command2.setEndRoad("west");
        Vehicle v2 = new Vehicle(command2, 4);

        command3.setVehicleId("vehicle3");
        command3.setStartRoad("west");
        command3.setEndRoad("south");
        Vehicle v3 = new Vehicle(command3, 5);

        assertEquals(0, lane.importance(4));
        lane.addVehicle(v1);
        lane.addVehicle(v2);
        assertEquals(5, lane.importance(4));
        assertEquals(9, lane.importance(8));
        lane.addVehicle(v3);
        assertEquals(11, lane.importance(8));


    }

    @Test
    void getVehicleTest(){
        Lane lane = new Lane(WorldDirection.NORTH);
        AddVehicleCommand command1 = new AddVehicleCommand();
        AddVehicleCommand command2 = new AddVehicleCommand();
        AddVehicleCommand command3 = new AddVehicleCommand();
        command1.setVehicleId("vehicle1");
        command1.setStartRoad("north");
        command1.setEndRoad("south");
        Vehicle v1 = new Vehicle(command1, 3);

        command2.setVehicleId("vehicle2");
        command2.setStartRoad("north");
        command2.setEndRoad("east");
        Vehicle v2 = new Vehicle(command2, 4);

        command3.setVehicleId("vehicle3");
        command3.setStartRoad("north");
        command3.setEndRoad("west");
        Vehicle v3 = new Vehicle(command3, 0);

        assertNull(lane.getVehicle());
        lane.addVehicle(v1);
        assertNull(lane.getVehicle());
        lane.letLane();
        assertEquals(v1,lane.getVehicle());
        assertNull(lane.getVehicle());
        lane.addVehicle(v2);
        lane.conditionalLight();
        assertNull(lane.getVehicle());
        lane.addVehicle(v3);
        lane.letLane();
        lane.getVehicle();
        lane.conditionalLight();
        assertEquals(v3,lane.getVehicle());


    }


}






























