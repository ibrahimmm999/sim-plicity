package simplicity;

public abstract class Objek {
    private Posisi posisi;
    private String deskripsi;

    private String namaObjek;

    public Objek(String namaObjek) {
        this.namaObjek = namaObjek;
    }

    public Objek(String namaObjek, Posisi posisi, String deskripsi){
        this.namaObjek = namaObjek;
        this.posisi = posisi;
        this.deskripsi = deskripsi;
    }

    public Posisi getPosisi() {
        return this.posisi;
    }

    public void setPosisi(Posisi posisi) {
        this.posisi = posisi;
    }

    public String getDeskripsi() {
        return this.deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getNamaObjek() {
        return this.namaObjek;
    }


    public void setNamaObjek(String namaObjek) {
        this.namaObjek = namaObjek;
    }

    public abstract boolean isPurchasable();
}