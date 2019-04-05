package org.fasttrackit;

public class Vehicle {

    private String name;
    private double mileage;
    private double fuellLevel;
    public static int vehicleCount = 0;
    private double totalTraveledDistance;

    Vehicle() {
        vehicleCount++;
    }

    protected double accelerate(double variationSpeed) {
        int duration=1;
        System.out.println(name + " accelerated with " + variationSpeed + " km/h for " + duration + " hour.");
        double traveledDistance = variationSpeed * duration;
        System.out.println("Traveled distance: " + traveledDistance + " meters.");
        totalTraveledDistance += traveledDistance;
        System.out.println("Total travelled distance: " + totalTraveledDistance + " meters");
        double spentFuel = traveledDistance * mileage / 100;
        fuellLevel -= spentFuel;
        System.out.println("Remaining fuel: " + fuellLevel);
        return traveledDistance;
    }

    public double getTotalTraveledDistance() {
        return totalTraveledDistance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public void setFuellLevel(double fuellLevel) {
        this.fuellLevel = fuellLevel;
    }

    public double getFuellLevel() {
        return fuellLevel;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", mileage=" + mileage +
                '}';
    }


}

