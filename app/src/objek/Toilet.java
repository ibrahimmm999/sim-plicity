package objek;

public class Toilet extends Non_Makanan{

    public Toilet(){
        super("Toilet", 1, 1, "Buang air", 50);
    }

    public boolean isOccupied(){
        return false;
    }
}
