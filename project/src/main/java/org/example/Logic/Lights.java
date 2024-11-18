package org.example.Logic;

public enum Lights {
    RED,
    GREEN,
    RIGHT;

    public Lights let() {
        return GREEN;
    }

    public Lights block() {
        return RED;
    }

    public Lights turnRight() {
        return RIGHT;
    }

}
