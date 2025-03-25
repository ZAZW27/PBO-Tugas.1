package main;

class AirVehicle extends Vehicle{
    private final boolean hasPropeller;

    // Construtor Airvehicle
    public AirVehicle(String name, int speed, double fuelLevel, boolean hasPropeller, int stoppedAt){
        super(name, speed, fuelLevel, stoppedAt, (stoppedAt >= 1)); // Mengisi variable dari constructor superclass
        this.hasPropeller = hasPropeller; // assign propeller
    }

    @Override // Override method move sesuai dengan subclass
    public void move(){
        System.out.println(getName() + " bergerak di udara dengan kecepatan " + getSpeed() + " km/jam " + (hasPropeller ? "dengan baling" : "tanpa baling"));
    }

    @Override // Override method kalkulasi penggunaan bahan bakar
    public double calculateFuelConsumption(double distance) {
        final double FUEL_CONSUMPTION_RATE = 5; // inisiasi banyaknya pembuangan bahan bakar

        double fuelConsumed = distance / FUEL_CONSUMPTION_RATE; // kalkulasi konsumsi bahan bakar
        setFuelLevel(getFuelLevel() - fuelConsumed); // mengubah variable fuelLevel pada superclass sesuai dengan kalkulasi

        if(getFuelLevel() <= 0){ // cek jika fuelLevel kurang atau sama dengan 0
            // Mencari titik pesawat habis bahan bakar
            double stoppedPosition = (distance * 100) / fuelConsumed; // destinasi sebenarnya dikali 100 dan dibagi bahan bakar terpakai
            setStoppedAt((int)stoppedPosition); // ubah stopped at menjadi hasil kalkulasi
            stop( (int) distance, fuelConsumed); // panggil method stop
        }

        return fuelConsumed; // kembalikan nilai fuelConsumed saat method dipanggil
    }

    @Override // Method yang dipanggil jika isStopped true atau AirVehicle habis bahan bakar
    public void stop(int distance, double consumed){
        System.out.println("!!! Emergency landing pada " + getStoppedAt() + " km, dari rute awal " + distance + " km !!!");
        System.out.println("dan konsumsi bahan bakar sebanyak: " + Math.max(0, Math.min(consumed, 100)) + "%, Sisa: " + getFuelLevel() + "%");
    }
}
