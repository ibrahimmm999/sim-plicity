package simplicity;

import java.util.HashMap;

public class Rumah {
    Point koordinat;
    HashMap<Ruangan, Posisi> daftarRuangan;

    public Rumah(Point koordinat) {
        this.koordinat = koordinat;
        daftarRuangan = new HashMap<Ruangan, Posisi>();
    }

    public Point getKoordinat() {
        return koordinat;
    }

    public HashMap<Ruangan, Posisi> getDaftarRuangan() {
        return daftarRuangan;
    }

    public void addListRuangan(Ruangan ruangan) {
        daftarRuangan.put(ruangan, ruangan.getPosisi());
    }

    public void removeRuangan(Ruangan ruangan) {
    }

    public boolean checkEmptyKoordinat(Point koordinat) {
        return false;
    }
}
