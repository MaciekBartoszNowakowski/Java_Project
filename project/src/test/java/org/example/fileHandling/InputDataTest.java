package org.example.fileHandling;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class InputDataTest {

    @Test
    public void getCurrentCommandTest(){
        InputData input = Reader.read("testInput.json");
        AddVehicleCommand command =new AddVehicleCommand();
        command.setEndRoad("north");
        command.setStartRoad("south");
        command.setVehicleId("vehicle1");

        AddVehicleCommand command2 =new AddVehicleCommand();
        command2.setEndRoad("south");
        command2.setStartRoad("north");
        command2.setVehicleId("vehicle2");

        StepCommand command3 = new StepCommand();
// tests checking if correct commands are being read from getCurrentComand
        assertEquals(command,input.getCurrentCommand());
        assertEquals(command2,input.getCurrentCommand());
        assertEquals(command3.getClass(),input.getCurrentCommand().getClass());

//        Checking if there is not an error in ways comparing command classes.
        InputData input2 = Reader.read("testInput.json");
        assertNotEquals(command2,input2.getCurrentCommand());
        assertNotEquals(command3.getClass(),input2.getCurrentCommand());



    }
}
