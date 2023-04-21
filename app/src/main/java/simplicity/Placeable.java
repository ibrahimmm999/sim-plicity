package simplicity;

interface Placeable {
    
    public boolean checkEmptyPosisi(Posisi posisi);
    
    public void addObject(Objek objek, Posisi posisiObjek, boolean isRotate);
    
    public void removeObject(Objek objek, Posisi posisiObjek);
    
    public void moveObject(Objek objek, Posisi posisiAwal, Posisi posisiAkhir, boolean isRotate);
    
}
