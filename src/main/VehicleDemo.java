package main;

public class VehicleDemo {
    public static void main(String[] args) {
        final int DEFAULT_DISTANCE = 450; // jarak tempuh kendaraan (max 500)
        final int DEFAULT_REFUEL_AMOUNT = 30; // Jumlah fuel yang akan diisi

        Vehicle[] vehicles = new Vehicle[] // Menambahkan instance
                {
                        new LandVehicle("Mobil", 120, 100, 4, 0), // mobil (land)
                        new WaterVehicle("Speed boat", 80, 30, true, 60), // speed boat (water)
                        new LandVehicle("Truk", 90, 50, 8, 0), // truk (land)
                        new LandVehicle("Motor", 90, 50, 2, 300), // Motor (land)
                        new AirVehicle("Pesawat baling", 300, 100, true, 0), // pesawat blaing (air)
                        new AirVehicle("Balon udara", 70, 50, false, 0) // Balon udara (air)
                };

        for (Vehicle i : vehicles){ // loop semua instance dalam array vehicles, i sebagai dummy variabel
            System.out.println("================================================================== " + i.getName());
            i.move(); // memanggil method move pada kendaraan yang di loop
            double consumed = i.calculateFuelConsumption(DEFAULT_DISTANCE); // kalkulasi fuel

            i.stop(DEFAULT_DISTANCE, consumed); // mengeluarkan pesan titik berhenti kendaraan
            System.out.println("Konsumsi bahan bakar diperlukan untuk " + DEFAULT_DISTANCE + " km: " + consumed + "% bahan bakar. Sisa: " + i.getFuelLevel() + "%");

            // Menghitung jarak tempuh kendaraan (menit)
            double finalDistance = i.isStopped() ? i.getStoppedAt() : DEFAULT_DISTANCE;  // cari kejauan akhir
            double kalkulasiWaktu = (finalDistance / (double) i.getSpeed()) * 60; // titik tempuh dibagi kecepatan dan kali 60
            String waktuTempuh = String.format("%.2f", kalkulasiWaktu); // Format menjadi 00.00

            System.out.println("Waktu yang dihabiskan adalah: " + waktuTempuh + " menit" );

            if (i instanceof Refuelable r){ // Jika class implement refuelable dan r sebagai dummy variable i (instance yang sedang diloop)
                if (r.isFuelLow()){ // jika bahan bakar rendah
                    System.out.println("bahan bakar " + i.getName() + " rendah, mengisi ulang...");
                    r.refuel(DEFAULT_REFUEL_AMOUNT); // panggil refuel sesuai dengan instance yang di loop
                }
            }
            System.out.println(); // spacing
        }
    }
}
