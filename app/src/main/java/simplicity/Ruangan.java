package simplicity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

public class Ruangan implements Placeable {
    private String namaRuangan;
    private Posisi posisi;
    private HashMap<String, Non_Makanan> listObjek;
    private Matriks matriks;
    String[] listPosisiObjek = new String[217];

    public Ruangan(String namaRuangan, Posisi posisi) {
        this.namaRuangan = namaRuangan;
        matriks = new Matriks(6, 6);
        this.posisi = posisi;
        listObjek = new HashMap<>();
        Arrays.fill(listPosisiObjek, null);
    }

    public String getNamaRuangan() {
        return namaRuangan;
    }

    public Posisi getPosisi() {
        return posisi;
    }

    public HashMap<String, Non_Makanan> getListObjek() {
        return listObjek;
    }

    public void setPosisi(Posisi posisi) {
        this.posisi = posisi;
    }

    public void setListObjek(HashMap<String, Non_Makanan> listObjek) {
        this.listObjek = listObjek;
    }

    public Matriks getMatrix() {
        return matriks;
    }

    public List<Posisi> getListPosisiObject() {
        List<Posisi> listPosisiObject = new ArrayList<>();
        for (Map.Entry<String, Non_Makanan> entry : listObjek.entrySet()) {
            Non_Makanan objek = entry.getValue();
            if (objek.getPosisi() != null) {
                listPosisiObject.add(objek.getPosisi());
            }
        }
        return listPosisiObject;
    }

    public String[] getListPosisiObjek() {
        return listPosisiObjek;
    }

    public void setListPosisiObjek(String[] listPosisiObjek) {
        this.listPosisiObjek = listPosisiObjek;
    }

    public void printListObjek() {
        System.out.println(" ----------------------------------");
        System.out.println("|           Objek          | Titik |");
        System.out.println(" ----------------------------------");
        int idxKurung, space, x;
        String substr, kunci;
        for (String key : listObjek.keySet()) {
            System.out.print("| ");
            kunci = key;
            idxKurung = kunci.indexOf("(");
            substr = kunci.substring(idxKurung, kunci.length());
            space = 25 - kunci.length() + 4;
            System.out.print(kunci.substring(0, idxKurung));
            for (x = 0; x < space; x++) {
                System.out.print(" ");
            }
            System.out.println(" | " + substr + " |");
        }
        if (listObjek.size() > 0) {
            System.out.println(" ----------------------------------");
            System.out.println("*Kolom titik merupakan koordinat ujung kiri atas objek pada ruangan.");
        } else {
            System.out.println("Belum ada satu pun objek dalam ruangan.");
        }
    }

    public void addListObjek(Non_Makanan objek, Posisi posisi) {
        String namaObjek = objek.getNamaObjek();
        String koorX = "" + posisi.getKiriAtas().getX();
        String koorY = "" + posisi.getKiriAtas().getY();
        namaObjek = namaObjek.concat("(").concat(koorX).concat(",").concat(koorY).concat(")");
        listObjek.put(namaObjek, objek);
        ubahPosisiObjek(namaObjek, posisi, true);
    }

    public void ubahPosisiObjek(String namaObjek, Posisi posisi, boolean add) {
        int x1, x2, y1, y2, a, b, c;
        x1 = posisi.getKiriAtas().getX();
        y1 = posisi.getKiriAtas().getY();
        x2 = posisi.getKananBawah().getX();
        y2 = posisi.getKananBawah().getY();
        if (!add) { // add == true maka namaObjek ditambah, sebaliknya add == false maka namaObjek
                    // dijadikan null
            namaObjek = null;
        }
        for (a = x1; a <= x2; a++) {
            for (b = y1; b <= y2; b++) {
                c = (a * 1 + b * 2) * (a + b);
                listPosisiObjek[c] = namaObjek;
            }
        }
    }

    public void removeListObjek(String namaObjek, Non_Makanan objek) {
        listObjek.remove(namaObjek, objek);
        ubahPosisiObjek(namaObjek, posisi, false);
    }

    public boolean checkEmptyPosisi(Posisi posisi) {
        return (matriks.checkAvailability(posisi.getKiriAtas(), posisi.getKananBawah()));
    }

    public Matriks getMatriks() {
        return matriks;
    }

