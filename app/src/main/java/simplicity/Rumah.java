package simplicity;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Rumah {
    // private String namaRumah;
    private Point koordinat;
    private ArrayList<String> daftarNamaRuangan;
    private ArrayList<Ruangan> daftarRuangan;
    private Sim pemilik;

    public Rumah(Point koordinat, Sim pemilik) {
        // this.namaRumah = namaRumah;
        this.koordinat = koordinat;
        this.pemilik = pemilik;
        daftarNamaRuangan = new ArrayList<String>();
        daftarRuangan = new ArrayList<Ruangan>();
        Ruangan ruang1 = new Ruangan("Ruang 1", new Posisi(new Point(0, 0), new Point(5, 0)));
        daftarRuangan.add(ruang1);
        // MASIH KURANG NAMBAHIN BARANG DEFAULT (liat flow permainan di docs)
    }

    // public String getNamaRumah() {
    // return namaRumah;
    // }

    public Point getKoordinat() {
        return koordinat;
    }

    public ArrayList<String> getdaftarNamaRuangan() {
        return daftarNamaRuangan;
    }

    public ArrayList<Ruangan> getListRuangan() {
        return daftarRuangan;
    }

    public Ruangan getRuangan(String namaRuangan) {
        for (Ruangan ruangan : daftarRuangan) {
            if ((ruangan.getNamaRuangan()).equals(namaRuangan)) {
                return ruangan;
            }
        }
        return null;
    }

    public void addRuangan() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Masukkan nama ruangan : ");
                String namaRuangan = scanner.nextLine();
                System.out.println(namaRuangan);
                if (!(daftarNamaRuangan.contains(namaRuangan))) {
                    System.out.println("Silahkan masukkan koordinat untuk titik kiri atas dan kanan bawah ruangan!");
                    System.out.print("Masukkan titik X koordinat kiri atas : ");
                    int kiriAtasX = scanner.nextInt();
                    System.out.print("\nMasukkan titik Y koordinat kiri atas : ");
                    int kiriAtasY = scanner.nextInt();
                    System.out.print("\nMasukkan titik X koordinat kanan bawah : ");
                    int kananBawahX = scanner.nextInt();
                    System.out.print("\nMasukkan titik Y koordinat kanan bawah : ");
                    int kananBawahY = scanner.nextInt();
                    Point kiriAtas = new Point(kiriAtasX, kiriAtasY);
                    Point kananBawah = new Point(kananBawahX, kananBawahY);
                    if (checkEmptyKoordinat(kiriAtas, kananBawah)) {
                        Posisi posisiRuanganBaru = new Posisi(kiriAtas, kananBawah);
                        Ruangan ruanganBaru = new Ruangan(namaRuangan, posisiRuanganBaru);
                        daftarRuangan.add(ruanganBaru);
                        daftarNamaRuangan.add(namaRuangan);
                        System.out.println("Ruangan berhasil ditambahkan!");
                        break;
                    } else {
                        System.out.println("Koordinat sudah terpakai!");
                    }
                } else {
                    System.out.println("Nama ruangan sudah terpakai!");
                }
                System.out.println("Silahkan ulangi!");
            } catch (NoSuchElementException e) {
                System.out.println("Input salah, silakan coba lagi.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    public void removeRuangan() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Masukkan nama ruangan yang akan dihapus : ");
            String namaRuangan = scanner.nextLine();
            scanner.close();
            if ((daftarNamaRuangan.contains(namaRuangan))) {
                daftarNamaRuangan.remove(namaRuangan);
                for (Ruangan ruangan : daftarRuangan) {
                    if ((ruangan.getNamaRuangan()).equals(namaRuangan)) {
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

    public void masukRumah(Rumah now, Point koordinat) {
        now.koordinat = koordinat;
    }

    public Sim getPemilikRumah() {
        return pemilik;
    }
}
