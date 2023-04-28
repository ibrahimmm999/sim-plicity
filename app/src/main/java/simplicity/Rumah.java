package simplicity;

import java.util.Scanner;
import java.util.ArrayList;

public class Rumah {
    private Point koordinat;
    private ArrayList<String> daftarNamaRuangan;
    private ArrayList<Ruangan> daftarRuangan;

    public Rumah(Point koordinat) {
        this.koordinat = koordinat;
        daftarNamaRuangan = new ArrayList<String>();
        daftarRuangan = new ArrayList<Ruangan>();
        Ruangan ruangUtama = new Ruangan(new Posisi(new Point(0, 6), new Point(6, 0)));
        // MASIH KURANG NAMBAHIN BARANG DEFAULT (liat flow permainan di docs)
    }

    public Point getKoordinat() {
        return koordinat;
    }

    public ArrayList<String> getdaftarNamaRuangan() {
        return daftarNamaRuangan;
    }

    public void addRuangan() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Masukkan nama ruangan : ");
                String namaRuangan = scanner.nextLine();
                System.out.println(namaRuangan);
                if (!(daftarNamaRuangan.contains(namaRuangan))) {
                    System.out.println("Silahkan masukkan koordinat untuk titik kiri atas dan kanan bawah ruangan!");
                    System.out.print("Masukkan titik X koordinat kiri atas : ");
                    int kiriAtasX = scanner.nextInt();
                    System.out.println(kiriAtasX);
                    System.out.print("\nMasukkan titik Y koordinat kiri atas : ");
                    int kiriAtasY = scanner.nextInt();
                    System.out.println(kiriAtasY);
                    System.out.print("\nMasukkan titik Y koordinat kanan bawah : ");
                    int kananBawahX = scanner.nextInt();
                    System.out.println(kananBawahX);
                    System.out.print("\nMasukkan titik Y koordinat kanan bawah : ");
                    int kananBawahY = scanner.nextInt();
                    System.out.println(kananBawahY);
                    Point kiriAtas = new Point(kiriAtasX, kiriAtasY);
                    Point kananBawah = new Point(kananBawahX, kananBawahY);
                    if (checkEmptyKoordinat(kiriAtas, kananBawah)) {
                        Posisi posisiRuanganBaru = new Posisi(kiriAtas, kananBawah);
                        Ruangan ruanganBaru = new Ruangan(posisiRuanganBaru);
                        daftarRuangan.add(ruanganBaru);
                        daftarNamaRuangan.add(namaRuangan);
                        System.out.println("Ruangan berhasil ditambahkan!");
                        scanner.close();
                        break;
                    } else {
                        System.out.println("Koordinat sudah terpakai!");
                    }
                } else {
                    System.out.println("Nama ruangan sudah terpakai!");
                }
                System.out.println("Silahkan ulangi!");
                scanner.close();
            } catch (Exception e) {
                System.out.println("Input salah, silahkan ulangi!");
            }

        }
    }

    public void removeRuangan() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Masukkan nama ruangan yang akan dihapus : ");
            String namaRuangan = scanner.nextLine();
            if ((daftarNamaRuangan.contains(namaRuangan))) {
                daftarNamaRuangan.remove(namaRuangan);
                for (Ruangan ruangan : daftarRuangan) {
                    if ((ruangan.getNama()).equals(namaRuangan)) {
                        daftarRuangan.remove(ruangan);
                        break;
                    }
                }
                break;
            } else {
                System.out.println("\nRuangan tidak ada!");
            }
        }
    }

    public boolean checkEmptyKoordinat(Point kiriAtas, Point kananBawah) {
        for (Ruangan ruangan : daftarRuangan) {
            if (kiriAtas.getX() >= ruangan.getPosisi().getKiriAtas().getX()
                    && kiriAtas.getX() <= ruangan.getPosisi().getKananBawah().getX()
                    && kiriAtas.getY() <= ruangan.getPosisi().getKiriAtas().getY()
                    && kiriAtas.getY() >= ruangan.getPosisi().getKananBawah().getY()) {
                return false;
            }
        }
        return true;
    }

}
