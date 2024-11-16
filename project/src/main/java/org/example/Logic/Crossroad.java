package org.example.Logic;


import org.example.fileHandling.InputData;
import org.example.fileHandling.OutputData;

import java.util.LinkedList;
import java.util.List;

public class Crossroad {
    private InputData input;

    private OutputData output;

    private Lane northLane= new Lane("north");
    private Lane westLane= new Lane("west");
    private Lane southLane= new Lane("south");
    private Lane eastLane = new Lane("east");

    public Crossroad(InputData input, OutputData output){
        this.input = input;
        this.output = output;
    }

    public void simulate(){

//        while (input.remaining()){
//            Command currentCommand = input.getCurrentCommand();
//        }


    }






}
