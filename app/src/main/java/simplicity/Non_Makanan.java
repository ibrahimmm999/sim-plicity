package simplicity;

public class Non_Makanan extends Objek {
    private boolean isAvailable;
    private int panjang;
    private int lebar;
    private Posisi posisi;
    private String aksi;
    private int harga;

    public Non_Makanan(String nama, int panjang, int lebar, String aksi, int harga) {
        super(nama);
        this.panjang = panjang;
        this.lebar = lebar;
        this.aksi = aksi;
        this.harga = harga;
        this.posisi = null;
    }

    public int getPanjang() {
        return this.panjang;
    }

    public int getLebar() {
        return this.lebar;
    }

    public Posisi getPosisi() {
        return posisi;    
    }
    
    public String getAksi() {
        return this.aksi;
    }

    public int getHarga() {
        return this.harga;
    }

    public boolean isPurchasable() {
        return true;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    
    public void setPosisi(Posisi posisi) {
        this.posisi = posisi;
    }
}
