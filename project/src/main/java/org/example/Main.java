package org.example;


import org.example.Logic.Crossroad;
import org.example.fileHandling.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
//        String inputFilePath= args[0];
//        String outputFilePath = args[1];
        String inputFilePath= "exampleData.json";
        String outputFilePath = "exampleOutput.json";

        InputData input = Reader.read(inputFilePath);
        OutputData output = new OutputData();
        System.out.println(input.toString());

        Crossroad crossroad = new Crossroad(input,output,outputFilePath);
        crossroad.simulate();


        //        OutputData output = new OutputData();
//
//
//        SingleStep step1 = new SingleStep();
//        step1.addVehicle("vehicle1");
//        step1.addVehicle("vehicle2");
//
//        SingleStep step2 = new SingleStep();
//        step2.addVehicle("vehicle3");
//
//        output.addStep(step1);
//        output.addStep(step2);
//        Writer.write(outputFilePath,output);

    }
}