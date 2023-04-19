package simplicity;

public class KomporGas extends Non_Makanan {
    private boolean isAvailable;

    public KomporGas() {
        super("Kompor Gas", 2, 1, "Memasak", 100);
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
