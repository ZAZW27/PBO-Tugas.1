package main;

public class VehicleDemo {
    public static void main(String[] args) {
        final int DEFAULT_DISTANCE = 700; // jarak tempuh kendaraan (max 500)
        final int DEFAULT_REFUEL_AMOUNT = 30; // Jumlah fuel yang akan diisi

        Vehicle[] vehicles = new Vehicle[] // Menambahkan instance
                {
                        new LandVehicle("Mobil", 120, 100, 4, 0), // mobil (land)
                        new WaterVehicle("Speed boat", 80, 30, true, 60), // speed boat (water)
                        new LandVehicle("Truk", 90, 80, 8, 640), // truk (land)
                        new AirVehicle("Pesawat baling", 300, 100, true, 0) // pesawat blaing (air)
                };

        for (Vehicle i : vehicles){
            System.out.println("==================================================================");
            i.move(); // memanggil method move pada kendaraan yang di loop
            double consumed = i.calculateFuelConsumption(DEFAULT_DISTANCE);

            if (i.isStopped()){ // Jika kendaraan ada titik berhenti
                i.stop(DEFAULT_DISTANCE, consumed);
            }
            else{
                System.out.println("Konsumsi bahan bakar untuk " + DEFAULT_DISTANCE + " km: " + consumed + "% bahan bakar. Sisa: " + i.getFuelLevel() + "%");
            }

            // Menghitung jarak tempuh kendaraan (menit)
            double finalDistance = i.isStopped() ? i.getStoppedAt() : DEFAULT_DISTANCE;  // cari kejauan akhir
            double kalkulasiWaktu = (finalDistance / (double) i.getSpeed()) * 60; // titik tempuh dibagi kecepatan dan kali 60
            String waktuTempuh = String.format("%.2f", kalkulasiWaktu); // Format menjadi 00.00

            System.out.println("Waktu yang dihabiskan adalah: " + waktuTempuh + " menit" );

            if (i instanceof Refuelable r){ // Jika class implement refuelable
                if (r.isFuelLow()){ // jika bahan bakar rendah
                    System.out.println("bahan bakar " + i.getName() + " rendah, mengisi ulang...");
                    r.refuel(DEFAULT_REFUEL_AMOUNT); // refuel
                }
            }
            System.out.println();
        }
    }
}
