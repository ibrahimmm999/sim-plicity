package simplicity;

public class Toilet extends Non_Makanan {
    private boolean isAvailable;

    public Toilet() {
        super("Toilet", 1, 1, "Buang air", 50);
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
