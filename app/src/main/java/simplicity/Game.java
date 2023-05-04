package simplicity;

import java.util.Scanner;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Game {
    private static World world = new World(64, 64, null);
    private static boolean isPlaying = true;
    private static Scanner scanner = new Scanner(System.in);

    public static void showMenu2() {
        System.out.println("======= MENU UTAMA =======");
        System.out.println("Silakan pilih nomor menu : ");
        System.out.println("1. start");
        System.out.println("2. help");
        System.out.println("3. exit");
        System.out.print("\n");
    }

    public static void help2() {
        System.out.println("======= MENU UTAMA =======");
        System.out.println("Silakan pilih nomor menu : ");
        System.out.println("1. start");
        System.out.println("2. help");
        System.out.println("3. exit");
        System.out.print("\n");
    }

    public static void showMenu() {
        System.out.print("\n");
        System.out.println("=========== MENU ===========");
        System.out.println("Silakan pilih nomor menu : ");
        System.out.println("1. view sim info");
        System.out.println("2. view current location");
        System.out.println("3. view inventory");
        System.out.println("4. upgrade house");
        System.out.println("5. move room");
        System.out.println("6. edit room");
        System.out.println("7. add sim");
        System.out.println("8. change sim");
        System.out.println("9. list object");
        System.out.println("10. go to object");
        System.out.println("11. action");
        System.out.println("12. help");
        System.out.println("13. ganti kerja");
        System.out.println("14. exit");
    }

    public static void help() {
        showMenu();
    }

    public static void viewSimInfo() {
        world.getCurrentSim().printStatus();
    }

    public static void viewCurrentLocation() {
        Sim sim = world.getCurrentSim();
        String rumah = sim.getRumahSim().getPemilikRumah().getNamaLengkap();
        String ruang = sim.getRuanganSim().getNamaRuangan();
        System.out.print("\n");
        System.out.println("Posisi Sim " + sim.getNamaLengkap());
        System.out.println("Rumah : " + "Rumah " + rumah);
        System.out.println("Ruang : " + ruang);
    }

    public static void viewInventory() {
        Sim sim = world.getCurrentSim();
        sim.printInventory();
    }

    public static void upgradeHouse() {
        Sim sim = world.getCurrentSim();
        for (Rumah rumah : world.getListRumah()) {
            if (rumah.getPemilikRumah().getNamaLengkap().equals(sim.getNamaLengkap())) {
                sim.upgradeRumah(rumah);
                break;
            }
        }
    }

    public static void moveRoom() {
        int i = 1;
        int pilihanRuangan;
        Sim sim = world.getCurrentSim();
        System.out.println("Pilihan ruangan : ");
        for (Ruangan ruangan : sim.getRumahSim().getListRuangan()) {
            System.out.println(i + ". " + ruangan.getNamaRuangan());
            i++;
        }
        while (true) {
            try {
                System.out.print("Pilih nomor ruangan yang akan dituju : ");
                pilihanRuangan = scanner.nextInt();
                if (pilihanRuangan <= sim.getRumahSim().getListRuangan().size() && pilihanRuangan > 0) {
                    sim.pindahRuangan(sim.getRuanganSim(), sim.getRumahSim().getListRuangan().get(pilihanRuangan - 1));
                    break;
                } else {
                    System.out.println("Masukkan nomor yang sesuai");
                }
            } catch (Exception e) {
                System.out.println("Input tidak sesuai");
            }
        }
        scanner.nextLine();
    }

    public static void editRoom() {
        Sim sim = world.getCurrentSim();
        String idx;
        String nomorBarang;
        while (true) {
            System.out.println("Aksi yang tersedia :");
            System.out.println("1. Membeli barang");
            System.out.println("2. Pindah barang");
            System.out.println("3. Pasang barang");
            System.out.print("Pilih nomor :  ");
            idx = scanner.nextLine();
            if (idx.equals("1")) {
                System.out.println("\n1. Non Makanan");
                System.out.println("2. Bahan Makanan\n");
                System.out.print("Pilih nomor : ");
                nomorBarang = scanner.nextLine();
                if (nomorBarang.equals("1")) {
                    System.out.println("1. Kasur Single ( harga: 50 )");
                    System.out.println("2. Kasur Queen Size ( harga: 100 )");
                    System.out.println("3. Kasur King Size ( harga: 150 )");
                    System.out.println("4. Toilet ( harga: 50 )");
                    System.out.println("5. Kompor Gas ( harga: 100 )");
                    System.out.println("6. Kompor Listrik ( harga: 200 )");
                    System.out.println("7. Meja dan Kursi ( harga: 50 )");
                    System.out.println("8. Jam ( harga: 10)");
                    System.out.print("Masukkan nama barang yang ingin dibeli : ");
                    String namaBarang = scanner.nextLine();
                    if (namaBarang.equals("1")) {
                        sim.beliBarang(new SingleBed(), world);
                        break;
                    } else if (namaBarang.equals("2")) {
                        sim.beliBarang(new QueenSizeBed(), world);
                        break;
                    } else if (namaBarang.equals("3")) {
                        sim.beliBarang(new KingSizeBed(), world);
                        break;
                    } else if (namaBarang.equals("4")) {
                        sim.beliBarang(new Toilet(), world);
                        break;
                    } else if (namaBarang.equals("5")) {
                        sim.beliBarang(new KomporGas(), world);
                        break;
                    } else if (namaBarang.equals("6")) {
                        sim.beliBarang(new KomporListrik(), world);
                        break;
                    } else if (namaBarang.equals("7")) {
                        sim.beliBarang(new MejaKursi(), world);
                        break;
                    } else if (namaBarang.equals("8")) {
                        sim.beliBarang(new Jam(), world);
                        break;
                    } else {
                        System.out.println("Barang tidak tersedia");
                        break;
                    }
                } else if (nomorBarang.equals("2")) {
                    System.out.println("1. Nasi ( harga: 5 )");
                    System.out.println("2. Kentang ( harga: 3 )");
                    System.out.println("3. Ayam ( harga: 10 )");
                    System.out.println("4. Sapi ( harga: 12 )");
                    System.out.println("5. Wortel ( harga: 3 )");
                    System.out.println("6. Bayam ( harga: 3 )");
                    System.out.println("7. Kacang ( harga: 2 )");
                    System.out.println("8. Susu ( harga: 2 )");
                    System.out.print("\nPiliih nomor yang tersedia : ");
                    String namaBarang = scanner.nextLine();
                    if (namaBarang.equals("1")) {
                        sim.beliBarang(new Bahan_Makanan("Nasi"), world);
                        break;
                    } else if (namaBarang.equals("2")) {
                        sim.beliBarang(new Bahan_Makanan("Kentang"), world);
                        break;
                    } else if (namaBarang.equals("3")) {
                        sim.beliBarang(new Bahan_Makanan("Ayam"), world);
                        break;
                    } else if (namaBarang.equals("4")) {
                        sim.beliBarang(new Bahan_Makanan("Sapi"), world);
                        break;
                    } else if (namaBarang.equals("5")) {
                        sim.beliBarang(new Bahan_Makanan("Wortel"), world);
                        break;
                    } else if (namaBarang.equals("6")) {
                        sim.beliBarang(new Bahan_Makanan("Bayam"), world);
                        break;
                    } else if (namaBarang.equals("7")) {
                        sim.beliBarang(new Bahan_Makanan("Kacang"), world);
                        break;
                    } else if (namaBarang.equals("8")) {
                        sim.beliBarang(new Bahan_Makanan("Susu"), world);
                        break;
                    } else {
                        System.out.println("Barang tidak tersedia");
                        break;
                    }
                } else {
                    System.out.println("Input tidak sesuai!");
                    break;
                }
            } else if (idx.equals("2")) {
                // Ruangan ruangan = sim.getRuanganSim();
                // HashMap<String, Non_Makanan> kumpulanObjek = ruangan.getListObjek();
                // ArrayList<String> kumpulanNamaObjek = new ArrayList<>();
                // for (Entry<String, Non_Makanan> entry : kumpulanObjek.entrySet()) {
                // kumpulanNamaObjek.add(entry.getValue().getNamaObjek());
                // }
                // ruangan.printListObjek();
                // while (true) {
                // System.out.print("Masukkan nama barang yang ingin dipindah : ");
                // int x, y;
                // String namaBarang = scanner.nextLine();
                // if (kumpulanNamaObjek.contains(namaBarang)) {
                // System.out.println("Barang mau dipindahkan ke koordinat ");
                // System.out.println("titik X : ");
                // x = scanner.nextInt();
                // System.out.println("titik Y : ");
                // y = scanner.nextInt();
                // Point kiriAtas = new Point(x, y);
                // sim.getRuanganSim().moveObject(kumpulanObjek.get(namaBarang).getPosisi(),
                // kiriAtas,
                // isPlaying, sim.getInventory());
                // break;
                // } else {
                // System.out.println("Barang tidak ditemukan");
                // }
                // }
                boolean objekDitemukan = false;
                // Sim sim = world.getCurrentSim();
                Object objek;
                while (!objekDitemukan) {
                    sim.getRuanganSim().printListObjek();
                    System.out.print("\n");
                    System.out.println("Format Input : [Nama Objek][Koordinat Objek]");
                    System.out.println("Contoh : Jam(1,4)");
                    System.out.print("\n");
                    System.out.print("Pilih objek : ");
                    objek = scanner.nextLine();
                    if (sim.getRuanganSim().getListObjek().containsKey(objek)) {
                        try {
                            System.out.println("Barang mau dipindahkan ke koordinat ");
                            System.out.println("titik X : ");
                            int x = scanner.nextInt();
                            System.out.println("titik Y : ");
                            int y = scanner.nextInt();
                            Point kiriAtas = new Point(x, y);
                            sim.getRuanganSim().moveObject(sim.getRuanganSim().getListObjek().get(objek).getPosisi(),
                                    kiriAtas,
                                    isPlaying, sim.getInventory());

                            objekDitemukan = true;
                        } catch (Exception e) {
                            // TODO: handle exception
                            System.out.println("Hadeeeeeh...Input koordinat salah\nBarang gagal dipindahkan");
                            break;
                        }

                    } else {
                        System.out.println("Objek tidak ditemukan");
                        break;
                    }
                }
                scanner.nextLine();
                break;

            } else if (idx.equals("3")) {
                if (sim.getInventory().getInventory().size() > 0) {
                    HashMap<String, Integer> inventoryTemp = new HashMap<String, Integer>();
                    for (Entry<Object, Integer> entry : sim.getInventory().getInventory().entrySet()) {
                        if (entry.getKey() instanceof Non_Makanan) {
                            Objek objek = (Objek) entry.getKey();
                            if (inventoryTemp.containsKey(objek.getNamaObjek())) {
                                inventoryTemp.replace(objek.getNamaObjek(),
                                        inventoryTemp.get(objek.getNamaObjek()) + 1);
                            } else {
                                inventoryTemp.put(objek.getNamaObjek(), 1);
                            }
                        }
                    }
                    for (String objek : inventoryTemp.keySet()) {
                        int currentQuantity = inventoryTemp.get(objek);
                        System.out.println(objek + " - " + currentQuantity);
                    }
                    boolean objekDitemukan = false;
                    Object objek;
                    /*
                     * Masakan masakan = new Masakan(object);
                     * kenyang = masakan.getValueKekenyangan();
                     * ArrayList<Object> listObjek = new ArrayList<Object>();
                     * ArrayList<String> listStringInventory = new ArrayList<>();
                     * for (Map.Entry<Object, Integer> entry : inventory.getInventory().entrySet())
                     * {
                     * Objek objek = (Objek) entry.getKey();
                     * listStringInventory.add(objek.getNamaObjek());
                     * listObjek.add(entry.getKey());
                     * }
                     * 
                     * if (listStringInventory.contains(object)) {
                     * for (int i = 0; i < listStringInventory.size(); i++) {
                     * if (listStringInventory.get(i).equals(object)) {
                     * // System.out.println("HEHEH");
                     * inventory.getInventory().remove((Object) listObjek.get(i));
                     * break;
                     * }
                     * }
                     * System.out.println("Sim sedang makan");
                     * for (int i = 0; i < 30; i++) {
                     * world.getTime().delayWaktu(1);
                     * world.getTime().updateWaktu(1);
                     * System.out.print("nyam...");
                     * }
                     * System.out.println("\nSim sudah selesai makan");
                     * setKekenyangan(kenyang);
                     * } else {
                     * System.out.println("Makanan tidak tersedia di inventory, silahkan masak dulu"
                     * );
                     * }
                     */
                    while (!objekDitemukan) {
                        System.out.print("Pilih objek : ");
                        objek = scanner.nextLine();
                        if (inventoryTemp.containsKey(objek)) {
                            try {
                                System.out.println("Barang mau dipasang ke koordinat: ");
                                System.out.print("titik X : ");
                                int x = scanner.nextInt();
                                System.out.print("\ntitik Y : ");
                                int y = scanner.nextInt();
                                // Non_Makanan barangDipilih
                                Non_Makanan objekDipilih;
                                for (Entry<Object, Integer> entry : sim.getInventory().getInventory().entrySet()) {
                                    if (entry.getKey() instanceof Non_Makanan) {
                                        objekDipilih = (Non_Makanan) entry.getKey();
                                        if (objekDipilih.getNamaObjek().equals(objek)) {
                                            sim.getRuanganSim().addObject(objekDipilih, new Point(x, y), false,
                                                    sim.getInventory());
                                            break;
                                        } else {
                                        }
                                    }
                                }
                                objekDitemukan = true;
                            } catch (Exception e) {
                                // TODO: handle exception
                                System.out.println("Hadeeeeeh...Input koordinat salah\nBarang gagal dipasang");
                                break;
                            }

                        } else {
                            System.out.println("Objek tidak ditemukan");
                            break;
                        }
                    }
                } else {
                    System.out.println("Inventory kosong");
                    break;
                    // scanner.nextLine();
                }

                scanner.nextLine();
                break;
            } else {
                System.out.println("Masukkan nomor yang sesuai");
                break;
            }
        }
    }

    public static void addSim() {
        world.createSIMNext();
    }

    public static void changeSim() {
        try {
            if (world.getListSim().size() > 0) {
                System.out.println("Daftar Sim yang tersedia");
                int idx = 1;
                for (Sim sim : world.getListSim()) {
                    System.out.println(idx + ". " + sim.getNamaLengkap());
                    idx++;
                }

                while (true) {
                    System.out.print("Silahkan pilih nomor sim : ");
                    int simDipilih = scanner.nextInt();
                    scanner.nextLine();
                    if (simDipilih > 0 && simDipilih <= world.getListSim().size()) {
                        world.setCurrentSim(world.getListSim().get(simDipilih - 1));
                        break;
                    } else {
                        System.out.println("Pilih nomor yang sesuai!");
                    }
                }
                System.out.println("Berhasil mengganti Sim ke " + world.getCurrentSim().getNamaLengkap());
            } else {
                System.out.println("Tidak ada Sim yang tersedia!");
                // throw new RuntimeException("GAME OVER");
                System.out.println(">>>> GAME OVER <<<<");
                System.out.println("Keluar dari game...");
                System.exit(1);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Input salah, silakan coba lagi.");
            // scanner.nextLine();
        }
    }

    public static void listObject() {
        Sim sim = world.getCurrentSim();
        System.out.println("List Objek dalam ruangan : ");
        int idx = 1;
        for (Objek i : sim.getRuanganSim().getListObjek().values()) {
            System.out.println(idx + ". " + i.getNamaObjek());
            idx++;
        }
    }

    public static void goToObject() {
        boolean objekDitemukan = false;
        Sim sim = world.getCurrentSim();
        Object objek;
        sim.getRuanganSim().printListObjek();
        while (!objekDitemukan) {
            System.out.print("\n");
            System.out.println("Format Input : [Nama Objek][Koordinat Objek]");
            System.out.println("Contoh : Jam(1,4)");
            System.out.print("\n");
            System.out.print("Pilih objek : ");
            objek = scanner.nextLine();
            if (sim.getRuanganSim().getListObjek().containsKey(objek)) {
                sim.setObjekDipakai(objek);
                System.out.println("Sim " + sim.getNamaLengkap() + " menggunakan " + sim.getObjekDipakai());
                System.out.println(sim.getObjekDipakai().toString());
                objekDitemukan = true;
            } else {
                System.out.println("Objek tidak ditemukan");
            }
        }
    }

    public static void action() {
        Sim sim = world.getCurrentSim();
        System.out.println("==== Daftar Action : ====");
        System.out.println("1. Kerja");
        System.out.println("2. Olahraga");
        System.out.println("3. Tidur");
        System.out.println("4. Makan");
        System.out.println("5. Memasak");
        System.out.println("6. Berkunjung");
        System.out.println("7. Buang Air");
        System.out.println("8. Melihat Waktu");
        System.out.println("9. Berdoa");
        System.out.println("10. Meditasi");
        System.out.println("11. Merapihkan kasur");
        System.out.println("12. Socialize");
        System.out.println("13. Beresin kamar mandi");
        System.out.println("14. Belajar");
        System.out.println("15. Ngegame");
        System.out.println("16. Ganti pekerjaan");
        System.out.print("Silahkan pilih nomor untuk melakukan aksi yang ingin dilakukan : ");
        String aksi = scanner.next();
        if (aksi.equals("1")) {
            sim.kerja(world);
            scanner.nextLine();
        }

        else if (aksi.equals("2")) {
            sim.olahraga(world);
            scanner.nextLine();
        }

        else if (aksi.equals("3")) {
            sim.tidur(sim.getRuanganSim(), world.getCurrentSim(), world);
        }

        else if (aksi.equals("4")) {
            if (sim.getObjekDipakai() != null && ((String) sim.getObjekDipakai()).contains("Meja dan Kursi")) {
                System.out.println("Daftar makanan yang dapat dimakan: ");
                System.out.println("1. Nasi Ayam");
                System.out.println("2. Nasi Kari");
                System.out.println("3. Susu Kacang");
                System.out.println("4. Tumis Sayur");
                System.out.println("5. Bistik");
                System.out.println("6. Nasi");
                System.out.println("7. Kentang");
                System.out.println("8. Ayam");
                System.out.println("9. Sapi");
                System.out.println("10. Wortel");
                System.out.println("11. Bayam");
                System.out.println("12. Kacang");
                System.out.println("13. Susu");

                sim.makan(world);
                // scanner.nextLine();
            } else {
                System.out.println("Silahkan pergi ke objek 'Meja dan Kursi' terlebih dahulu!");
            }
        }

        else if (aksi.equals("5")) {
            if (sim.getObjekDipakai() != null
                    && (((String) sim.getObjekDipakai()).contains("Kompor Gas")
                            || ((String) sim.getObjekDipakai()).contains("Kompor Listrik"))) {
                System.out.println("1. Nasi Ayam");
                System.out.println("2. Nasi Kari");
                System.out.println("3. Susu Kacang");
                System.out.println("4. Tumis Sayur");
                System.out.println("5. Bistik");
                sim.memasak(world, world.getCurrentSim());
                // STEP BERIKUTNYA
            } else {
                System.out.println("Silahkan pergi ke objek 'Kompor' terlebih dahulu!");
            }
            // scanner.nextLine();
        }

        else if (aksi.equals("6")) {
            int i = 1;
            int simDikunjungi;
            System.out.println("Daftar rumah Sim untuk dikunjungi :");
            for (Rumah rumah : world.getListRumah()) {
                System.out.println(i + ". " + rumah.getPemilikRumah().getNamaLengkap());
                i++;
            }
            while (true) {
                System.out.print("Pilih nomor Sim untuk dikunjungi :");
                simDikunjungi = scanner.nextInt();
                if (simDikunjungi > 0 && simDikunjungi <= world.getListRumah().size()) {
                    break;
                }
            }
            sim.berkunjung(sim.getRumahSim(), world.getListRumah().get(simDikunjungi - 1), world);

        } else if (aksi.equals("7")) {
            Object objekDipakai = sim.getObjekDipakai();
            if (objekDipakai != null && ((String) objekDipakai).contains("Toilet")) {
                sim.buangAir(world);
            } else {
                System.out.println("Silahkan pergi ke objek 'Toilet' terlebih dahulu!");
            }

        } else if (aksi.equals("8")) {
            Object objekDipakai = sim.getObjekDipakai();
            if (objekDipakai != null && ((String) objekDipakai).contains("Jam")) {
                sim.lihatWaktu(world.getCurrentSim().getRuanganSim(), world.getCurrentSim(), world);
            } else {
                System.out.println("Silahkan pergi ke object 'Jam' untuk lihat waktu!");
            }
        }

        else if (aksi.equals("9")) {
            Object objekDipakai = sim.getObjekDipakai();
            if (objekDipakai != null && ((String) objekDipakai).contains("Meja dan Kursi")) {
                sim.berdoa(sim.getRuanganSim(), world);
            } else {
                System.out.println("Silahkan pergi ke object 'Meja dan Kursi' untuk berdoa!");
            }
        }

        else if (aksi.equals("10")) {
            Object objekDipakai = sim.getObjekDipakai();
            if (objekDipakai != null && ((String) objekDipakai).contains("Meja dan Kursi")) {
                sim.meditasi(world);
            } else {
                System.out.println("Silahkan pergi ke object 'Meja dan Kursi' untuk meditasi!");
            }
        }

        else if (aksi.equals("11")) {
            Object objekDipakai = sim.getObjekDipakai();
            if (objekDipakai != null && (((String) objekDipakai).contains("Kasur Single")
                    || ((String) objekDipakai).contains("Kasur Queen Size")
                    || ((String) objekDipakai).contains("Kasur King Size"))) {
                sim.rapihinKasur(sim.getRuanganSim(), world.getCurrentSim(), world);
            } else {
                System.out.println("Silahkan pergi ke object 'Kasur' untuk merapihkan kasur!");
            }
        }

        else if (aksi.equals("12")) {
            int i = 1;
            int simSocialize;
            System.out.println("Daftar Sim untuk diajak socialize :");
            for (Sim simLawan : world.getListSim()) {
                System.out.println(i + ". " + simLawan.getNamaLengkap());
                i++;
            }
            while (true) {
                System.out.print("Pilih nomor Sim untuk diajak socialize :");
                simSocialize = scanner.nextInt();
                if (simSocialize > 0 && simSocialize <= world.getListSim().size()) {
                    break;
                }
            }
            if (simSocialize > 0 && simSocialize <= world.getListSim().size()) {
                sim.socialize(world.getListSim().get(simSocialize - 1), world);
            } else {
                System.out.println("Nomor Sim yang dipilih tidak valid.");
            }
        }

        else if (aksi.equals("13")) {
            Object objekDipakai = sim.getObjekDipakai();
            if (objekDipakai != null && ((String) objekDipakai).contains("Toilet")) {
                sim.beresinKamarMandi(sim.getRuanganSim(), world.getCurrentSim(), world);
            } else {
                System.out.println("Silahkan pergi ke object 'Toilet' untuk beresin kamar mandi!");
            }
        }

        else if (aksi.equals("14")) {
            Object objekDipakai = sim.getObjekDipakai();
            if (objekDipakai != null && ((String) objekDipakai).contains("Meja dan Kursi")) {
                sim.belajar(sim.getRuanganSim(), world.getCurrentSim(), world);
            } else {
                System.out.println("Silahkan pergi ke object 'Meja dan Kursi' untuk belajar!");
            }
        }

        else if (aksi.equals("15")) {
            Object objekDipakai = sim.getObjekDipakai();
            if (objekDipakai != null && ((String) objekDipakai).contains("Meja dan Kursi")) {
                // sim.meditasi(world);
                sim.ngegame(sim.getRuanganSim(), world.getCurrentSim(), world);
            } else {
                System.out.println("Silahkan pergi ke object 'Meja dan Kursi' untuk ngegame!");
            }
        } else if (aksi.equals("16")) {
            sim.printDaftarPekerjaan();
            System.out.println("Pilih nomor pekerjaan : ");
            String pilihan = scanner.nextLine();
            if (pilihan.equals("1")) {
                pilihan = "Badut Sulap";
                sim.gantiPekerjaan(Pekerjaan.BADUT_SULAP);
            } else if (pilihan.equals("2")) {
                pilihan = "Koki";
                sim.gantiPekerjaan(Pekerjaan.KOKI);
            } else if (pilihan.equals("3")) {
                pilihan = "Polisi";
                sim.gantiPekerjaan(Pekerjaan.POLISI);
            } else if (pilihan.equals("4")) {
                pilihan = "Programmer";
                sim.gantiPekerjaan(Pekerjaan.PROGRAMMER);
            } else if (pilihan.equals("5")) {
                pilihan = "Dokter";
                sim.gantiPekerjaan(Pekerjaan.DOKTER);
            } else {
                System.out.println("Pilih nomor yang benar");
            }
        } else {
            System.out.println("Masukkan input yang sesuai");
        }
        scanner.nextLine();
    }

    public static void startGame() {
        banner();
        boolean a = false;
        String command;
        world.createSIM1();
        world.getCurrentSim();
        showMenu2();
        try {
            while (!a) {
                try {
                    // System.out.println("=== MENU AWAL ====");
                    System.out.print("Masukkan nomor perintah : ");
                    command = scanner.nextLine();
                    switch (command) {
                        case "1":
                            showMenu();
                            while (isPlaying) {
                                try {
                                    System.out.println("\n======= MENU UTAMA =======");
                                    System.out.print("Masukkan nomor perintah : ");
                                    command = scanner.nextLine();
                                    switch (command) {
                                        case "1":
                                            viewSimInfo();
                                            break;
                                        case "2":
                                            viewCurrentLocation();
                                            break;
                                        case "3":
                                            viewInventory();
                                            break;
                                        case "4":
                                            upgradeHouse();
                                            world.getCurrentSim().cekEfekTidakTidur(world);
                                            world.getCurrentSim().cekEfekTidakBuangAir(world);
                                            break;
                                        case "5":
                                            moveRoom();
                                            break;
                                        case "6":
                                            editRoom();
                                            world.getCurrentSim().cekEfekTidakTidur(world);
                                            world.getCurrentSim().cekEfekTidakBuangAir(world);
                                            break;
                                        case "7":
                                            if (world.getTime().getStatusAddSim()) {
                                                addSim();
                                            } else {
                                                System.out.println("Belum bisa nambah sim");
                                            }
                                            world.getCurrentSim().cekEfekTidakTidur(world);
                                            world.getCurrentSim().cekEfekTidakBuangAir(world);
                                            break;
                                        case "8":
                                            changeSim();
                                            world.getCurrentSim().cekEfekTidakTidur(world);
                                            world.getCurrentSim().cekEfekTidakBuangAir(world);
                                            break;
                                        case "9":
                                            listObject();
                                            world.getCurrentSim().cekEfekTidakTidur(world);
                                            world.getCurrentSim().cekEfekTidakBuangAir(world);
                                            break;
                                        case "10":
                                            goToObject();
                                            world.getCurrentSim().cekEfekTidakTidur(world);
                                            world.getCurrentSim().cekEfekTidakBuangAir(world);
                                            break;
                                        case "11":
                                            action();
                                            world.getCurrentSim().cekEfekTidakTidur(world);
                                            world.getCurrentSim().cekEfekTidakBuangAir(world);
                                            break;
                                        case "12":
                                            help();
                                            break;
                                        case "13":
                                            // help();
                                            break;
                                        case "14":
                                            System.out.println("Keluar dari permainan");
                                            isPlaying = false;
                                            a = true;
                                            break;
                                        default:
                                            System.out.println("Masukkan input yang sesuai");
                                            break;
                                    }
                                } catch (NoSuchElementException e) {
                                    System.out.println("Masukkan input yang sesuai");
                                }
                            }
                            break;
                        case "2":
                            help2();
                            break;
                        case "3":
                            System.out.println("Keluar dari permainan");
                            a = true;
                            break;
                        default:
                            System.out.println("Masukkan input yang sesuai");
                            break;
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Masukkan input yang sesuai");

                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("Masukkan input yang sesuai");
        }
    }

    public static void banner() {
        System.out.print("\n");
        System.out.println("█████████████████████████████████████████████████████████████");
        System.out.println("█▄─█▀▀▀█─▄█▄─▄▄─█▄─▄███─▄▄▄─█─▄▄─█▄─▀█▀─▄█▄─▄▄─███─▄─▄─█─▄▄─█");
        System.out.println("██─█─█─█─███─▄█▀██─██▀█─███▀█─██─██─█▄█─███─▄█▀█████─███─██─█");
        System.out.println("▀▀▄▄▄▀▄▄▄▀▀▄▄▄▄▄▀▄▄▄▄▄▀▄▄▄▄▄▀▄▄▄▄▀▄▄▄▀▄▄▄▀▄▄▄▄▄▀▀▀▀▄▄▄▀▀▄▄▄▄▀");
        System.out.println("████████████████████████████████████████████████████████████████");
        System.out.println("█─▄▄▄▄█▄─▄█▄─▀█▀─▄█▀▀▀▀▀██▄─▄▄─█▄─▄███▄─▄█─▄▄▄─█▄─▄█─▄─▄─█▄─█─▄█");
        System.out.println("█▄▄▄▄─██─███─█▄█─██████████─▄▄▄██─██▀██─██─███▀██─████─████▄─▄██");
        System.out.println("▀▄▄▄▄▄▀▄▄▄▀▄▄▄▀▄▄▄▀▀▀▀▀▀▀▀▄▄▄▀▀▀▄▄▄▄▄▀▄▄▄▀▄▄▄▄▄▀▄▄▄▀▀▄▄▄▀▀▀▄▄▄▀▀");
        System.out.print("\n");
    }

}
