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
                    isPlaying = false;
                    break;
            }
        }
    }
}
