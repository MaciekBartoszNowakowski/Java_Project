package org.example.fileHandling;

import java.util.ArrayList;
import java.util.List;

public class OutputData {
    private List<SingleStep> steps;

    public OutputData(){
        this.steps = new ArrayList<>();
    }

    public List<SingleStep> getSteps() {
        return steps;
    }

    public void addStep(SingleStep singleStep){
        this.steps.add(singleStep);
    }

}

