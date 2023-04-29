package simplicity;

import java.util.Scanner;

public class Game {
    private static World world = new World(64, 64, null);
    private static boolean isPlaying = true;
    private static Scanner scanner = new Scanner(System.in);

    public static void showMenu() {
        System.out.println("=== MENU ===");
        System.out.println("Silakan pilih nomor menu : ");
        System.out.println("1. start");
        System.out.println("2. help");
        System.out.println("3. exit");
        System.out.println("4. view sim info");
        System.out.println("5. view current location");
        System.out.println("6. view inventory");
        System.out.println("7. upgrade house");
        System.out.println("8. move room");
        System.out.println("9. edit room");
        System.out.println("10. add sim");
        System.out.println("11. change sim");
        System.out.println("12. list object");
        System.out.println("13. go to object");
        System.out.println("14. action");
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
        System.out.println("Posisi Sim " + sim.getNamaLengkap() + " : { Rumah " + rumah + ", di " + ruang + " }.");
    }

    public static void viewInventory() {
        Sim sim = world.getCurrentSim();
        System.out.println(sim.getInventory());
    }

    public static void upgradeHouse() {

    }

    public static void moveRoom() {

    }

    public static void editRoom() {

    }

    public static void addSim() {
        world.createSIM();
    }

    public static void changeSim() {
        if (world.getListSim().size() > 0) {
            System.out.println("Daftar Sim yang tersedia");
            int idx = 1;
            for (Sim sim : world.getListSim()) {
                System.out.println(idx + ". " + sim.getNamaLengkap());
                idx++;
            }
            System.out.print("Silahkan pilih nomor sim : ");
            while (true) {
                int simDipilih = scanner.nextInt();
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
        Sim sim = world.getCurrentSim();
        String objek;
        listObject();
        while (true) {
            System.out.print("Pilih objek : ");
            objek = scanner.next();
            if (sim.getRuanganSim().getListObjek().containsKey(objek)) {
                break;
            } else {
                System.out.println("Objek tidak ditemukan");
            }
        }
        sim.setObjekDipakai(objek);
        System.out.println("Sim " + sim.getNamaLengkap() + " menggunakan " + sim.getObjekDipakai());
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
        System.out.print("Silahkan pilih nomor untuk melakukan aksi yang ingin dilakukan : ");
        String aksi = scanner.next();
        if (aksi.equals("1")) {
            sim.kerja();
        }

        else if (aksi.equals("2")) {
            sim.olahraga();
        }

        else if (aksi.equals("3")) {
            if (sim.getObjekDipakai().equals("Kasur Single") || sim.getObjekDipakai().equals("Kasur Queen Size")
                    || sim.getObjekDipakai().equals("Kasur King Size")) {
                sim.tidur();
            } else {
                System.out.println("Silahkan pergi ke objek 'Kasur' terlebih dahulu!");
            }
        }

        else if (aksi.equals("4")) {
            System.out.println("Daftar makanan : ");
            // STEP BERIKUTNYA
        }

        else if (aksi.equals("5")) {
            if (sim.getObjekDipakai().equals("Kompor Gas") || sim.getObjekDipakai().equals("Kompor Listrik")) {
                System.out.println("Daftar menu untuk dimasak :");
                // STEP BERIKUTNYA
            } else {
                System.out.println("Silahkan pergi ke objek 'Kompor' terlebih dahulu!");
            }
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
            sim.berkunjung(sim.getRumahSim(), world.getListRumah().get(simDikunjungi - 1));

        } else if (aksi.equals("7")) {
            if (sim.getObjekDipakai().equals("Toilet")) {
                sim.buangAir(sim.getRuanganSim());
            } else {
                System.out.println("Silahkan pergi ke objek 'Toilet' terlebih dahulu!");
            }

        } else if (aksi.equals("8")) {
            if (sim.getObjekDipakai().equals("Jam")) {
                sim.lihatWaktu();
            } else {
                System.out.println("Silahkan pergi ke object 'Jam' untuk lihat waktu!");
            }
        }

        else if (aksi.equals("9")) {
            sim.berdoa(sim.getRuanganSim());
        }

        else if (aksi.equals("10")) {
            sim.meditasi();
        }

        else if (aksi.equals("11")) {
            if (sim.getObjekDipakai().equals("Kasur Single") || sim.getObjekDipakai().equals("Kasur Queen Size")
                    || sim.getObjekDipakai().equals("Kasur King Size")) {
                sim.rapihinKasur(sim.getObjekDipakai());
            } else {
                System.out.println("Silahkan pergi ke objek 'Kasur' terlebih dahulu!");
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
            sim.socialize(world.getListSim().get(simSocialize));
        }

        else if (aksi.equals("13")) {
            sim.beresinKamarMandi(sim.getRuanganSim());
        }

        else if (aksi.equals("14")) {
            sim.belajar(sim.getRuanganSim());
        }

        else if (aksi.equals("15")) {
            sim.ngegame(sim.getRuanganSim());
        }

        else {
            System.out.println("Masukkan input yang sesuai");
        }
    }

    public static void startGame() {
        String command;
        System.out.println("Welcome to Sim-Plicity");
        System.out.print("Masukkan nama sim: ");
        Sim sim1 = new Sim(scanner.next());
        Rumah rumah1 = new Rumah(null, sim1); // INI DIISI APA KOORDINATNYA
        sim1.setRumahSim(rumah1);
        sim1.setRuanganSim(rumah1.getRuangan("Ruang 1"));
        sim1.setPosisiSim(rumah1.getRuangan("Ruang 1").getPosisi());
        showMenu();
        while (isPlaying) {
            System.out.println("Masukkan nomor perintah : ");
            command = scanner.next();
            switch (command) {
                case "1":
                    System.out.println("Game sedang berjalan...");
                    break;
                case "2":
                    help();
                    break;
                case "3":
                    System.out.println("Keluar dari permainan");
                    scanner.close();
                    isPlaying = false;
                    break;
                case "4":
                    viewSimInfo();
                    break;
                case "5":
                    viewCurrentLocation();
                    break;
                case "6":
                    viewInventory();
                    break;
                case "7":
                    upgradeHouse();
                    break;
                case "8":
                    moveRoom();
                    break;
                case "9":
                    editRoom();
                    break;
                case "10":
                    addSim();
                    break;
                case "11":
                    changeSim();
                    break;
                case "12":
                    listObject();
                    break;
                case "13":
                    goToObject();
                    break;
                case "14":
                    action();
                    break;
                default:
            }
        }
    }
}
