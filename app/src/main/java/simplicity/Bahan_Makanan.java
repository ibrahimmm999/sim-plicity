package simplicity;

public class Bahan_Makanan extends Objek {

    private int valueKekenyangan;
    private int harga;
    private boolean isAvailable;

    public Bahan_Makanan(String nama, int valueKekenyangan, int harga) {
        super(nama);
        this.valueKekenyangan = valueKekenyangan;
        this.harga = harga;
    }

    public int getValueKekenyangan() {
        return this.valueKekenyangan;
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
}
