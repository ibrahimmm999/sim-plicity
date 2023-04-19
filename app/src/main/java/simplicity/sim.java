package simplicity;

import java.util.Random;
import java.util.Scanner;

public class Sim {
    private String namaLengkap;
    private Pekerjaan pekerjaan;
    private int uang;
    private Inventory inventory;
    private int kekenyangan;
    private int mood;
    private int kesehatan;
    private String status;
    private int waktuKerjaSim; // waktu total sim kerja buat ngecek gajian

    public Sim(String namaLengkap) {
        this.namaLengkap = namaLengkap;
        this.pekerjaan = getRandomPekerjaan();
        this.uang = 100;
        this.kekenyangan = 80;
        this.mood = 80;
        this.kesehatan = 80;
        this.waktuKerjaSim = 0;
    }

    // pekerjaan harusnya dimasukin ke array dulu
    public Pekerjaan getRandomPekerjaan() {
        Pekerjaan[] daftarPekerjaan = pekerjaan.getAllPekerjaan();
        Random random = new Random();
        int index = random.nextInt(daftarPekerjaan.length);
        return daftarPekerjaan[index];
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public Pekerjaan getPekerjaan() {
        return pekerjaan;
    }

    public int getUang() {
        return uang;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getKekenyangan() {
        return kekenyangan;
    }

    public int getMood() {
        return mood;
    }

    public int getKesehatan() {
        return kesehatan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void cekMood() {
        if (mood == 0) {
            // tindakan mati karna depresi
            throw new RuntimeException("Sim ini sudah mati karena depresi.");
        }
        // implementasi lainnya
    }

    public void cekKesehatan() {
        if (kesehatan == 0) {
            // tindakan mati karna sakit
            throw new RuntimeException("Sim ini sudah mati karena sakit.");
        }
        // implementasi lainnya
    }

    public void cekKekenyangan() {
        if (kekenyangan == 0) {
            // tindakan mati karna kelaparan
            throw new RuntimeException("Sim ini sudah mati karena kelaparan.");
        }
        // implementasi lainnya
    }

    public void kerja() {
        while (true) {
            System.out.print("Masukkan durasi kerja dalam satuan detik (kelipatan 120) : ");
            Scanner scanner = new Scanner(System.in);
            int durasi = scanner.nextInt();
            if (durasi % 120 == 0) {
                setStatus("kerja");
                kekenyangan -= (10 * (durasi / 30));
                mood -= (10 * (durasi / 30));
                waktuKerjaSim += durasi;
                if (waktuKerjaSim >= 240) {
                    uang += pekerjaan.getGaji() * waktuKerjaSim / 240;
                    waktuKerjaSim = waktuKerjaSim % 240;
                }
                // CEK MATI
                cekMood();
                cekKesehatan();
                cekKekenyangan();
                break;
            } else {
                System.out.println("Masukkan input yang benar");
            }
        }
    }

    public void olahraga() {
        while (true) {
            System.out.print("Masukkan durasi olahraga dalam satuan detik (kelipatan 20) : ");
            Scanner scanner = new Scanner(System.in);
            int durasi = scanner.nextInt();
            if (durasi % 20 == 0) {
                setStatus("olahraga");
                kekenyangan -= (5 * (durasi / 20));
                kesehatan += (5 * (durasi / 20));
                mood += (10 * (durasi / 20));
                // CEK MATI
                cekMood();
                cekKesehatan();
                cekKekenyangan();
                break;
            } else {
                System.out.println("Masukkan input yang benar");
            }
        }
    }

    public void tidur() {
        Scanner scanner = new Scanner(System.in);
        int durasi = scanner.nextInt();    
        // EFEK TIDUR
        mood += (30 * (durasi / 4));
        kesehatan += (20 * (durasi / 4));

    }

    public void makan() {

    }

    public void memasak(BahanMakanan[] listBahanMakanan) {

    }

    public void berkunjung(Rumah tujuan) {

    }

    public void buangAir() {

    }

    public void upgradeRumah() {

    }

    public void beliBarang(Objek barang) {

    }

    public void pindahRuangan() {

    }

    public void lihatInventory() {

    }

    public void pasangBarang() {

    }

    public void lihatWaktu() {

    }

    public void berdoa() {

    }

    public void rapihinKasur() {

    }

    public void meditasi() {

    }

    public void socialize() {

    }

    public void beresinKamarMandi() {

    }

    public void belajar() {

    }

    public void nubes() {

    }
}