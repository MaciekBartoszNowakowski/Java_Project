package org.example;


import org.example.Logic.Crossroad;
import org.example.fileHandling.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String inputFilePath = args[0];
        String outputFilePath = args[1];

        InputData input = Reader.read(inputFilePath);
        OutputData output = new OutputData();

        Crossroad crossroad = new Crossroad(input, output, outputFilePath);
        crossroad.simulate();


    }
}