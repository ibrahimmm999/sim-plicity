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
                    System.out.println(namaLengkap + " tidak memiliki " + ((Bahan_Makanan) object).getNamaObjek()
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

    public void buangAir(Ruangan ruangan) {
        boolean berhasilBuangAir = false;
        for (Non_Makanan objek : ruangan.getListObjek().values()) {
            if (objek instanceof Toilet) {
                Toilet toilet = (Toilet) objek;
                if (toilet.getIsAvailable() && toilet.isOccupied()) {
                    toilet.setIsAvailable(false);
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
            if (waktuKerjaSim - pekerjaan.getWaktuKerja() >= 4) {
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

    public void upgradeRumah(Posisi posisi) {
        // biayaupgrade nya brp ya? blm tau ini masih ngasal dlu biayanya
        int biayaUpgrade = 50; // biaya upgrade untuk menambah satu ruangan

        // cek apakah sim memiliki cukup uang untuk upgrade
        if (uang < biayaUpgrade) {
            System.out.println("Maaf, uang anda tidak cukup untuk melakukan upgrade rumah.");
            return;
        }

        // tambahkan ruangan baru pada rumah
        Ruangan ruanganBaru = new Ruangan(posisi);
        // ini blm ada rumah
        Rumah.addRuangan(ruanganBaru);

        // kurangi uang sim sesuai biaya upgrade
        uang -= biayaUpgrade;

        // tambahkan waktu upgrade ke waktu total Sim
        // ini blm di update ke waktu yg di world nnt
        waktuKerjaSim += 18 * 60;

        System.out.println("Rumah berhasil diupgrade dengan tambahan satu ruangan.");
    }

    public void beliBarang(Objek barang) {
        if (barang instanceof Non_Makanan) {
            Non_Makanan nm = (Non_Makanan) barang;
            if (this.uang < nm.getHarga()) {
                System.out.println("Uang anda tidak cukup!");
                return;
            }
            Random random = new Random();
            int durasi = random.nextInt(30) + 1;
            // kurangi uang sim
            this.uang -= nm.getHarga();
            // tambahkan objek ke inventory
            this.inventory.addInventory(nm);
            System.out.println("Anda telah membeli " + nm.getNamaObjek() + " seharga " + nm.getHarga() + ".");
            try {
                Thread.sleep(durasi * 10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (barang instanceof Bahan_Makanan) {
            Bahan_Makanan bahan = (Bahan_Makanan) barang;
            if (this.uang < bahan.getHarga()) {
                System.out.println("Uang anda tidak cukup!");
                return;
            }
            Random random = new Random();
            int durasi = random.nextInt(30) + 1;
            // kurangi uang sim
            this.uang -= bahan.getHarga();
            // tambahkan objek ke inventory
            this.inventory.addInventory(bahan);
            System.out.println("Anda telah membeli " + bahan.getNamaObjek() + " seharga " + bahan.getHarga() + ".");
            try {
                Thread.sleep(durasi * 10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //
    public void pindahRuangan(Ruangan tujuan) {
        // Cek apakah tujuan ruangan sama dengan ruangan saat ini
        if (this.Rumah.getRuangan() == tujuan) {
            System.out.println("Anda sudah berada di ruangan tersebut.");
            return;
        }

        // Pindah ruangan dan update lokasi ruangan inventory
        this.Rumah.setRuangan(tujuan);
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

    public void pasangBarang(Non_Makanan barang, Ruangan ruangan) {
        // Cek apakah ruangan memiliki ruang kosong
        boolean adaRuangKosong = false;
        for (int i = 0; i < ruangan.getListPosisiObjek().length; i++) {
            if (ruangan.getListPosisiObjek()[i] == null) {
                adaRuangKosong = true;
                break;
            }
        }

        if (!adaRuangKosong) {
            System.out.println("Ruangan sudah penuh, tidak dapat memasang barang lagi.");
            return;
        }

        // Cek apakah barang muat di dalam ruangan
        int[] ukuranBarang = { barang.getPanjang(), barang.getLebar() };
        Matriks matriks = ruangan.getMatriks();
        Posisi posisiBarang = matriks.cariPosisi(ukuranBarang);
        if (posisiBarang == null) {
            System.out.println("Barang tidak muat dalam ruangan.");
            return;
        }

        // Cek apakah barang ada di inventory
        if (!inventory.cariBarang(barang.getNamaObjek())) {
            System.out.println("Barang tidak ditemukan di inventory.");
            return;
        }

        // Pasang barang di posisi yang tersedia
        String[] listPosisiObjek = ruangan.getListPosisiObjek();
        for (int i = 0; i < listPosisiObjek.length; i++) {
            if (listPosisiObjek[i] == null) {
                barang.setPosisi(posisiBarang);
                inventory.hapusBarang(barang.getNamaObjek());
                System.out.printf("%s berhasil dipasang di (%d, %d).\n", barang.getNamaObjek(),
                        posisiBarang.getKiriAtas().getX(), posisiBarang.getKiriAtas().getY());
                listPosisiObjek[i] = barang.getNamaObjek();
                ruangan.setListPosisiObjek(listPosisiObjek);
                ruangan.addListObjek(barang, posisiBarang);
                matriks.tandaiPosisi(posisiBarang);
                return;
            }
        }
    }

    public void lihatWaktu() {

    }

    public void berdoa(Ruangan ruangan) {
        MejaKursi mejaKursi = null;
        for (Map.Entry<String, Non_Makanan> entry : ruangan.getListObjek().entrySet()) {
            Objek objek = entry.getValue();
            if (objek instanceof MejaKursi) {
                mejaKursi = (MejaKursi) objek;
                break;
            }
        }
        if (mejaKursi != null) {
            Posisi posisiMejaKursi = mejaKursi.getPosisi();
            System.out.println("Sim berdoa di meja kursi pada posisi " + posisiMejaKursi);
        } else {
            System.out.println("Sim tidak menemukan meja kursi di ruangan ini");
        }
    }

    public void rapihinKasur(Object object) {
        if (kekenyangan >= 5) {
            if (object instanceof SingleBed) {
                System.out.println("SIM merapikan kasur Single Bed");
            } else if (object instanceof QueenSizeBed) {
                System.out.println("SIM merapikan kasur Queen Size Bed");
            } else if (object instanceof KingSizeBed) {
                System.out.println("SIM merapikan kasur King Size Bed");
            } else {
                System.out.println("Kasur yang diberikan tidak valid");
                return;
            }
            kekenyangan -= 5;
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("SIM kekurangan energi untuk merapikan kasur " + ((Objek) object).getNamaObjek());
        }
    }

    public void meditasi() {

    }

    public void socialize() {

    }

    public void beresinKamarMandi(Ruangan ruangan) {
        // Cari toilet
        Toilet toilet = null;
        for (Map.Entry<String, Non_Makanan> entry : ruangan.getListObjek().entrySet()) {
            Objek objek = entry.getValue();
            if (objek instanceof MejaKursi) {
                toilet = (Toilet) objek;
                break;
            }
        }

        if (toilet == null) { // jika toilet tidak ditemukan, tampilkan pesan kesalahan
            System.out.println("Tidak ada toilet dalam ruangan ini");
            return;
        }

        if (toilet.isOccupied()) { // jika toilet sedang digunakan, tampilkan pesan kesalahan
            System.out.println("Toilet sedang digunakan");
            return;
        }

        // membersihkan toilet dan menandai toilet sebagai sedang digunakan
        toilet.setIsOccupied(true);

        // menunggu selama 10 detik
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // menandai toilet sebagai tersedia kembali dan mengurangi kekenyangan sebesar 5
        toilet.setIsOccupied(false);
        kekenyangan -= 5;
    }

    public void belajar(Ruangan ruangan) {
        MejaKursi mejaKursi = null;
        for (Map.Entry<String, Non_Makanan> entry : ruangan.getListObjek().entrySet()) {
            Objek objek = entry.getValue();
            if (objek instanceof MejaKursi) {
                mejaKursi = (MejaKursi) objek;
                break;
            }
        }
        if (mejaKursi != null) {
            Posisi posisiMejaKursi = mejaKursi.getPosisi();
            System.out.println("Sim belajar di meja kursi pada posisi " + posisiMejaKursi);
        } else {
            System.out.println("Sim tidak menemukan meja kursi di ruangan ini untuk belajar");
        }
    }

    public void ngegame(Ruangan ruangan) {
        MejaKursi mejaKursi = null;
        for (Map.Entry<String, Non_Makanan> entry : ruangan.getListObjek().entrySet()) {
            Objek objek = entry.getValue();
            if (objek instanceof MejaKursi) {
                mejaKursi = (MejaKursi) objek;
                break;
            }
        }
        if (mejaKursi != null) {
            Posisi posisiMejaKursi = mejaKursi.getPosisi();
            System.out.println("Sim bermain game di meja kursi pada posisi " + posisiMejaKursi);
        } else {
            System.out.println("Sim tidak menemukan meja kursi di ruangan ini untuk bermain game");
        }
    }
}