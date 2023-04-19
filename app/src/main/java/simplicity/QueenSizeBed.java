package simplicity;

public class QueenSizeBed extends Non_Makanan {
    private boolean isAvailable;

    public QueenSizeBed() {
        super("Kasur Queen Size", 4, 2, "Tidur", 100);
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
