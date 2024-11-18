package org.example.Logic;
import org.example.fileHandling.AddVehicleCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleTest {


    @Test
    void testVehicleCreationWithData() {
        AddVehicleCommand command1 = new AddVehicleCommand();
        AddVehicleCommand command2 = new AddVehicleCommand();
        AddVehicleCommand command3 = new AddVehicleCommand();
        AddVehicleCommand command4 = new AddVehicleCommand();
        command1.setVehicleId("vehicle1");
        command1.setStartRoad("east");
        command1.setEndRoad("north");
        Vehicle v1 = new Vehicle(command1, 0);

        command2.setVehicleId("vehicle2");
        command2.setStartRoad("north");
        command2.setEndRoad("west");
        Vehicle v2 = new Vehicle(command2, 0);

        command3.setVehicleId("vehicle3");
        command3.setStartRoad("west");
        command3.setEndRoad("south");
        Vehicle v3 = new Vehicle(command3, 0);

        command4.setVehicleId("vehicle4");
        command4.setStartRoad("south");
        command4.setEndRoad("east");
        Vehicle v4 = new Vehicle(command4, 0);

        assertEquals("vehicle1", v1.getVehicleId());
        assertEquals(WorldDirection.EAST, v1.getInitialDirection());
        assertEquals(WorldDirection.NORTH, v1.getDestinationDirection());


        assertEquals(WorldDirection.NORTH, v2.getInitialDirection());
        assertEquals(WorldDirection.WEST, v2.getDestinationDirection());

        assertEquals(WorldDirection.WEST, v3.getInitialDirection());
        assertEquals(WorldDirection.SOUTH, v3.getDestinationDirection());

        assertEquals(WorldDirection.SOUTH, v4.getInitialDirection());
        assertEquals(WorldDirection.EAST, v4.getDestinationDirection());
    }

    @Test
    void testWaitingSteps(){
        AddVehicleCommand command = new AddVehicleCommand();
        command.setVehicleId("vehicle1");
        command.setStartRoad("east");
        command.setEndRoad("north");

        Vehicle v1 = new Vehicle(command, 5);
        assertEquals(0,v1.waitingSteps(5));
        assertEquals(42,v1.waitingSteps(47));
    }

}