    public void addObject(Non_Makanan objek, Point koordinatObjek, boolean isRotated, Inventory inventory) {
        int width, length;

        if (isRotated) { // panjang dan lebar (dimensi) default objek ditukar
            width = objek.getPanjang();
            length = objek.getLebar();
        } else {
            width = objek.getLebar();
            length = objek.getPanjang();
        }

        Point kananBawah = new Point(koordinatObjek.getX() + length, koordinatObjek.getY() - width);
        Posisi posisi = new Posisi(koordinatObjek, kananBawah);

        if (checkEmptyPosisi(posisi)) { // posisi kosong (tersedia)
            matriks.setMatriks(koordinatObjek, kananBawah, false);
            addListObjek(objek, posisi);
            objek.setPosisi(posisi);
            inventory.removeInventory(objek);
            System.out.println("Object " + objek.getNamaObjek() + " berhasil diletakkan!");
        } else {
            System.out.println("Tidak bisa meletakkan barang.");
        }
    }

    public void addObject1(Non_Makanan objek, Point koordinatObjek, boolean isRotated, Inventory inventory) {
        int width, length;

        if (isRotated) { // panjang dan lebar (dimensi) default objek ditukar
            width = objek.getPanjang();
            length = objek.getLebar();
        } else {
            width = objek.getLebar();
            length = objek.getPanjang();
        }

        Point kananBawah = new Point(koordinatObjek.getX() + length, koordinatObjek.getY() - width);
        Posisi posisi = new Posisi(koordinatObjek, kananBawah);

        if (checkEmptyPosisi(posisi)) { // posisi kosong (tersedia)
            matriks.setMatriks(koordinatObjek, kananBawah, false);
            addListObjek(objek, posisi);
            objek.setPosisi(posisi);
            System.out.println("Object " + objek.getNamaObjek() + " berhasil diletakkan untuk pertama kali!");
        } else {
            System.out.println("Tidak bisa meletakkan barang.");
        }
    }

    public void removeObject(Posisi posisiObjek, Inventory inventory) {
        matriks.setMatriks(posisiObjek.getKiriAtas(), posisiObjek.getKananBawah(), true);
        int x = posisiObjek.getKiriAtas().getX();
        int y = posisiObjek.getKiriAtas().getY();
        int z = (x * 1 + y * 2) * (x + y);
        String namaObjek = listPosisiObjek[z];
        inventory.addInventory(listObjek.get(namaObjek));
        z = 999;
        Point point = new Point(z, z);
        Posisi posisi = new Posisi(point, point);
        listObjek.get(namaObjek).setPosisi(posisi);
        removeListObjek(namaObjek, listObjek.get(namaObjek));
        System.out.println("Barang berhasil dipindahkan ke inventory!");
    }

    public void moveObject(Posisi posisiAwal, Point koordinatAkhir, boolean isRotated, Inventory inventory) {
        int x, y, z;
        x = posisiAwal.getKiriAtas().getX();
        y = posisiAwal.getKiriAtas().getY();
        z = (x * 1 + y * 2) * (x + y);
        String namaObjek = listPosisiObjek[z];

        int length = listObjek.get(namaObjek).getPanjang();
        int width = listObjek.get(namaObjek).getLebar();
        Point kananBawah = new Point(koordinatAkhir.getX() + length - 1, koordinatAkhir.getY() + width - 1);
        Posisi posisiAkhir = new Posisi(koordinatAkhir, kananBawah);
        Non_Makanan objek = listObjek.get(namaObjek);
        removeObject(posisiAwal, inventory);

        if (checkEmptyPosisi(posisiAkhir)) {
            addObject(objek, koordinatAkhir, isRotated, inventory);
            System.out.println("Barang berhasil dipindah");
        } else {
            addObject(objek, posisiAwal.getKiriAtas(), false, inventory);
            System.out.println("Gagal memindah objek karena koordinat sudah terpakai");
        }

    }

    public Non_Makanan getObjectAtPoint(Point point) {
        int x, y, z;
        x = point.getX();
        y = point.getY();
        z = (x * 1 + y * 2) * (x + y);
        String namaObjek = listPosisiObjek[z];
        return listObjek.get(namaObjek);
    }
}
