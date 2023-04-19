package simplicity;

public class KingSizeBed extends Non_Makanan {
    private boolean isAvailable;

    public KingSizeBed() {
        super("Kasur King Size", 5, 2, "Tidur", 150);
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
