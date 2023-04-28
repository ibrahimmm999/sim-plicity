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

    public static void startGame() {
        String command;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Sim-Plicity");
        System.out.print("Masukkan nama sim: ");
        Sim sim1 = new Sim(scanner.nextLine());
        Rumah rumah1 = new Rumah(null, sim1); // INI DIISI APA KOORDINATNYA
        sim1.setRumahSim(rumah1);
        sim1.setPosisiSim(rumah1.getRuangan("Ruang 1").getPosisi());
        showMenu();
        while (isPlaying) {
            System.out.println("Masukkan nomor perintah : ");
            command = scanner.nextLine();
            switch (command) {
                case "1":
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
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "8":
                    break;
                case "9":
                    break;
                case "10":
                    System.out.print("Masukkan nama sim: ");
                    // STEP LANJUTAN
                    world.createSIM();
                    break;
                case "11":
                    break;
                case "12":
                    break;
                case "13":
                    break;
                case "14":
                    break;
                default:
            }
        }

    }
}
