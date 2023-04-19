package objek;

public class SingleBed extends Non_Makanan {
    public SingleBed(){
        super("Kasur Single",4,1,"Tidur",50);
    }
    public boolean isOccupied(){
        return false;
    }
}
