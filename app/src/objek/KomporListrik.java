package objek;

public class KomporListrik extends Non_Makanan {
    
    public KomporListrik(){
        super("Kompor Listrik", 1, 1, "Memasak", 200);
    }

    public boolean isUsed(){
        return false;
    }
}

