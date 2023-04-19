package simplicity;

public abstract class Objek {

    private String namaObjek;

    public Objek(String namaObjek) {
        this.namaObjek = namaObjek;
    }

    public String getNamaObjek() {
        return this.namaObjek;
    }

    public abstract boolean isPurchasable();
}
