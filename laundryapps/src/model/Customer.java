package model;

public class Customer {
    String id;
    String nama;
    String alamat;
    String nomorHp;
    String email;
    String telepon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNomorHp() {
        return nomorHp;
    }

    public void setNomorHp(String nomorHp) {
        this.nomorHp = nomorHp;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getTelepon() {
        return telepon;
    }

}
