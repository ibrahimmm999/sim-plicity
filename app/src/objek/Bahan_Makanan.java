package objek;

public class Bahan_Makanan extends Objek {
    private int valueKekenyangan;
    private int harga;

    public Bahan_Makanan(String nama, int valueKekenyangan, int harga){
        super(nama);
        this.valueKekenyangan = valueKekenyangan;
        this.harga = harga;
    }

    public int getValueKekenyangan(){
        return this.valueKekenyangan;
    }

    public int getHarga(){
        return this.harga;
    }

    public boolean isPurchasable(){
        return true;
    }
}
