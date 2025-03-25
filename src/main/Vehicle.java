package main;

abstract class Vehicle
{
    // initialize variables
    private final String name; // nama kendaraan
    private final int speed; // kelajuan kendaraan
    private double fuelLevel; // bahan bakar awal
    protected boolean isStopped; // apakah berhenti 
    protected int stoppedAt; // titik berhenti

    // Constructor
    public Vehicle(String name, int speed, double fuelLevel, int stoppedAt, boolean isStopped){
        this.name = name;
        this.speed = speed;
        this.fuelLevel = fuelLevel;
        this.stoppedAt = stoppedAt;
        this.isStopped = isStopped;
    }

    // Getter methods
    public String getName(){
        return name;
    }
    public int getSpeed(){
        return speed;
    }
    public double getFuelLevel(){
        return fuelLevel;
    }
    public int getStoppedAt(){
        return stoppedAt;
    }
    public boolean isStopped(){
        return isStopped;
    }

    // Set fuel level from 0-100
    protected void setFuelLevel(double fuelLevel){
        this.fuelLevel = Math.max(0, Math.min(fuelLevel, 100)); // pastika tidak lebih dari 100 atau kurang dari 0
    }
    // mengubah variable superclass stoppedAt
    protected void setStoppedAt(int stoppedAt){
        this.stoppedAt = stoppedAt;
    }

    // abstract methods
    public abstract void move();
    public abstract double calculateFuelConsumption(double distance);
    public abstract void stop(int distance, double consumed);
}