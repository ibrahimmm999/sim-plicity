package simplicity;

public class Point {
    
    // Kelas ini merepresentasikan sebuah titik pada koordinat kuadran I. Dengan kata lain, nilai x maupun y tidak boleh negatif
    private int x = 0;
    private int y = 0;
    
    public Point(int x, int y) {
        if(x >= 0 && y >= 0) {
            this.x = x;
            this.y = y;    
        } else {
            throw new RuntimeException("Baik nilai x maupun y tidak boleh negatif.");
        }
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
}