package simplicity;

public class Jam extends Non_Makanan {
    private boolean isAvailable;

    public Jam() {
        super("Jam", 1, 1, "Melihat Waktu", 10);
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
