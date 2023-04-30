package simplicity;

public class Bahan_Makanan extends Objek {

    private int valueKekenyangan;
    private int harga;
    private boolean isAvailable;

    public Bahan_Makanan(String nama) {
        super(nama);
        switch (nama) {
            case "Nasi":
                this.harga = 5;
                this.valueKekenyangan = 5;
                break;
            case "Kentang":
                this.harga = 3;
                this.valueKekenyangan = 4;
                break;
            case "Ayam":
                this.harga = 10;
                this.valueKekenyangan = 8;
                break;
            case "Sapi":
                this.harga = 12;
                this.valueKekenyangan = 15;
                break;
            case "Wortel":
                this.harga = 3;
                this.valueKekenyangan = 2;
                break;
            case "Bayam":
                this.harga = 3;
                this.valueKekenyangan = 2;
                break;
            case "Kacang":
                this.harga = 2;
                this.valueKekenyangan = 2;
                break;
            case "Susu":
                this.harga = 2;
                this.valueKekenyangan = 1;
                break;
            default:
                System.out.println("Invalid bahan makanan");
                this.harga = 0;
                this.valueKekenyangan = 0;
        }
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
