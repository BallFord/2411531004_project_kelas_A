package Pekan5;

public class Pesawat extends Kendaraan implements TransportasiUdara, Maskapai {
    String jenisPesawat;
    String maskapai;

    public Pesawat(String merk, String model, int tahunProduksi, String jenisPesawat, String maskapai) {
        super(merk, model, tahunProduksi);
        this.jenisPesawat = jenisPesawat;
        this.maskapai = maskapai;
    }

    @Override
    public void nyalakanMesin() {
        System.out.println("Nyalakan Mesin: Bersiap lepas landas");
    }

    @Override
    public String jenisBahanBakar() {
        return "Avtur";
    }

    @Override
    public void jenisPenerbangan() {
        System.out.println("Jenis penerbangan: Komersial.");
    }

    @Override
    public String namaMaskapai() {
        return maskapai;
    }
}
