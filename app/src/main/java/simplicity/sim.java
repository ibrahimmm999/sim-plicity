package simplicity;

import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

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

    public void setKekenyangan(int kekenyangan) {
        this.kekenyangan = kekenyangan;
    }

    public int getMood() {
        return mood;
    }

    public void setmood(int mood) {
        this.mood = mood;
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
            scanner.close();
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
            scanner.close();
        }
    }

    public void tidur() {
        Scanner scanner = new Scanner(System.in);
        int durasi = scanner.nextInt();
        // EFEK TIDUR
        mood += (30 * (durasi / 4));
        kesehatan += (20 * (durasi / 4));
        scanner.close();
    }

    public void makan(Object object) {
        if (object instanceof Masakan || object instanceof Bahan_Makanan) {
            if (inventory.getInventory().containsKey(object) && inventory.getInventory().get(object) > 0) {
                if (object instanceof Masakan) {
                    kekenyangan += ((Masakan) object).getValueKekenyangan();
                } else {
                    kekenyangan += ((Bahan_Makanan) object).getValueKekenyangan();
                }
                cekKekenyangan();
                mood += 5;
                cekMood();
                inventory.removeInventory(object);
                if (object instanceof Masakan) {
                    System.out.println(namaLengkap + " berhasil makan " + ((Masakan) object).getNamaObjek() + "!");
                } else {
                    System.out
                            .println(namaLengkap + " berhasil makan " + ((Bahan_Makanan) object).getNamaObjek() + "!");
                }
            } else {
                if (object instanceof Masakan) {
                    System.out.println(
                            namaLengkap + " tidak memiliki " + ((Masakan) object).getNamaObjek() + " dalam inventory!");
                } else {
                    System.out.println(namaLengkap + " tidak memiliki " + ((Bahan_Makanan) object).getNama()
                            + " dalam inventory!");
                }
            }
        } else {
            System.out.println("Object cannot be eaten.");
        }
    }

    public void memasak(Masakan masakan, Inventory inventory) {
        ArrayList<Bahan_Makanan> bahanDibutuhkan = masakan.getBahan();
        boolean semuaBahanTersedia = true;
        for (Bahan_Makanan bahan : bahanDibutuhkan) {
            if (!inventory.contains(bahan)) {
                semuaBahanTersedia = false;
                break;
            }
        }

        if (semuaBahanTersedia) {
            for (Bahan_Makanan bahan : bahanDibutuhkan) {
                inventory.removeInventory(bahan);
            }
            int waktuMasak = (int) (1.5 * masakan.getValueKekenyangan());
            kekenyangan += masakan.getValueKekenyangan();
            cekKekenyangan();
            mood += 10;
            cekMood();
            masakan.setIsAvailable(false);
            inventory.addInventory(masakan);
            Thread masakThread = new Thread(() -> {
                try {
                    Thread.sleep(waktuMasak * 1000);
                    masakan.setIsAvailable(true);
                    inventory.removeInventory(masakan);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            masakThread.start();
            System.out.println("Memasak " + masakan.getNamaObjek() + "...");
        } else {
            System.out.println("Bahan makanan tidak cukup!");
        }
    }

    public void berkunjung(Rumah tujuan) {
        int waktuTempuh;
        // this.getRumah() maksudnya buat rumah sim yang sedang dimainkan
        if (this.getRumah() == tujuan.getRumah()) {
            waktuTempuh = 0;
        } else {
            // getRumah yang disini maksudnya rumah nya si sim yg dimainkan
            // kurang tau gmn buatnya jadi aku buat this.getRumah() aja dulu
            waktuTempuh = (int) Math
                    .round(Math.sqrt(Math.pow(tujuan.getKoordinat().getX() - this.getRumah().getKoordinat().getX(), 2)
                            + Math.pow(tujuan.getKoordinat().getY() - this.getRumah().getKoordinat().getY(), 2)));
            this.getRumah().keluarRumah(this);
            tujuan.getRumah().masukRumah(this, tujuan.getKoordinat());
        }
        this.setmood(this.getMood() + waktuTempuh / 3); // Mood meningkat sebesar 10 untuk setiap 30 detik
        this.setKekenyangan(this.getKekenyangan() - waktuTempuh / 3); // Kekenyangan menurun sebesar 10 untuk setiap 30
                                                                      // detik
    }

    public void buangAir() {
        boolean berhasilBuangAir = false;
        for (int i = 0; i < inventory.getBarang().size(); i++) {
            if (ruangan.getBarang().get(i) instanceof Toilet) { // cek apakah ruangan memiliki toilet
                Toilet toilet = (Toilet) inventory.getBarang().get(i);
                if (toilet.getIsAvailable() && toilet.getIsOccupied()) { // cek apakah toilet tersedia
                    // getIsAvailable dan getIsOccupied bakal ada di kelas tiap objek
                    toilet.setIsAvailable(false); // ubah status toilet menjadi tidak tersedia
                    toilet.setIsOccupied(false);
                    kekenyangan -= 20;
                    cekKekenyangan();
                    mood += 10;
                    cekMood();
                    berhasilBuangAir = true;
                    System.out.println(namaLengkap + " berhasil buang air di " + toilet.getNamaObjek());
                    break;
                }
            }
        }
        if (!berhasilBuangAir) {
            if (waktuKerjaSim - pekerjaan.getWaktuKerja() >= 4) { // cek apakah sudah 4 menit setelah makan
                kesehatan -= 5;
                cekKesehatan();
                mood -= 5;
                cekMood();
                System.out.println(namaLengkap + " tidak berhasil buang air dan mengalami kesehatan dan mood menurun");
            } else {
                System.out.println(
                        namaLengkap + " tidak berhasil buang air dan masih terlalu cepat untuk mengalami efek negatif");
            }
        }
    }

    public void upgradeRumah() {
        // biayaupgrade nya brp ya? blm tau ini masih ngasal dlu biayanya
        int biayaUpgrade = 50; // biaya upgrade untuk menambah satu ruangan

        // cek apakah sim memiliki cukup uang untuk upgrade
        if (uang < biayaUpgrade) {
            System.out.println("Maaf, uang anda tidak cukup untuk melakukan upgrade rumah.");
            return;
        }

        // tambahkan ruangan baru pada rumah
        Ruangan ruanganBaru = new Ruangan();
        rumah.addRuangan(ruanganBaru);

        // kurangi uang sim sesuai biaya upgrade
        uang -= biayaUpgrade;

        // tambahkan waktu upgrade ke waktu total Sim
        // ini blm di update ke waktu yg di world nnt
        waktuKerjaSim += 18 * 60;

        System.out.println("Rumah berhasil diupgrade dengan tambahan satu ruangan.");
    }

    public void beliBarang(Objek barang) {
        if (this.uang < barang.getHarga()) {
            System.out.println("Uang anda tidak cukup!");
            return;
        }
        Random random = new Random();
        int durasi = random.nextInt(30) + 1;
        // kurangi uang sim
        this.uang -= barang.getHarga();
        // tambahkan objek ke inventory
        this.inventory.tambahBarang(barang);
        System.out.println("Anda telah membeli " + barang.getNama() + " seharga " + barang.getHarga() + ".");
    }

    public void pindahRuangan(Ruangan tujuan) {
        // Cek apakah tujuan ruangan sama dengan ruangan saat ini
        if (this.rumah.getRuangan() == tujuan) {
            System.out.println("Anda sudah berada di ruangan tersebut.");
            return;
        }

        // Cek apakah tujuan ruangan penuh
        // ini bisa full ga ya? blm tau
        if (tujuan.getJumlahObjek() >= tujuan.getKapasitas()) {
            System.out.println("Ruangan penuh.");
            return;
        }

        // Pindah ruangan dan update lokasi ruangan inventory
        this.rumah.setRuangan(tujuan);
        System.out.println("Anda telah pindah ke ruangan " + tujuan.getNama() + ".");
    }

    public void lihatInventory() {
        HashMap<Object, Integer> simInventory = inventory.getInventory();
        if (simInventory.isEmpty()) {
            System.out.println("Inventory kosong.");
        } else {
            System.out.println("Isi inventory:");
            for (Map.Entry<Object, Integer> entry : simInventory.entrySet()) {
                Object item = entry.getKey();
                int jumlah = entry.getValue();
                System.out.println("- " + item.toString() + " (" + jumlah + ")");
            }
        }
    }

    public void pasangBarang(Barang barang, Ruangan ruangan) {
        // Cek apakah ruangan memiliki ruang kosong
        boolean adaRuangKosong = false;
        for (Map.Entry<Object, Posisi> entry : ruangan.getListObjek().entrySet()) {
            Posisi posisi = entry.getValue();
            if (posisi == null) {
                adaRuangKosong = true;
                break;
            }
        }

        if (!adaRuangKosong) {
            System.out.println("Ruangan sudah penuh, tidak dapat memasang barang lagi.");
            return;
        }

        // Cek apakah barang muat di dalam ruangan
        if (!barang.cocokDenganRuangan(ruangan)) {
            System.out.println("Barang tidak muat dalam ruangan.");
            return;
        }

        // Cek apakah barang ada di inventory
        if (!inventory.cariBarang(barang.getNama())) {
            System.out.println("Barang tidak ditemukan di inventory.");
            return;
        }

        // Cari ruang kosong dan pasang barang di dalamnya
        for (Map.Entry<Object, Posisi> entry : ruangan.getListObjek().entrySet()) {
            Object key = entry.getKey();
            Posisi posisi = entry.getValue();
            if (posisi == null) {
                Point titikTengah = new Point((posisi.getKananAtas().x + posisi.getKiriBawah().x) / 2,
                        (posisi.getKananAtas().y + posisi.getKiriBawah().y) / 2);
                posisi.setObjek(barang);
                inventory.hapusBarang(barang.getNama());
                System.out.printf("%s berhasil dipasang di (%d, %d).\n", barang.getNama(), titikTengah.x,
                        titikTengah.y);
                return;
            }
        }
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