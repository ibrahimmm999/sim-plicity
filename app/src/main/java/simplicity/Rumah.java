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
    private Matriks matriks;

    public Rumah(Point koordinat, Sim pemilik) {
        // this.namaRumah = namaRumah;
        this.koordinat = koordinat;
        this.pemilik = pemilik;
        matriks = new Matriks(98, 98);
        daftarNamaRuangan = new ArrayList<String>();
        daftarRuangan = new ArrayList<Ruangan>();
        Ruangan ruang1 = new Ruangan("Ruang 1", new Posisi(new Point(0, 0), new Point(5, 0)));
        boolean bool = matriks.setDenahRumah(new Point(48,48), 1);
        daftarRuangan.add(ruang1);
        daftarNamaRuangan.add("Ruang 1");
        // MASIH KURANG NAMBAHIN BARANG DEFAULT (liat flow permainan di docs)
    }

    // public String getNamaRumah() {
    // return namaRumah;
    // }

    public Point getKoordinat() {
        return koordinat;
    }
    
    public Matriks getMatriks() {
        return matriks;
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
    
    public void cetakDaftarNamaRuangan() {
        for (int i = 0; i < daftarNamaRuangan.size(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(daftarNamaRuangan.get(i));
        }
    }

    public void addRuanganX() {
        Scanner sc = new Scanner(System.in);
        Point kiriAtas;
        String ruanganPatokan;
        cetakDaftarNamaRuangan();
        while (true) {
            try {
                System.out.print("Masukkan nama ruangan yang ingin dijadikan sebagai patokan: ");
                ruanganPatokan = sc.nextLine();

                if (daftarNamaRuangan.contains(ruanganPatokan)) {
                    break;
                } else { // ruangan patokan bukan merupakan ruangan dalam rumah
                    System.out.println("Harap masukkan nama ruangan patokan yang berada dalam rumah--nama yang berada pada daftar ruangan");
                }
                // System.out.print("Masukkan sisi yang ingin diekspansi (Atas/Bawah/Kanan/Kiri): ");

            } catch (NoSuchElementException e) {
                System.out.println("Input salah, silahkan coba lagi.");
                sc.nextLine();
            }
        }

        while (true) {
            try {
                System.out.print("Masukkan sisi yang ingin diekspansi (Atas/Bawah/Kanan/Kiri): ");
                String sisiEkspansi = sc.nextLine();

                if (sisiEkspansi == "Atas" || sisiEkspansi == "Bawah" || sisiEkspansi == "Kanan" || sisiEkspansi == "Kiri") {
                    boolean berhasilEkspansi = false;
                    Ruangan ruangPenanda = daftarRuangan.get(daftarNamaRuangan.indexOf(ruanganPatokan));
                    Point titikRuangPenanda = ruangPenanda.getPosisi().getKiriAtas();
                    int x = titikRuangPenanda.getX();
                    int y = titikRuangPenanda.getY();

                    if (sisiEkspansi == "Atas") {
                        kiriAtas = new Point(x, (y - 6));
                    } else if (sisiEkspansi == "Bawah") {
                        kiriAtas = new Point(x, (y + 6));
                    } else if (sisiEkspansi == "Kanan") {
                        kiriAtas = new Point((x + 6), y);
                    } else { // sisiEkspansi == "Kiri"
                        kiriAtas = new Point((x - 6), y);
                    }
                    berhasilEkspansi = matriks.setDenahRumah(kiriAtas, (daftarNamaRuangan.size() + 1));
                    if(berhasilEkspansi) {
                        break;
                    } else {
                    System.out.println("Harap masukkan sisi ekspansi yang valid");
                    }
                }
            } catch (NoSuchElementException e) {
                System.out.println("Input salah, silakan coba lagi.");
                sc.nextLine();
            }
        }

        while (true) {
            try {
                System.out.print("Masukkan nama ruangan baru yang ingin ditambahkan: ");
                String ruanganBaru = sc.nextLine();

                if (!(daftarNamaRuangan.contains(ruanganBaru))) {
                    Posisi posisiRuangBaru = new Posisi(kiriAtas, new Point((kiriAtas.getX() + 5), (kiriAtas.getY() + 5)));
                    Ruangan ruangBaru = new Ruangan(ruanganBaru, posisiRuangBaru);
                    daftarRuangan.add(ruangBaru);
                    daftarNamaRuangan.add(ruanganBaru);
                    System.out.println("Ruangan berhasil ditambahkan.");
                    break;
                } else {
                    System.out.println("Upgrade gagal dilakukan karena sudah ada ruangan pada sisi tersebut. Mohon pilih sisi ekspansi lain.");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Input salah, silakan coba lagi");
                sc.nextLine();
            }
        }
        

        while (true) {
            try {
                System.out.print("Masukkan nama ruangan baru yang ingin ditambahkan: ");
                String ruanganBaru = sc.nextLine();

                if (!(daftarNamaRuangan.contains(ruanganBaru))) {
                    Posisi posisiRuangBaru = new Posisi(kiriAtas, new Point((kiriAtas.getX() + 5), (kiriAtas.getY() + 5)));
                    Ruangan ruangBaru = new Ruangan(ruanganBaru, posisiRuangBaru);
                    daftarRuangan.add(ruangBaru);
                    daftarNamaRuangan.add(ruanganBaru);
                    System.out.println("Ruangan berhasil ditambahkan.");
                    break;
                } else {
                    System.out.println("Upgrade gagal dilakukan karena sudah ada ruangan pada sisi tersebut. Mohon pilih sisi ekspansi lain.");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Input salah, silakan coba lagi");
                sc.nextLine();
            }
        }
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
