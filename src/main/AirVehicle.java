package main;

class AirVehicle extends Vehicle{
    private final boolean hasPropeller;
    private final double initialFuel; // digunakan untuk mengetahui level fuel awal kendaraan

    // Construtor Airvehicle
    public AirVehicle(String name, int speed, double fuelLevel, boolean hasPropeller, int stoppedAt){
        super(name, speed, fuelLevel, stoppedAt, (stoppedAt >= 1)); // Mengisi variable dari constructor superclass
        this.hasPropeller = hasPropeller; // menambah propeller pada constructor
        this.initialFuel = fuelLevel;
    }

    @Override // Override method move sesuai dengan subclass
    public void move(){
        System.out.println(getName() + " bergerak di udara dengan kecepatan " + getSpeed() + " km/jam " + (hasPropeller ? "dengan baling" : "tanpa baling"));
        System.out.println("Dengan bahan bakar sebanyak " + getFuelLevel() + "%\n-");
    }

    @Override // Override method kalkulasi penggunaan bahan bakar
    public double calculateFuelConsumption(double distance) {
        final double FUEL_CONSUMPTION_RATE = 5; // inisiasi banyaknya pembuangan bahan bakar
        double stoppedPosition = getFuelLevel() * FUEL_CONSUMPTION_RATE; // Digunakan jika fuel level kurang dari 1, kalkulasi seberapa jauh kendaraan berjalan

        double fuelConsumed = distance / FUEL_CONSUMPTION_RATE; // kalkulasi konsumsi bahan bakar
        setFuelLevel(getFuelLevel() - fuelConsumed); // mengubah variable fuelLevel pada superclass sesuai dengan kalkulasi

        if(getFuelLevel() < 1){ // cek jika fuelLevel kurang atau sama dengan 0
            setStoppedAt((int)stoppedPosition); // ubah stopped at menjadi hasil kalkulasi
            setIsStopped(true);
        }

        return fuelConsumed; // kembalikan nilai fuelConsumed saat method dipanggil
    }

    @Override // Method yang dipanggil jika isStopped true atau AirVehicle habis bahan bakar
    public void stop(int distance, double consumed){
        if (isStopped()){
            System.out.println("!!! Emergency landing pada " + getStoppedAt() + " km, dari rute awal " + distance + " km !!!");
            System.out.println("dan konsumsi bahan bakar sebanyak: " + initialFuel + "%, Sisa: " + getFuelLevel() + "%");
        }
        else{
            System.out.println(getName() + " Berhasil mendarat sesuai tujuan");
        }
    }
}
