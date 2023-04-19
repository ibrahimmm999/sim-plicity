package simplicity;

public class KomporListrik extends Non_Makanan {
    private boolean isAvailable;

    public KomporListrik() {
        super("Kompor Listrik", 1, 1, "Memasak", 200);
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
