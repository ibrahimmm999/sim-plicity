package simplicity;

public class KomporGas extends Non_Makanan {
    
    public KomporGas(){
        super("Kompor Gas", 2, 1, "Memasak", 100);
    }

    public boolean isUsed(){
        return false;
    }
}
