package simplicity;

interface Placeable {
    public boolean checkEmptyPosisi(Posisi posisi);
    
    public void addObject(Object object, Posisi posisiObjek, boolean isRotate);
    
    public void removeObject(Object object, Posisi posisiObjek);
    
    public void moveObject(Object object, Posisi posisiAkhir, boolean isRotate);
    
}