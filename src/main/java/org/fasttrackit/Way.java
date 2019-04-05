package org.fasttrackit;

public class Way {
    private String name;
    private int length;

    Way(String name, int length)
    {
        this.name=name;
        this.length=length;
    }

    String getName()
    {
        return name;
    }

    public double getLength() {
        return length;
    }
}

