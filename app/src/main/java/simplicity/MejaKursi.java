package simplicity;

public class MejaKursi extends Non_Makanan {
    private boolean isAvailable;

    public MejaKursi() {
        super("Meja dan Kursi", 3, 3, "Makan", 50);
    }

    public boolean isUsed() {
        return false;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
