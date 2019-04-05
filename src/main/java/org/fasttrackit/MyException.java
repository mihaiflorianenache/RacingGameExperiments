package org.fasttrackit;

public class MyException extends Exception{
    String rule;
    MyException(String rule)
    {
        this.rule=rule;
    }

    public String toString()
    {
        return rule;
    }
}