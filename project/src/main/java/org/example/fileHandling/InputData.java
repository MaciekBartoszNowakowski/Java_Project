package org.example.fileHandling;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;
import java.util.Objects;

public class InputData {
    private List<Command> commands;

//    public List<Command> getCommands() {
//        return this.commands;
//    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    public Command getCurrentCommand(){
        return commands.remove(0);
    }

    public boolean remaining(){
        return !commands.isEmpty();
    }

    @Override
    public String toString() {
        return "InputData{" +
                "commands=" + commands +
                '}';
    }
}


