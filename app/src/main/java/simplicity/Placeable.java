package simplicity;

interface Placeable {
    
    public boolean checkEmptyPosisi(Posisi posisi);
    
    public void addObject(Non_Makanan objek, Point koordinatObjek, boolean isRotated, Inventory inventory);
    
    public void removeObject(Posisi posisiObjek, Inventory inventory);
    
    public void moveObject(Posisi posisiAwal, Point koordinatAkhir, boolean isRotated, Inventory inventory);
    
}
