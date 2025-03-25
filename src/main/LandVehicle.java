package main;

class LandVehicle extends Vehicle implements Refuelable {
    private final int wheels; // roda kendaraan darat

    // Constructor
    public LandVehicle(String name, int speed, double fuelLevel, int wheels, int stoppedAt) {
        super(name, speed, fuelLevel, stoppedAt, (stoppedAt >= 1)); // call parent's class constructor
        this.wheels = wheels;
    }

    @Override
    public void move(){
        System.out.println(getName() + " Bergerak di darat dengan " + wheels + " roda pada kecepatan " + getSpeed() + " km/jam.");
    }

    @Override
    public double calculateFuelConsumption(double distance){
        final double FUEL_CONSUMPTION_RATE = 10;

        // Jika kendaraan berhenti maka jarak akan dikurangi titik berhenti. selainnya distance akan tetap
        final double finalDistance = this.isStopped ? this.stoppedAt : distance;
        double fuelConsumed = finalDistance / FUEL_CONSUMPTION_RATE;
        setFuelLevel(getFuelLevel() - fuelConsumed);

        return fuelConsumed;
    }

    @Override
    public void stop(int distance, double consumed){
        System.out.println("!!! Emergency stop pada " + getStoppedAt() + " km, dari " + distance + " km, dan konsumsi bahan bakar sebanyak: " + consumed + "%, Sisa: " + getFuelLevel() + "% !!!");
    }

    @Override
    public void refuel(double amount){
        setFuelLevel(getFuelLevel() + amount); // nilai fuel level ditambah dengan amount
        System.out.println( getName() + " diisi bahan bakar, level sekarang bertambah menjadi: " + getFuelLevel() + "%");
    }

    @Override
    public boolean isFuelLow(){
        return getFuelLevel() < 20;
    }
}
