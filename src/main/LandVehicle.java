package main;

class LandVehicle extends Vehicle implements Refuelable {
    private final int wheels; // roda kendaraan darat

    // Constructor
    public LandVehicle(String name, int speed, double fuelLevel, int wheels, int stoppedAt) {
        super(name, speed, fuelLevel, stoppedAt, (stoppedAt >= 1)); // Mengisi variable dari constructor superclass
        this.wheels = wheels; // Menambah wheels pada constructor
    }

    @Override // Override method move sesuai dengan subclass
    public void move(){
        System.out.println(getName() + " Bergerak di darat dengan " + wheels + " roda pada kecepatan " + getSpeed() + " km/jam.");
        System.out.println("Dengan bahan bakar sebanyak " + getFuelLevel() + "%\n-");
    }

    @Override // Override method kalkulasi penggunaan bahan bakar
    public double calculateFuelConsumption(double distance){
        final double FUEL_CONSUMPTION_RATE = 10; // inisiasi banyaknya pembuangan bahan bakar

        // Jika kendaraan berhenti maka destinasi akhirnya berubah menjadi titik berhentinya. selainnya distance akan tetap dari titik awal
        final double finalDistance = this.isStopped ? this.stoppedAt : distance;
        double fuelConsumed = finalDistance / FUEL_CONSUMPTION_RATE; // kalkulasi penghabisan bahan bakar
        setFuelLevel(getFuelLevel() - fuelConsumed); // mengubah variable fuelLevel pada superclass sesuai dengan kalkulasi

        return fuelConsumed; // kembalikan nilai fuelConsumed saat method dipanggil
    }

    @Override // method digunakan jika input dari argumen memiliki nilai lebih dari 0
    public void stop(int distance, double consumed){
        if (isStopped()){
            System.out.println("!!! Emergency stop pada " + getStoppedAt() + " km, dari " + distance + " km, dan konsumsi bahan bakar sebanyak: " + consumed + "%, Sisa: " + getFuelLevel() + "% !!!");
        }
        else{
            System.out.println(getName() + " berhasil berhenti tepat pada tujuan");
        }
    }

    @Override // method digunakan untuk mengisi ulang bahan bakar
    public void refuel(double amount){ // amount banyaknya bahann bakar yang akan diisi
        setFuelLevel(getFuelLevel() + amount); // nilai fuel level ditambah dengan amount
        System.out.println( getName() + " diisi bahan bakar, level sekarang bertambah menjadi: " + getFuelLevel() + "%");
    }

    @Override // digunakna untuk cek jika nilai bahan bakar kurang dari 20
    public boolean isFuelLow(){
        return getFuelLevel() < 20;
    }
}
