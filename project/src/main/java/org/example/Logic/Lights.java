package org.example.Logic;

public enum Lights {
    RED,
    GREEN;

    public Lights let(){
        return GREEN;
    }

    public Lights block(){
        return RED;

    }

}
