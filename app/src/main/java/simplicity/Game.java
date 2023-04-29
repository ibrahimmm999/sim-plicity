package simplicity;

import java.util.Scanner;

public class Game {
    private static World world = new World(64, 64, null);
    private static boolean isPlaying = true;

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

    }

    public static void changeSim() {

    }

    public static void listObject() {

    }

    public static void goToObject() {

    }

    public static void action() {

    }

    public static void startGame() {
        String command;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Sim-Plicity");
        System.out.print("Masukkan nama sim: ");
        Sim sim1 = new Sim(scanner.nextLine());
        Rumah rumah1 = new Rumah(null, sim1); // INI DIISI APA KOORDINATNYA
        sim1.setRumahSim(rumah1);
        sim1.setRuanganSim(rumah1.getRuangan("Ruang 1"));
        sim1.setPosisiSim(rumah1.getRuangan("Ruang 1").getPosisi());
        showMenu();
        while (isPlaying) {
            System.out.println("Masukkan nomor perintah : ");
            command = scanner.nextLine();
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
