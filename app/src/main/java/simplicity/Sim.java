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
    private String objekDipakai;
    private int waktuKerjaSim; // waktu total sim kerja buat ngecek gajian
    private Posisi posisiSim;
    private Rumah rumahSim;
    private Ruangan ruanganSim;

    public Sim(String namaLengkap) {
        this.namaLengkap = namaLengkap;
        this.pekerjaan = getRandomPekerjaan();
        this.uang = 100;
        this.kekenyangan = 80;
        this.mood = 80;
        this.kesehatan = 80;
        this.waktuKerjaSim = 0;
    }

    public void printStatus() {
        System.out.println("Nama Lengkap: " + namaLengkap);
        System.out.println("Pekerjaan: " + pekerjaan.getNamaPekerjaan());
        System.out.println("Uang: " + uang);
        System.out.println("Kekenyangan: " + kekenyangan);
        System.out.println("Mood: " + mood);
        System.out.println("Kesehatan: " + kesehatan);
        System.out.println("Status: " + status);
    }

    public void printInventory() {
        HashMap<Object, Integer> inventory = this.inventory.getInventory();
        for (Map.Entry<Object, Integer> entry : inventory.entrySet()) {
            Object object = entry.getKey();
            int quantity = entry.getValue();
            if (object instanceof Masakan) {
                Masakan masakan = (Masakan) object;
                System.out.println(masakan.getNamaObjek() + " (" + quantity + ")");
            } else if (object instanceof Bahan_Makanan) {
                Bahan_Makanan bahan = (Bahan_Makanan) object;
                System.out.println(bahan.getNamaObjek() + " (" + quantity + ")");
            } else if (object instanceof Non_Makanan) {
                Non_Makanan non_makanan = (Non_Makanan) object;
                System.out.println(non_makanan.getNamaObjek() + " (" + quantity + ")");
            }
        }
    }

    // pekerjaan harusnya dimasukin ke array dulu
    public Pekerjaan getRandomPekerjaan() {
        Pekerjaan[] daftarPekerjaan = Pekerjaan.getAllPekerjaan();
        Random random = new Random();
        int index = random.nextInt(daftarPekerjaan.length);
        return daftarPekerjaan[index];
    }

    public Posisi getPosisiSim() {
        return posisiSim;
    }

    public Rumah getRumahSim() {
        return rumahSim;
    }

    public String getObjekDipakai() {
        return objekDipakai;
    }

    public Ruangan getRuanganSim() {
        return ruanganSim;
    }

    public void setPosisiSim(Posisi posisiSim) {
        this.posisiSim = posisiSim;
    }

    public void setRumahSim(Rumah rumahSim) {
        this.rumahSim = rumahSim;
    }

    public void setRuanganSim(Ruangan ruanganSim) {
        this.ruanganSim = ruanganSim;
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

    public void setObjekDipakai(String objek) {
        objekDipakai = objek;
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

    public void makan() {
        inventory.listMasakan();
        inventory.listBahanMakanan();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan object yang ingin dimakan : ");
        Object object = scanner.next();
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
        scanner.close();
    }

    public void memasak() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan object yang ingin dimasak : ");
        String namaMasakan = scanner.next();
        Object object = (Object) namaMasakan;
        Masakan masakan = (Masakan) object;
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
            waktuKerjaSim = (int) (1.5 * masakan.getValueKekenyangan());
            kekenyangan += masakan.getValueKekenyangan();
            cekKekenyangan();
            mood += 10;
            cekMood();
            masakan.setIsAvailable(false);
            inventory.addInventory(masakan);
            Thread masakThread = new Thread(() -> {
                try {
                    Thread.sleep(waktuKerjaSim * 1000);
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
        scanner.close();
    }

    public void berkunjung(Rumah now, Rumah tujuan) {
        int waktuTempuh;
        // this.getRumah() maksudnya buat rumah sim yang sedang dimainkan
        if (now.getPemilikRumah().getNamaLengkap().equals(tujuan.getPemilikRumah().getNamaLengkap())) {
            waktuTempuh = 0;
        } else {
            // getRumah yang disini maksudnya rumah nya si sim yg dimainkan
            // kurang tau gmn buatnya jadi aku buat this.getRumah() aja dulu
            waktuTempuh = (int) Math
                    .round(Math.sqrt(Math.pow(tujuan.getKoordinat().getX() - now.getKoordinat().getX(), 2)
                            + Math.pow(tujuan.getKoordinat().getY() - now.getKoordinat().getY(), 2)));
            tujuan.masukRumah(now, tujuan.getKoordinat());
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

    public void upgradeRumah(Rumah rumah) {
        // biayaupgrade nya brp ya? blm tau ini masih ngasal dlu biayanya
        int biayaUpgrade = 50; // biaya upgrade untuk menambah satu ruangan

        // cek apakah sim memiliki cukup uang untuk upgrade
        if (uang < biayaUpgrade) {
            System.out.println("Maaf, uang anda tidak cukup untuk melakukan upgrade rumah.");
            return;
        }

        // tambahkan ruangan baru pada rumah
        // ini blm ada rumah
        rumah.addRuangan();

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
    public void pindahRuangan(Ruangan asal, Ruangan tujuan) {
        // Cek apakah tujuan ruangan sama dengan ruangan saat ini
        if (asal.getNamaRuangan().equals(tujuan.getNamaRuangan())) {
            System.out.println("Anda sudah berada di ruangan tersebut.");
            return;
        }

        // Pindah ruangan dan update lokasi ruangan
        this.setRuanganSim(tujuan);
        System.out.println("Anda telah pindah ke ruangan " + tujuan.getNamaRuangan() + ".");
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
        // blm tau nerapin2 waktu2 nya gmn
        Waktu currentTime = new Waktu(8, 0, 0); // asumsi waktu saat ini adalah jam 8 pagi
        Waktu totalAvailableTime = new Waktu(16, 0, 0); // asumsi waktu yang tersedia dari jam 8 pagi sampai jam 12
                                                        // malam
        Waktu remainingTime = new Waktu(totalAvailableTime.convert() - currentTime.convert()); // hitung waktu yang
                                                                                               // masih tersedia

        // tampilkan sisa waktu pada hari tersebut
        System.out.println("Sisa waktu hari ini: " + remainingTime.getDay() + " hari, " + remainingTime.getHour()
                + " jam, " + remainingTime.getMinute() + " menit, " + remainingTime.getSecond() + " detik");

        // hitung waktu yang dibutuhkan untuk setiap tindakan yang bisa ditinggalkan dan
        // kurangi dengan sisa waktu yang masih ada
        Waktu upgradeTime = new Waktu(0, 30, 0); // asumsi waktu yang dibutuhkan untuk upgrade rumah adalah 30 menit
        Waktu remainingUpgradeTime = new Waktu(remainingTime.convert() - upgradeTime.convert()); // hitung waktu yang
                                                                                                 // masih tersedia untuk
                                                                                                 // upgrade rumah

        // tampilkan sisa waktu yang masih ada untuk upgrade rumah
        System.out.println("Sisa waktu untuk upgrade rumah: " + remainingUpgradeTime.getHour() + " jam, "
                + remainingUpgradeTime.getMinute() + " menit, " + remainingUpgradeTime.getSecond() + " detik");

        // lanjutkan dengan menghitung waktu untuk tindakan-tindakan lainnya yang bisa
        // ditinggalkan
        // ...
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

    public void rapihinKasur(String object) {
        if (kekenyangan >= 5) {
            if (object.equals("Kasur Single")) {
                System.out.println("SIM merapikan kasur Single Bed");
            } else if (object.equals("Kasur Queen Size")) {
                System.out.println("SIM merapikan kasur Queen Size Bed");
            } else if (object.equals("Kasur King Size")) {
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
            System.out.println("SIM kekurangan energi untuk merapikan " + object);
        }
    }

    public void meditasi() {
        if (kekenyangan >= 5) {
            System.out.println("SIM meditasi");
            kekenyangan -= 5;
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("SIM kekurangan energi untuk meditasi");
        }
    }

    public void socialize(Sim sim) {
        if (sim == this) {
            System.out.println("Sim tidak bisa socialize dengan dirinya sendiri");
            return;
        }

        if (sim.getRumahSim() != this.getRumahSim()) {
            System.out.println("Sim tidak berada di rumah yang sama");
            return;
        }

        if (sim.getPosisiSim() != this.getPosisiSim()) {
            System.out.println("Sim tidak berada di posisi yang sama");
            return;
        }

        System.out.println("Sim socialize dengan " + sim.getNamaLengkap());
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
