package simplicity;

import java.util.ArrayList;

public class Masakan extends Objek {
    private boolean isAvailable;

    private ArrayList<Bahan_Makanan> bahan;
    private int valueKekenyangan;

    public Masakan(String nama, ArrayList<Bahan_Makanan> bahan, int valueKekenyangan) {
        super(nama);
        this.bahan = bahan;
        this.valueKekenyangan = valueKekenyangan;
    }

    public ArrayList<Bahan_Makanan> getBahan() {
        return this.bahan;
    }

    public int getValueKekenyangan() {
        return this.valueKekenyangan;
    }

    public boolean isPurchasable() {
        return false;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}