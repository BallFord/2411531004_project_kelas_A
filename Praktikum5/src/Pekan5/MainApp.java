package Pekan5;

public class MainApp {
    public static void main(String[] args) {
        Mobil mobil = new Mobil("Toyota", "Avanza", 2021, "Automatic");
        Bus bus = new Bus("Mercedes-Benz", "Bus Pariwisata", 2018, "Eksekutif");
        Pesawat pesawat = new Pesawat("Garuda", "Boeing 737", 100, "Jet", "Garuda Indonesia");

        System.out.println("=== Informasi Mobil ===");
        mobil.tampilkanInfo();
        mobil.nyalakanMesin();
        System.out.println("Jenis Bahan Bakar: " + mobil.jenisBahanBakar());
        mobil.fiturMobil();
        mobil.infoKonsumsi();

        System.out.println("\n=== Informasi Bus ===");
        bus.tampilkanInfo();
        bus.nyalakanMesin();
        System.out.println("Jenis Bahan Bakar: " + bus.jenisBahanBakar());
        System.out.println("Kapasitas: " + bus.kapasitasPenumpang() + " Penumpang");
        bus.fiturBus();
        bus.infoKonsumsi();

        Bus.JadwalPerjalanan jadwal = bus.new JadwalPerjalanan("Jakarta - Bandung", "08:00");
        jadwal.tampilkanJadwal();

        System.out.println("\n=== Informasi Pesawat ===");
        pesawat.tampilkanInfo();
        pesawat.nyalakanMesin();
        System.out.println("Jenis Bahan Bakar: " + pesawat.jenisBahanBakar());
        pesawat.jenisPenerbangan();
        System.out.println("Maskapai: " + pesawat.namaMaskapai());
        pesawat.infoKonsumsi();
    }
}
