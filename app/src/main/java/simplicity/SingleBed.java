package simplicity;

public class SingleBed extends Non_Makanan {
    private boolean isAvailable;

    public SingleBed() {
        super("Kasur Single", 4, 1, "Tidur", 50);
    }

    public boolean isOccupied() {
        return false;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
