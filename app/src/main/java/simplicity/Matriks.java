package simplicity;

public class Matriks {
    
    private int rows;
    private int columns;
    private int[][] matriks;
    
    public Matriks(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        
        int r, c;
        
        matriks = new int[rows][columns];
        
        for (r = 0; r < rows; r++) {
            for (c = 0; c < columns; c++) {
                matriks[r][c] = 0;
            }
        }
    }
    
    public void printMatriks() {
        int r, c;
        
        for (r = 0; r < rows; r++) {
            System.out.println(" ");
            for (c = 0; c < columns; c++) {
                System.out.print(matriks[r][c] + " ");
            }
        }
    }
    
    public void setMatriks(Point kiriAtas, Point kananBawah, boolean beAvail) {
        int x, y, value;
        int x1 = kiriAtas.getX();
        int x2 = kananBawah.getX();
        int y1 = kiriAtas.getY();
        int y2 = kananBawah.getY();
      
        if (beAvail == true) {
            value = 0;
        } else {
            value = 1;
        }
        for (y = y1; y <= y2; y++) {
            for(x = x1; x <= x2; x++) {
                matriks[y][x] = value;
            }
        }
    }
    
    
    public boolean checkAvailability(Point kiriAtas, Point kananBawah) {
        // return true apabila available
        boolean avail = true;
        int x, y;
        int x1 = kiriAtas.getX();
        int x2 = kananBawah.getX();
        int y1 = kiriAtas.getY();
        int y2 = kananBawah.getY();
        
        for (y = y1; y <= y2; y++) {
            for(x = x1; x <= x2; x++) {
                if (matriks[y][x] == 1) {
                    avail = false;
                }
            }
        }
        
        return avail;
    }
    
    
}