package simplicity;

public class Toilet extends Non_Makanan {
    private boolean isAvailable;
    private boolean isOccupied;

    public Toilet() {
        super("Toilet", 1, 1, "Buang air", 50);
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
