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
    private Object objekDipakai;
    private int waktuKerjaSim; // waktu total sim kerja buat ngecek gajian
    private Posisi posisiSim;
    private Rumah rumahSim;
    private Ruangan ruanganSim;
    private HashMap<String, Integer> activities;
    private int waktuTerakhirSimTidur;
    private int waktuTerakhirSimMakan;
    private int durasiSimBerkunjung;
    private int durasiTotalTidur;
    private boolean cekBelumEEKsetelahMakan;
    private int waktuGantiKerja;
    private Point koordinatPosisiSim; // koordinat rumah

    public Sim(String namaLengkap) {
        this.namaLengkap = namaLengkap;
        this.pekerjaan = getRandomPekerjaan();
        this.uang = 1000000;
        this.kekenyangan = 80;
        this.mood = 80;
        this.kesehatan = 80;
        this.waktuKerjaSim = 0;
        inventory = new Inventory();
        this.status = "idle";
        activities = new HashMap<String, Integer>();
        waktuTerakhirSimTidur = 0;
        waktuTerakhirSimMakan = 0;
        durasiSimBerkunjung = 0;
        cekBelumEEKsetelahMakan = false;
        durasiTotalTidur = 0;
        waktuGantiKerja = 0;

    }

    public void printStatus() {
        System.out.print("\n");
        System.out.println("Berikut info dari SIM " + namaLengkap + ":");
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
        if (inventory.size() > 0) {
            HashMap<String, Integer> inventoryTemp = new HashMap<String, Integer>();
            for (Map.Entry<Object, Integer> entry : getInventory().getInventory().entrySet()) {
                Objek objek = (Objek) entry.getKey();
                String kategori;
                if (objek.getClass().getSimpleName().equals("Bahan_Makanan")) {
                    kategori = "Bahan Makanan";
                } else if (objek.getClass().getSuperclass().getSimpleName().equals("Non_Makanan")) {
                    kategori = "Non Makanan";
                } else {
                    kategori = "Masakan";
                }
                if (inventoryTemp.containsKey(objek.getNamaObjek() + " - " + kategori)) {
                    inventoryTemp.replace(
                            objek.getNamaObjek() + " - " + kategori,
                            inventoryTemp.get(
                                    objek.getNamaObjek() + " - " + kategori)
                                    + 1);
                } else {
                    inventoryTemp.put(objek.getNamaObjek() + " - " + kategori,
                            1);
                }

            }
            System.out.print("\n");
            System.out.println("Your Inventory:");
            int i = 1;
            for (String objek : inventoryTemp.keySet()) {
                int currentQuantity = inventoryTemp.get(objek);
                System.out.println(i + ". " + objek + " ( jumlah: " + currentQuantity + " )");
                i++;
            }
        } else {
            System.out.print("\n");
            System.out.println("Inventory kosong");
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

    public HashMap<String, Integer> getActivities() {
        return activities;
    }

    public Object getObjekDipakai() {
        return objekDipakai;
    }

    public Ruangan getRuanganSim() {
        return ruanganSim;
    }

    public Point getKoordinatPosisiSim() {
        return koordinatPosisiSim;
    }

    public void setKoordinatPosisiSim(Point point) {
        koordinatPosisiSim = point;
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
        if (this.kekenyangan + kekenyangan > 100) {
            this.kekenyangan = 100;
        } else if (this.kekenyangan + kekenyangan < 0) {
            this.kekenyangan = 0;
        } else {
            this.kekenyangan += kekenyangan;
        }
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        if (this.mood + mood > 100) {
            this.mood = 100;
        } else if (this.mood + mood < 0) {
            this.mood = 0;
        } else {
            this.mood += mood;
        }
    }

    public int getKesehatan() {
        return kesehatan;
    }

    public void setKesehatan(int kesehatan) {
        if (this.kesehatan + kesehatan > 100) {
            this.kesehatan = 100;
        } else if (this.kesehatan + kesehatan < 0) {
            this.kesehatan = 0;
        } else {
            this.kesehatan += kesehatan;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setObjekDipakai(Object objek) {
        objekDipakai = objek;
    }

    public void setStatus(String status) {
        if (this.status.equals("idle")) {
            this.status = status;
            return;
        } else if (status.equals("idle")) {
            if (activities.size() > 0) {
                if (this.status.contains("upgrade rumah")) {
                    this.status = "upgrade rumah";
                    return;
                } else if (this.status.contains("beli barang")) {
                    this.status = "beli barang";
                    return;
                } else {
                    return;
                }
            } else {
                this.status = status;
                return;
            }
        } else if (this.status.contains("beli barang") && status.equals("beli barang")) {
            return;
        } else if (this.status.contains("upgrade rumah") && status.equals("upgrade rumah")) {
            return;
        } else {
            this.status = this.status.concat(", " + status);
        }
    }

    public boolean cekMood() {
        if (mood <= 0) {
            // tindakan mati karna depresi
            System.out.println("\nSim ini sudah mati karena depresi.");
            return false; // mati

        } else {
            return true;
        }
        // implementasi lainnya
    }

    public boolean cekKesehatan() {
        if (kesehatan <= 0) {
            // tindakan mati karna sakit
            System.out.println("\nSim ini sudah mati karena sakit.");
            return false;

        }
        return true;
        // implementasi lainnya
    }

    public boolean cekKekenyangan() {
        if (kekenyangan <= 0) {
            // tindakan mati karna kelaparan
            System.out.println("\nSim ini sudah mati karena kelaparan.");
            return false;
        }
        return true;
        // implementasi lainnya
    }

    public void kerja(World world) {
        System.out.println("Mau kerja");
        if (world.getTime().getTotalDetik() - waktuGantiKerja >= 720 || waktuGantiKerja == 0) {
            while (true) {
                try {
                    System.out.print("Masukkan durasi kerja dalam satuan detik (kelipatan 120) : ");
                    Scanner scanner = new Scanner(System.in);
                    int durasi = scanner.nextInt();
                    System.out.print("\n");
                    if (durasi % 120 == 0 && durasi > 0) {
                        System.out.println("Sim sedang bekerjaaaa....");
                        setStatus("kerja");
                        for (int i = 1; i <= durasi; i++) {
                            world.getTime().delayWaktu(1);
                            world.getTime().updateWaktu(1);
                            System.out.print("kerja...");
                            waktuKerjaSim += 1;
                            if (i % 30 == 0) {
                                setKekenyangan(-(10));
                                setMood(-(10));
                            }
                            if (waktuKerjaSim % 240 == 0) {
                                uang += pekerjaan.getGaji();
                                System.out.println("\nSim mendapatkan uang sebesar " + pekerjaan.getGaji());
                                System.out.println("Uang sim menjadi : " + uang);
                            }
                        }
                        System.out.print("\n");
                        System.out.println("sim sudah bekerja selama " + waktuKerjaSim + " detik.");
                        // CEK MATI
                        if (!(cekMood())) {
                            // MATI
                            world.removeSimDanRumah(this);
                            Game.changeSim();
                            if (world.getListSim().size() > 0) {
                                System.out.println("Klik enter 2x");
                            }
                        } else if (!(cekKesehatan())) {
                            world.removeSimDanRumah(this);
                            Game.changeSim();
                            if (world.getListSim().size() > 0) {
                                System.out.println("Klik enter 2x");
                            }
                        } else if (!(cekKekenyangan())) {
                            world.removeSimDanRumah(this);
                            Game.changeSim();
                            if (world.getListSim().size() > 0) {
                                System.out.println("Klik enter 2x");
                            }
                        } else {
                            System.out.println("Sudah selesai kerja");
                            setStatus("idle");
                        }
                        break;
                    } else {
                        System.out.println("Masukkan input yang benar");
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("Input durasi salah");
                }

            }
        } else {
            System.out.println("Sim baru saja ganti pekerjaan, Tunggu 1 hari dulu baru bisa kerja lagi");
        }

    }

    public void olahraga(World world) {
        while (true) {
            try {
                System.out.print("Masukkan durasi olahraga dalam satuan detik (kelipatan 20) : ");
                Scanner scanner = new Scanner(System.in);
                int durasi = scanner.nextInt();
                if (durasi % 20 == 0 && durasi > 0) {
                    System.out.println("Sim sedang olahraga....");
                    setStatus("olahraga");
                    world.getTime().delayWaktu(durasi);
                    setKekenyangan(-(5 * (durasi / 20)));
                    setKesehatan(5 * (durasi / 20));
                    setMood(10 * (durasi / 20));
                    // CEK MATI
                    if (!(cekKekenyangan())) {
                        world.removeSimDanRumah(this);
                        Game.changeSim();
                        if (world.getListSim().size() > 0) {
                            System.out.println("Klik enter 2x");
                        }
                    } else {
                        System.out.println("Sudah selesai olahraga");
                        setStatus("idle");
                    }
                    break;
                } else {
                    System.out.println("Masukkan input yang benar");
                }

            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Input durasi salah");
            }
        }
    }

    public void tidur(Ruangan ruangan, Sim sim, World world) {
        SingleBed singleBed = null;
        QueenSizeBed queenSizeBed = null;
        KingSizeBed kingSizeBed = null;
        Object object = sim.getObjekDipakai();

        if (object instanceof SingleBed) {
            singleBed = (SingleBed) object;
        } else if (object instanceof QueenSizeBed) {
            queenSizeBed = (QueenSizeBed) object;
        } else if (object instanceof KingSizeBed) {
            kingSizeBed = (KingSizeBed) object;
        } else if (object instanceof String && ((String) object).contains("Kasur")) {
            Map<String, Non_Makanan> listObjek = ruangan.getListObjek();
            for (Map.Entry<String, Non_Makanan> entry : listObjek.entrySet()) {
                Non_Makanan objek = entry.getValue();
                if (objek instanceof SingleBed) {
                    singleBed = (SingleBed) objek;
                    break;
                } else if (object instanceof QueenSizeBed) {
                    queenSizeBed = (QueenSizeBed) object;
                    break;
                } else if (object instanceof KingSizeBed) {
                    kingSizeBed = (KingSizeBed) object;
                    break;
                }
            }
        }

        if (singleBed != null || queenSizeBed != null || kingSizeBed != null) {
            if (singleBed != null) {
                Scanner scanner = new Scanner(System.in);
                int inputDurasiTidur;
                while (true) {
                    System.out.print("Masukkan berapa lama sim tidur (dalam menit) : ");
                    inputDurasiTidur = scanner.nextInt();
                    try {
                        if (inputDurasiTidur >= 3) {
                            break;
                        } else {
                            System.out.println("Masukkan durasi tidur minimal 3 menit");

                        }
                    } catch (Exception e) {
                        System.out.println("Input salah");
                        break;
                    }
                }
                int durasiTidur = inputDurasiTidur * 60;
                String posisi = singleBed.getPosisi().cetakPosisi();
                System.out.println("Sim sedang tidur pada posisi " + posisi);
                System.out.println("Sim sedang Tidur di Single Bed....");
                for (int i = 0; i < durasiTidur; i++) {
                    System.out.print("zzz...");
                    world.getTime().delayWaktu(1);
                    world.getTime().updateWaktu(1);
                    durasiTotalTidur++;
                    if (durasiTotalTidur > 0 && durasiTotalTidur % 240 == 0) {
                        setMood(30);
                        setKesehatan(20);
                    }
                }
                System.out.print("\n");
            } else if (queenSizeBed != null) {
                int inputDurasiTidur;
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    try {
                        System.out.print("Masukkan berapa lama sim tidur : ");
                        inputDurasiTidur = scanner.nextInt();
                        if (inputDurasiTidur >= 3) {
                            break;
                        } else {
                            System.out.println("Masukkan durasi tidur lebih dari 3 menit");
                        }
                    } catch (Exception e) {
                        System.out.println("Masukkan durasi tidur yang benar");
                    }
                }
                int durasiTidur = inputDurasiTidur * 60;
                String posisi = queenSizeBed.getPosisi().cetakPosisi();
                System.out.println("Sim sedang tidur pada posisi " + posisi);
                System.out.println("Sim sedang Tidur di Queen Size Bed....");
                for (int i = 0; i < durasiTidur; i++) {
                    System.out.print("zzz...");
                    world.getTime().delayWaktu(1);
                    world.getTime().updateWaktu(1);
                    durasiTotalTidur++;
                    if (durasiTotalTidur > 0 && durasiTotalTidur % 240 == 0) {
                        setMood(30);
                        setKesehatan(20);
                    }
                }
                System.out.print("\n");

            } else if (kingSizeBed != null) {
                int inputDurasiTidur;
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    try {
                        System.out.print("Masukkan berapa lama sim tidur dalam menit : ");
                        inputDurasiTidur = scanner.nextInt();
                        if (inputDurasiTidur >= 3) {
                            break;
                        } else {
                            System.out.println("Masukkan durasi tidur lebih dari 3 menit");
                        }
                    } catch (Exception e) {
                        System.out.println("Masukkan durasi tidur yang benar");
                    }
                }
                int durasiTidur = inputDurasiTidur * 60;
                String posisi = kingSizeBed.getPosisi().cetakPosisi();
                System.out.println("Sim sedang tidur pada posisi " + posisi);
                System.out.println("Sim sedang Tidur di Queen Size Bed....");
                for (int i = 0; i < durasiTidur; i++) {
                    System.out.print("zzz...");
                    world.getTime().delayWaktu(1);
                    world.getTime().updateWaktu(1);
                    durasiTotalTidur++;
                    if (durasiTotalTidur > 0 && durasiTotalTidur % 240 == 0) {
                        setMood(30);
                        setKesehatan(20);
                    }
                }
                System.out.print("\n");
            }
            waktuTerakhirSimTidur = world.getTime().getTotalDetik();
            if (!(cekKekenyangan())) {
                world.removeSimDanRumah(this);
                Game.changeSim();
                if (world.getListSim().size() > 0) {
                    System.out.println("Klik enter 2x");
                }
            } else {
                System.out.println("Sudah selesai Tidur");
                setStatus("idle");
            }
        } else {
            System.out.println("Sim tidak sedang berada di Kasur mana pun Tidur di Kasur");
        }
    }

    public void cekEfekTidakTidur(World world) {
        int cekWaktu = world.getTime().getTotalDetik() - waktuTerakhirSimTidur;
        if (((cekWaktu > 600 || cekWaktu % 600 == 0) && cekWaktu > 0)) {
            System.out.println("Sim telah tidak tidur selama 10 menit");
            waktuTerakhirSimTidur = world.getTime().getTotalDetik();
            setKesehatan(-5);
            setMood(-5);
        }
        if (!(cekMood())) {
            // MATI
            world.removeSimDanRumah(this);
            Game.changeSim();
            if (world.getListSim().size() > 0) {
                System.out.println("Klik enter 2x");
            }
        } else if (!(cekKesehatan())) {
            world.removeSimDanRumah(this);
            Game.changeSim();
            if (world.getListSim().size() > 0) {
                System.out.println("Klik enter 2x");
            }
        } else if (!(cekKekenyangan())) {
            world.removeSimDanRumah(this);
            Game.changeSim();
            if (world.getListSim().size() > 0) {
                System.out.println("Klik enter 2x");
            }
        }
    }

    public void makan(World world) {
        int kenyang;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nomor masakan yang ingin dimakan : ");
        String object = scanner.nextLine();
        if (object.equals("1") || object.equals("2") || object.equals("3") || object.equals("4")
                || object.equals("5")) {
            if (object.equals("1")) {
                object = "Nasi Ayam";
            } else if (object.equals("2")) {
                object = "Nasi Kari";
            } else if (object.equals("3")) {
                object = "Susu Kacang";
            } else if (object.equals("4")) {
                object = "Tumis Sayur";
            } else if (object.equals("5")) {
                object = "Bistik";
            }
            Masakan masakan = new Masakan(object);
            kenyang = masakan.getValueKekenyangan();
            ArrayList<Object> listObjek = new ArrayList<Object>();
            ArrayList<String> listStringInventory = new ArrayList<>();
            for (Map.Entry<Object, Integer> entry : inventory.getInventory().entrySet()) {
                Objek objek = (Objek) entry.getKey();
                listStringInventory.add(objek.getNamaObjek());
                listObjek.add(entry.getKey());
            }

            if (listStringInventory.contains(object)) {
                for (int i = 0; i < listStringInventory.size(); i++) {
                    if (listStringInventory.get(i).equals(object)) {
                        // System.out.println("HEHEH");
                        inventory.getInventory().remove((Object) listObjek.get(i));
                        break;
                    }
                }
                System.out.println("Sim sedang makan");
                for (int i = 0; i < 30; i++) {
                    world.getTime().delayWaktu(1);
                    world.getTime().updateWaktu(1);
                    System.out.print("nyam...");
                }
                System.out.println("\nSim sudah selesai makan");
                if (!cekBelumEEKsetelahMakan) {
                    waktuTerakhirSimMakan = world.getTime().getTotalDetik();
                    cekBelumEEKsetelahMakan = true;
                }
                setKekenyangan(kenyang);
            } else {
                System.out.println("Makanan tidak tersedia di inventory, silahkan masak dulu");
            }

        } else if (object.equals("6") || object.equals("7") || object.equals("8")
                || object.equals("9") || object.equals("10") || object.equals("11") || object.equals("12")
                || object.equals("13")) {
            if (object.equals("6")) {
                object = "Nasi";
            } else if (object.equals("7")) {
                object = "Kentang";
            } else if (object.equals("8")) {
                object = "Ayam";
            } else if (object.equals("9")) {
                object = "Sapi";
            } else if (object.equals("10")) {
                object = "Wortel";
            } else if (object.equals("11")) {
                object = "Bayam";
            } else if (object.equals("12")) {
                object = "Kacang";
            } else if (object.equals("13")) {
                object = "Susu";
            }
            Bahan_Makanan makanan = new Bahan_Makanan(object);
            kenyang = makanan.getValueKekenyangan();
            ArrayList<Object> listObjek = new ArrayList<Object>();
            ArrayList<String> listStringInventory = new ArrayList<>();
            for (Map.Entry<Object, Integer> entry : inventory.getInventory().entrySet()) {
                Objek objek = (Objek) entry.getKey();
                listStringInventory.add(objek.getNamaObjek());
                listObjek.add(entry.getKey());
            }

            if (listStringInventory.contains(object)) {
                for (int i = 0; i < listStringInventory.size(); i++) {
                    if (listStringInventory.get(i).equals(object)) {
                        // System.out.println("HEHEH");
                        inventory.getInventory().remove((Object) listObjek.get(i));
                        break;
                    }
                }
                System.out.println("Sim sedang makan");
                for (int i = 0; i < 30; i++) {
                    world.getTime().delayWaktu(1);
                    world.getTime().updateWaktu(1);
                    System.out.print("nyam...");
                }
                System.out.println("\nSim sudah selesai makan");
                if (!cekBelumEEKsetelahMakan) {
                    waktuTerakhirSimMakan = world.getTime().getTotalDetik();
                    cekBelumEEKsetelahMakan = true;
                }
                setKekenyangan(kenyang);
            } else {
                System.out.println("Makanan tidak tersedia di inventory");
            }
        } else {
            System.out.println("Masakan tidak ditemukan");

        }
    }

    public void memasak(World world, Sim sim) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Pilih nomor masakan yang ingin dimasak : ");
        String namaMasakan = scanner.nextLine();
        Masakan masakan;
        while (true) {
            if (namaMasakan.equals("1")) {
                masakan = new Masakan("Nasi Ayam");
                break;
            } else if (namaMasakan.equals("2")) {
                masakan = new Masakan("Nasi Kari");
                break;
            } else if (namaMasakan.equals("3")) {
                masakan = new Masakan("Susu Kacang");
                break;
            } else if (namaMasakan.equals("4")) {
                masakan = new Masakan("Tumis Sayur");
                break;
            } else if (namaMasakan.equals("5")) {
                masakan = new Masakan("Bistik");
                break;
            } else {
                System.out.println("Masukan nomor yang benar");
            }
        }
        boolean semuaBahanTersedia = cekBahanMasak(masakan, sim);
        int durasiMasak;
        if (semuaBahanTersedia) {
            System.out.println("Memasak " + masakan.getNamaObjek() + "...");
            durasiMasak = (int) (1.5 * masakan.getValueKekenyangan());
            for (int i = 0; i < durasiMasak; i++) {
                world.getTime().delayWaktu(1);
                world.getTime().updateWaktu(1);
                System.out.print("sreng...");
            }
            System.out.print("\n");
            setMood(10);
            inventory.addInventory(masakan);
            HashMap<Object, Integer> mapCek = new HashMap<Object, Integer>();
            mapCek = sim.getInventory().getInventory();
            ArrayList<String> listCek = new ArrayList<String>();
            ArrayList<Object> listObjek = new ArrayList<Object>();
            ArrayList<String> listCekInventory = new ArrayList<String>();
            for (int i = 0; i < masakan.getBahan().size(); i++) {
                listCek.add(masakan.getBahan().get(i).getNamaObjek());
            }
            System.out.println("Masak sudah selesai, masakan masuk inventory");
            for (Map.Entry<Object, Integer> entry : mapCek.entrySet()) {
                Objek objek = (Objek) entry.getKey();
                listCekInventory.add(objek.getNamaObjek());
                listObjek.add(entry.getKey());
            }
            for (int i = 0; i < listCekInventory.size(); i++) {
                if (listCek.contains(listCekInventory.get(i))) {
                    // System.out.println("HEHEH");
                    sim.getInventory().getInventory().remove((Object) listObjek.get(i));
                    listCek.remove(listCekInventory.get(i));
                }
            }
        } else {
            System.out.println("Bahan makanan tidak cukup!");
        }
    }

    public boolean cekBahanMasak(Masakan masakan, Sim sim) {
        ArrayList<String> listCek = new ArrayList<>();
        ArrayList<String> listInventorySim = new ArrayList<>();
        for (Map.Entry<Object, Integer> entry : sim.getInventory().getInventory().entrySet()) {
            Objek objek = (Objek) entry.getKey();
            listInventorySim.add(objek.getNamaObjek());
        }
        for (int i = 0; i < masakan.getBahan().size(); i++) {
            listCek.add(masakan.getBahan().get(i).getNamaObjek());
        }
        for (int i = 0; i < listInventorySim.size(); i++) {
            if (listCek.contains(listInventorySim.get(i))) {
                listCek.remove(listInventorySim.get(i));
            }
        }
        if (listCek.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public void berkunjung(Rumah now, Rumah tujuan, World world) {
        int waktuTempuh;
        // this.getRumah() maksudnya buat rumah sim yang sedang dimainkan
        if (now.getPemilikRumah().getNamaLengkap().equals(tujuan.getPemilikRumah().getNamaLengkap())) {
            waktuTempuh = 0;
            System.out.println("Sim sudah berada di rumah tersebut!");
        } else {
            // getRumah yang disini maksudnya rumah nya si sim yg dimainkan
            // kurang tau gmn buatnya jadi aku buat this.getRumah() aja dulu
            waktuTempuh = (int) Math
                    .round(Math.sqrt(Math.pow(tujuan.getKoordinat().getX() - koordinatPosisiSim.getX(), 2)
                            + Math.pow(tujuan.getKoordinat().getY() - koordinatPosisiSim.getY(), 2)));
            System.out.println("Sim dalam perjalanan....");
            for (int i = 0; i < waktuTempuh; i++) {
                world.getTime().delayWaktu(1);
                System.out.print("brum...");
                world.getTime().updateWaktu(1);
                durasiSimBerkunjung++;
            }
            System.out.println("\nSim sudah sampai");
            setObjekDipakai((String) "");
            if (durasiSimBerkunjung > 0 && durasiSimBerkunjung % 30 == 0) {
                setMood(10);
                setKekenyangan(-10);
            }
            // tujuan.masukRumah(now, tujuan.getKoordinat());
            setRumahSim(tujuan);
            setRuanganSim(tujuan.getRuangan("Ruang Utama"));
            setKoordinatPosisiSim(tujuan.getKoordinat());
            setStatus("idle");
            if (!(cekMood())) {
                // MATI
                world.removeSimDanRumah(this);
                Game.changeSim();
                if (world.getListSim().size() > 0) {
                    System.out.println("Klik enter 2x");
                }
            } else if (!(cekKesehatan())) {
                world.removeSimDanRumah(this);
                Game.changeSim();
                if (world.getListSim().size() > 0) {
                    System.out.println("Klik enter 2x");
                }
            } else if (!(cekKekenyangan())) {
                world.removeSimDanRumah(this);
                Game.changeSim();
                if (world.getListSim().size() > 0) {
                    System.out.println("Klik enter 2x");
                }
            }
        }
    }

    public void cekEfekTidakBuangAir(World world) {
        if (waktuTerakhirSimMakan > 0) {
            int cekWaktu = world.getTime().getTotalDetik() - waktuTerakhirSimMakan;
            if (((cekWaktu > 240 || cekWaktu % 240 == 0) && cekWaktu > 0)) {
                System.out.println("Sim terkena efek tidak buang air");
                waktuTerakhirSimMakan = world.getTime().getTotalDetik();
                setKesehatan(-5);
                setMood(-5);
            }
            if (!(cekMood())) {
                // MATI
                world.removeSimDanRumah(this);
                Game.changeSim();
                if (world.getListSim().size() > 0) {
                    System.out.println("Klik enter 2x");
                }
            } else if (!(cekKesehatan())) {
                world.removeSimDanRumah(this);
                Game.changeSim();
                if (world.getListSim().size() > 0) {
                    System.out.println("Klik enter 2x");
                }
            } else if (!(cekKekenyangan())) {
                world.removeSimDanRumah(this);
                Game.changeSim();
                if (world.getListSim().size() > 0) {
                    System.out.println("Klik enter 2x");
                }
            }
        }

    }

    public void buangAir(World world) {
        System.out.println("Sim sedang eek");
        for (int i = 0; i < 10; i++) {
            System.out.print("plung...");
            world.getTime().delayWaktu(1);
            world.getTime().updateWaktu(1);
        }
        setKekenyangan(-20);
        setMood(10);
        if (!(cekKekenyangan())) {
            world.removeSimDanRumah(this);
            Game.changeSim();
            if (world.getListSim().size() > 0) {
                System.out.println("\nKlik enter");
            }
        } else {
            System.out.println("\nSim sudah selesai eek");
            cekBelumEEKsetelahMakan = false;
            setStatus("idle");
        }

    }

    public void upgradeRumah(Rumah rumah, World world) {
        // biayaupgrade nya brp ya? blm tau ini masih ngasal dlu biayanya
        int biayaUpgrade = 15; // biaya upgrade untuk menambah satu ruangan

        // cek apakah sim memiliki cukup uang untuk upgrade
        if (uang < biayaUpgrade) {
            System.out.println("Maaf, uang anda tidak cukup untuk melakukan upgrade rumah.");
            return;
        }

        // tambahkan ruangan baru pada rumah
        // ini blm ada rumah
        rumah.addRuanganX(world);

        // kurangi uang sim sesuai biaya upgrade
        setUang(-biayaUpgrade);

        // tambahkan waktu upgrade ke waktu total Sim
        // ini blm di update ke waktu yg di world nnt
        waktuKerjaSim += 18 * 60;
        System.out.println("\nHOORAY...\nBershasil upgrade Rumah, Silakan tunggu 18 Menit");
    }

    public void beliBarang(Objek barang, World world) {
        Random random = new Random();
        int durasi = random.nextInt(30) + 1;
        if (barang instanceof Non_Makanan) {
            Non_Makanan nm = (Non_Makanan) barang;
            if (world.getCurrentSim().getUang() < nm.getHarga()) {
                System.out.println("Uang anda tidak cukup!");
                return;
            }

            // kurangi uang sim
            setUang(-(nm.getHarga()));
            System.out.print("\n");
            System.out.println("HOORAY...");
            System.out.println("Anda telah membeli " + nm.getNamaObjek() + " seharga " + nm.getHarga()
                    + " dengan durasi pengiriman " + durasi + " detik.");
        } else if (barang instanceof Bahan_Makanan) {
            Bahan_Makanan bahan = (Bahan_Makanan) barang;
            if (world.getCurrentSim().getUang() < bahan.getHarga()) {
                System.out.println("Uang anda tidak cukup!");
                return;
            }
            // kurangi uang sim
            setUang(-(bahan.getHarga()));
            System.out.print("\n");
            System.out.println("HOORAY...");
            System.out.println(
                    "Anda telah membeli " + bahan.getNamaObjek() + " seharga " + bahan.getHarga()
                            + " dengan durasi pengiriman " + durasi + " detik.");
        }

        Thread thread = new Thread(new Runnable() {
            public void run() {
                Thread.currentThread().setName("pembelian " + barang.getNamaObjek());
                String codeKey = "pembelian " + barang.getNamaObjek()
                        + (durasi * (System.currentTimeMillis() + random.nextInt()));
                activities.put(codeKey, durasi);
                if (barang instanceof Non_Makanan) {
                    Non_Makanan nm = (Non_Makanan) barang;
                    try {
                        for (int i = 0; i < durasi; i++) {
                            setStatus("beli barang");
                            Thread.sleep(1000);
                            world.getTime().updateWaktu(1);
                            activities.replace(codeKey, activities.get(codeKey) - 1);
                        }
                        world.getCurrentSim().getInventory().addInventory(nm);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (barang instanceof Bahan_Makanan) {
                    Bahan_Makanan bahan = (Bahan_Makanan) barang;
                    try {
                        for (int i = 0; i < durasi; i++) {
                            setStatus("beli barang");
                            Thread.sleep(1000);
                            world.getTime().updateWaktu(1);
                            activities.replace(codeKey, activities.get(codeKey) - 1);
                        }
                        world.getCurrentSim().getInventory().addInventory(bahan);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                activities.remove(codeKey);
                world.getCurrentSim().setStatus("idle");
            }
        });
        thread.start();

    }

    //
    public void pindahRuangan(Ruangan asal, Ruangan tujuan) {
        // Cek apakah tujuan ruangan sama dengan ruangan saat ini
        if (asal.getNamaRuangan().equals(tujuan.getNamaRuangan())) {
            System.out.println("\nYahh....");
            System.out.println("Aksi gagal, Anda sudah berada di ruangan tersebut.");
            return;
        }

        // Pindah ruangan dan update lokasi ruangan
        this.setRuanganSim(tujuan);
        System.out.println("\nHOORAY...");
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

    public String printWaktu(String string) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (!(string.codePointAt(i) >= 48 && string.codePointAt(i) <= 57)) {
                result.append(string.charAt(i));
            }
        }
        return result.toString();
    }

    public void lihatWaktu(Ruangan ruangan, Sim sim, World world) {
        Jam jam = null;
        Object object = sim.getObjekDipakai();

        if (object instanceof Jam) {
            jam = (Jam) object;
        } else if (object instanceof String && ((String) object).contains("Jam")) {
            Map<String, Non_Makanan> listObjek = ruangan.getListObjek();
            for (Map.Entry<String, Non_Makanan> entry : listObjek.entrySet()) {
                Non_Makanan objek = entry.getValue();
                if (objek instanceof Jam) {
                    jam = (Jam) objek;
                    break;
                }
            }
        }

        if (jam != null) {
            System.out.println("Hari ke : " + world.getTime().getHari() + ", Sisa waktu di hari ini : "
                    + world.getTime().getSisaWaktu() + " detik");
            if (activities.size() > 0) {
                for (Map.Entry<String, Integer> entry : activities.entrySet()) {
                    System.out.println("Sisa waktu " + printWaktu(entry.getKey()) + ": " + entry.getValue() + " detik");

                }
            }
        } else {
            System.out.println("Sim tidak sedang menggunakan jam untuk melihat waktu");
        }

    }

    public void berdoa(Ruangan ruangan, World world) {
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
            System.out.println("Sim berdoa di meja kursi pada posisi " + posisiMejaKursi.cetakPosisi());
            System.out.println("Sim sedang berdoa....");
            // world.getTime().delayWaktu(2);
            // world.getTime().updateWaktu(2);
            for(int i = 0; i < 2; i++){
                world.getTime().delayWaktu(1);
                world.getTime().updateWaktu(1);
                System.out.print("seperti biasa ya tuhan amin...");
            }
            System.out.print("\n");
            setStatus("idle");
        } else {
            System.out.println("Sim tidak menemukan meja kursi di ruangan ini");
        }
    }

    public void rapihinKasur(Ruangan ruangan, Sim sim, World world) {
        SingleBed singleBed = null;
        QueenSizeBed queenSizeBed = null;
        KingSizeBed kingSizeBed = null;
        Object object = sim.getObjekDipakai();

        if (object instanceof SingleBed) {
            singleBed = (SingleBed) object;
        } else if (object instanceof QueenSizeBed) {
            queenSizeBed = (QueenSizeBed) object;
        } else if (object instanceof KingSizeBed) {
            kingSizeBed = (KingSizeBed) object;
        } else if (object instanceof String && ((String) object).contains("Kasur")) {
            Map<String, Non_Makanan> listObjek = ruangan.getListObjek();
            for (Map.Entry<String, Non_Makanan> entry : listObjek.entrySet()) {
                Non_Makanan objek = entry.getValue();
                if (objek instanceof SingleBed) {
                    singleBed = (SingleBed) objek;
                    break;
                } else if (object instanceof QueenSizeBed) {
                    queenSizeBed = (QueenSizeBed) object;
                    break;
                } else if (object instanceof KingSizeBed) {
                    kingSizeBed = (KingSizeBed) object;
                    break;
                }
            }
        }

        if (singleBed != null || queenSizeBed != null || kingSizeBed != null) {
            if (singleBed != null) {
                String posisi = singleBed.getPosisi().cetakPosisi();
                System.out.println("Sim merapikan Single Bed pada posisi " + posisi);
                System.out.println("Sim sedang merapikan Single Bed....");
                for(int i = 0; i < 10; i++){
                    world.getTime().delayWaktu(1);
                    world.getTime().updateWaktu(1);
                    System.out.print("srett...");
                }
                System.out.print("\n");
            } else if (queenSizeBed != null) {
                String posisi = queenSizeBed.getPosisi().cetakPosisi();
                System.out.println("Sim merapikan Queen Size Bed pada posisi " + posisi);
                System.out.println("Sim sedang merapikan Queen Size Bed....");
                for(int i = 0; i < 10; i++){
                    world.getTime().delayWaktu(1);
                    world.getTime().updateWaktu(1);
                    System.out.print("srett...");
                }
                System.out.print("\n");
            } else if (kingSizeBed != null) {
                String posisi = kingSizeBed.getPosisi().cetakPosisi();
                System.out.println("Sim merapikan Queen Size Bed pada posisi " + posisi);
                System.out.println("Sim sedang merapikan Queen Size Bed....");
                world.getTime().delayWaktu(10);
                world.getTime().updateWaktu(10);
                for(int i = 0; i < 10; i++){
                    world.getTime().delayWaktu(1);
                    world.getTime().updateWaktu(1);
                    System.out.print("srett...");
                }
                System.out.print("\n");
            }

            setMood(5);
            setKekenyangan(-10);
            if (!(cekKekenyangan())) {
                world.removeSimDanRumah(this);
                Game.changeSim();
                if (world.getListSim().size() > 0) {
                    System.out.println("Klik enter 2x");
                }
            } else {
                System.out.println("Sudah selesai Merapikan Kasur");
                setStatus("idle");
            }
        } else {
            System.out.println("Sim tidak sedang berada di Kasur mana pun untuk merapikan Kasur");
        }
    }

    public void meditasi(World world) {
        if (kekenyangan >= 5) {
            System.out.println("SIM sedang meditasi...");
            setKekenyangan(-5);
            setMood(5);

            for(int i = 0; i < 10; i++){
                world.getTime().delayWaktu(1);
                world.getTime().updateWaktu(1);
                System.out.print("namaste...");
            }
            System.out.print("\n");
            if (!(cekMood())) {
                // MATI
                world.removeSimDanRumah(this);
                Game.changeSim();
                if (world.getListSim().size() > 0) {
                    System.out.println("Klik enter 2x");
                }
            } else if (!(cekKesehatan())) {
                world.removeSimDanRumah(this);
                Game.changeSim();
                if (world.getListSim().size() > 0) {
                    System.out.println("Klik enter 2x");
                }
            } else if (!(cekKekenyangan())) {
                world.removeSimDanRumah(this);
                Game.changeSim();
                if (world.getListSim().size() > 0) {
                    System.out.println("Klik enter 2x");
                }
            } else {
                System.out.println("Sudah selesai meditasi");
                setStatus("idle");
            }

        } else {
            System.out.println("SIM kekurangan energi untuk meditasi");
        }
    }

    public void socialize(Sim sim, World world) {
        if (sim.getNamaLengkap().equals(this.getNamaLengkap())) {
            System.out.println("Sim tidak bisa socialize dengan dirinya sendiri");
        } else {
            if (sim.getRumahSim().getPemilikRumah().equals(this.getRumahSim().getPemilikRumah())) {
                if (sim.getRuanganSim().getNamaRuangan().equals(this.getRuanganSim().getNamaRuangan())) {
                    System.out.println("Sim socialize dengan " + sim.getNamaLengkap());
                    sim.setStatus("socialize");
                    this.setStatus("socialize");
                    System.out.println("Kedua SIM sedang ngobrol...");
                    for(int i = 0; i < 10; i++){
                        world.getTime().delayWaktu(1);
                        world.getTime().updateWaktu(1);
                        System.out.print("was wes wos...");
                    }
                    System.out.print("\n");
                    System.out.println("Udah selesai ngobrol");
                    sim.setStatus("idle");
                    this.setStatus("idle");
                    sim.setMood(5);
                    this.setMood(5);

                } else {
                    System.out.println("Sim tidak berada di ruangan yang sama");
                }
            } else {
                System.out.println("Sim tidak berada di rumah yang sama");
            }
        }

    }

    public void beresinKamarMandi(Ruangan ruangan, Sim sim, World world) {
        Toilet toilet = null;
        Object object = sim.getObjekDipakai();

        if (object instanceof Toilet) {
            toilet = (Toilet) object;
        } else if (object instanceof String && ((String) object).contains("Toilet")) {
            Map<String, Non_Makanan> listObjek = ruangan.getListObjek();
            for (Map.Entry<String, Non_Makanan> entry : listObjek.entrySet()) {
                Non_Makanan objek = entry.getValue();
                if (objek instanceof Toilet) {
                    toilet = (Toilet) objek;
                    break;
                }
            }
        }

        if (toilet != null) {
            String posisiToilet = toilet.getPosisi().cetakPosisi();
            System.out.println("Sim membersihkan toilet pada posisi " + posisiToilet);
            System.out.println("Sim sedang membersihkan toilet....");

            for(int i = 0; i < 10; i++){
                world.getTime().delayWaktu(1);
                world.getTime().updateWaktu(1);
                System.out.print("srekk...");
            }
            System.out.print("\n");

            setKekenyangan(-10);
            if (!(cekKekenyangan())) {
                world.removeSimDanRumah(this);
                Game.changeSim();
                if (world.getListSim().size() > 0) {
                    System.out.println("Klik enter 2x");
                }
            } else {
                System.out.println("Sudah selesai membersihkan toilet");
                setStatus("idle");
            }
            setMood(5);
        } else {
            System.out.println("Sim tidak sedang berada di Toilet untuk membersihkan Toilet");
        }
    }

    public void belajar(Ruangan ruangan, Sim sim, World world) {
        MejaKursi mejaKursi = null;
        Object object = sim.getObjekDipakai();

        if (object instanceof MejaKursi) {
            mejaKursi = (MejaKursi) object;
        } else if (object instanceof String && ((String) object).contains("Meja dan Kursi")) {
            Map<String, Non_Makanan> listObjek = ruangan.getListObjek();
            for (Map.Entry<String, Non_Makanan> entry : listObjek.entrySet()) {
                Non_Makanan objek = entry.getValue();
                if (objek instanceof MejaKursi) {
                    mejaKursi = (MejaKursi) objek;
                    break;
                }
            }
        }

        if (mejaKursi != null) {
            String posisiMejaKursi = mejaKursi.getPosisi().cetakPosisi();
            System.out.println("Sim belajar di meja kursi pada posisi " + posisiMejaKursi);
            System.out.println("Sim sedang belajar....");
            for(int i = 0; i < 10; i++){
                world.getTime().delayWaktu(1);
                world.getTime().updateWaktu(1);
                System.out.print("stress...");
            }
            System.out.print("\n");
            setKekenyangan(-10);
            setMood(5);
            if (!(cekMood())) {
                // MATI
                world.removeSimDanRumah(this);
                Game.changeSim();
                if (world.getListSim().size() > 0) {
                    System.out.println("Klik enter 2x");
                }
            } else if (!(cekKesehatan())) {
                world.removeSimDanRumah(this);
                Game.changeSim();
                if (world.getListSim().size() > 0) {
                    System.out.println("Klik enter 2x");
                }
            } else if (!(cekKekenyangan())) {
                world.removeSimDanRumah(this);
                Game.changeSim();
                if (world.getListSim().size() > 0) {
                    System.out.println("Klik enter 2x");
                }
            } else {
                System.out.println("Sim sudah selesai belajar");
                setStatus("idle");
            }
        } else {
            System.out.println("Sim tidak sedang berada di Meja Kursi untuk belajar");
        }
    }

    public void ngegame(Ruangan ruangan, Sim sim, World world) {
        MejaKursi mejaKursi = null;
        Object object = sim.getObjekDipakai();

        if (object instanceof MejaKursi) {
            mejaKursi = (MejaKursi) object;
        } else if (object instanceof String && ((String) object).contains("Meja dan Kursi")) {
            Map<String, Non_Makanan> listObjek = ruangan.getListObjek();
            for (Map.Entry<String, Non_Makanan> entry : listObjek.entrySet()) {
                Non_Makanan objek = entry.getValue();
                if (objek instanceof MejaKursi) {
                    mejaKursi = (MejaKursi) objek;
                    break;
                }
            }
        }

        if (mejaKursi != null) {
            String posisiMejaKursi = mejaKursi.getPosisi().cetakPosisi();
            System.out.println("Sim bermain game di meja kursi pada posisi " + posisiMejaKursi);
            System.out.println("Sim sedang ngegame....");

            for(int i = 0; i < 10; i++){
                world.getTime().delayWaktu(1);
                world.getTime().updateWaktu(1);
                System.out.print("gg gemink...");
            }
            System.out.print("\n");
            setMood(5);
            setStatus("idle");
            System.out.println("Sim sudah selesai main game");
        } else {
            System.out.println("Sim tidak menemukan meja kursi di ruangan ini untuk bermain game");
        }
    }

    public void printDaftarPekerjaan() {
        System.out.println("Daftar pekerjaan yang tersedia: ");
        System.out.println("1. Badut Sulap (Gaji: 15)");
        System.out.println("2. Koki (Gaji: 30)");
        System.out.println("3. Polisi (Gaji: 35)");
        System.out.println("4. Programmer (Gaji: 45)");
        System.out.println("5. Dokter (Gaji: 50)");
    }

    public void gantiPekerjaan(Pekerjaan pekerjaan, World world) {
        if (pekerjaan.getNamaPekerjaan().equals(this.pekerjaan.getNamaPekerjaan())) {
            System.out.println("Gagal ganti pekerjaan. Itu adalah pekerjaan Sim yang sekarang");
        } else {
            if (uang - (pekerjaan.getGaji() / 2) < 0) {
                System.out.println("Uang tidak cukup untuk membayar 1/2 gaji pekerjaan");
                System.out.println("Sim gagal ganti pekerjaan");
            } else {
                if (waktuKerjaSim >= 720) {
                    this.pekerjaan = pekerjaan;
                    waktuKerjaSim = 0;
                    waktuGantiKerja = world.getTime().getTotalDetik();
                    setUang(-(pekerjaan.getGaji() / 2));
                    System.out.println("Berhasil ganti pekerjaan menjadi" + pekerjaan.getNamaPekerjaan());
                } else {
                    System.out.println("Belum bisa ganti pekerjaan, minimal kerja dulu 12 menit");
                }
            }

        }
    }

    public void setUang(int nominal) {
        if (uang + nominal < 0) {
            uang = 0;
        } else {
            uang += nominal;
        }
    }
}
