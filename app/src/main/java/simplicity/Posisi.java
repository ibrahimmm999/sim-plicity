package simplicity;

public class Posisi {
    
    private Point kiriAtas;
    private Point kiriBawah;
    private Point kananAtas;
    private Point kananBawah;
    
    public Posisi(Point kiriAtas, Point kiriBawah, Point kananAtas, Point kananBawah) {
        if(kiriAtas.getX() == kiriBawah.getX() && kananAtas.getX() == kananBawah.getX() && kiriAtas.getY() == kananAtas.getY() && kiriBawah.getY() == kananBawah.getY()) {
            this.kiriAtas = kiriAtas;
            this.kiriBawah = kiriBawah;
            this.kananAtas = kananAtas;
            this.kananBawah = kananBawah;    
        } else {
            throw new RuntimeException("Baik kiriAtas dengan kiriBawah maupun kananAtas dengan kananBawah harus berada pada garis lurus secara horizontal, juga kiriAtas dengan kananAtas maupun kiriBawah dengan kananBawah harus berada pada garis lurus secara horizontal");
        }
    }
    
    public Point getKiriAtas() {
        return kiriAtas;
    }
    
    public Point getKiriBawah() {
        return kiriBawah;
    }
    
    public Point getKananAtas() {
        return kananAtas;
    }
    
    public Point getKananBawah() {
        return kananBawah;
    }
}