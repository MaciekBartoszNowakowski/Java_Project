package org.example.fileHandling;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReaderTest {

    @Test
    public void readTest() {
        InputData input = Reader.read("testInput.json");
        assertEquals("InputData{commands=[AddVehicleCommand{vehicleId='vehicle1', startRoad='south', endRoad='north'}, AddVehicleCommand{vehicleId='vehicle2', startRoad='north', endRoad='south'}, StepCommand{}]}",input.toString());
    }
}
