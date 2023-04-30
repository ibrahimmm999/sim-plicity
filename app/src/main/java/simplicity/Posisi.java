package simplicity;

public class Posisi {

    private Point kiriAtas;
    private Point kananBawah;

    public Posisi(Point kiriAtas, Point kananBawah) {
        if (kiriAtas.getX() <= kananBawah.getX() && kiriAtas.getY() >= kananBawah.getY()) {
            this.kiriAtas = kiriAtas;
            this.kananBawah = kananBawah;
        } else {
            System.out.println(
                    "Perhatikan kembali posisi titik kiri atas dan kanan bawah: nilai x pada koordinat kiri atas harus lebih kecil atau sama dengan nilai x pada koordinat kanan bawah, sedangkan nilai y pada koordinat kiri atas harus lebih besar atau sama dengan nilai y pada koordinat kanan bawah.");
        }
    }

    public void cetakPosisi() {
        System.out.println("(" + kiriAtas.getX() + ", " + kiriAtas.getY() + "); (" + kananBawah.getX() + ", "
                + kananBawah.getY() + ")");
    }

    public Point getKiriAtas() {
        return kiriAtas;
    }

    public Point getKananBawah() {
        return kananBawah;
    }
}