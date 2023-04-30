package simplicity;

import java.util.ArrayList;
import java.util.Arrays;

public class Masakan extends Objek {
    private boolean isAvailable;

    private ArrayList<Bahan_Makanan> bahan;
    private int valueKekenyangan;

    public Masakan(String nama) {
        super(nama);
        switch (nama) {
            case "Nasi Ayam":
                this.bahan = new ArrayList<>(Arrays.asList(new Bahan_Makanan("Nasi"), new Bahan_Makanan("Ayam")));
                this.valueKekenyangan = 16;
                break;
            case "Nasi Kari":
                this.bahan = new ArrayList<>(Arrays.asList(new Bahan_Makanan("Nasi"), new Bahan_Makanan("Kentang"),
                        new Bahan_Makanan("Wortel"), new Bahan_Makanan("Sapi")));
                this.valueKekenyangan = 30;
                break;
            case "Susu Kacang":
                this.bahan = new ArrayList<>(Arrays.asList(new Bahan_Makanan("Susu"), new Bahan_Makanan("Kacang")));
                this.valueKekenyangan = 5;
                break;
            case "Tumis Sayur":
                this.bahan = new ArrayList<>(Arrays.asList(new Bahan_Makanan("Wortel"), new Bahan_Makanan("Bayam")));
                this.valueKekenyangan = 5;
                break;
            case "Bistik":
                this.bahan = new ArrayList<>(Arrays.asList(new Bahan_Makanan("Kentang"), new Bahan_Makanan("Sapi")));
                this.valueKekenyangan = 22;
                break;
            default:
                System.out.println("Masakan tidak ditemukan.");
                break;
        }
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
