package simplicity;

import java.util.ArrayList;
import java.util.Scanner;

public class World {
    private int panjang = 0;
    private int lebar = 0;
    private Waktu time;
    private Sim currentSim;
    private ArrayList<Rumah> listRumah;
    private ArrayList<Sim> listSim = new ArrayList<>();

    public World(int panjang, int lebar, ArrayList<Rumah> listRumah) {
        this.panjang = panjang;
        this.lebar = lebar;
        this.listRumah = listRumah;
        this.time = new Waktu();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama SIM: ");
        this.currentSim = new Sim(scanner.next());
        scanner.close();
    }

    public void createSIM() {
        Scanner scanner = new Scanner(System.in);
        Sim sim;
        Rumah rumah;
        String namaSim;
        while (true) {
            System.out.print("Masukkan nama SIM: ");
            namaSim = scanner.next();
            if (checkSimAvailability(namaSim)) {
                sim = new Sim(namaSim);
                listSim.add(sim);
                break;
            } else {
                System.out.println("Nama SIM sudah terpakai!");
            }
        }
        while (true) {
            System.out.println("Masukkan koordinat rumah yang diinginkan:");
            System.out.print("Koordinat X : ");
            int x = scanner.nextInt();
            System.out.print("Koordinat Y : ");
            int y = scanner.nextInt();
            if (checkKoordinatAvailability(x, y)) {
                rumah = new Rumah(new Point(x, y), sim);
                sim.setRumahSim(rumah);
                sim.setRuanganSim(rumah.getRuangan("Ruang 1"));
                sim.setPosisiSim(rumah.getRuangan("Ruang 1").getPosisi());
                break;
            } else {
                System.out.println("Koordinat sudah terpakai!");
            }
        }
        System.out.println("Sim berhasil dibuat");
        scanner.close();
    }

    public boolean checkSimAvailability(String name) {
        boolean cek = true;
        for (Sim sim : listSim) {
            if (sim.getNamaLengkap().equals(name)) {
                cek = false;
                break;
            }
        }
        return cek;
    }

    public boolean checkKoordinatAvailability(int x, int y) {
        boolean cek = true;
        for (Rumah rumah : listRumah) {
            if (x == rumah.getKoordinat().getX() && y == rumah.getKoordinat().getY()) {
                cek = false;
                break;
            }
        }
        return cek;
    }

    public void setCurrentSim(Sim sim) {
        currentSim = sim;
    }

    public ArrayList<Sim> getListSim() {
        return listSim;
    }

    public Sim getCurrentSim() {
        return currentSim;
    }

    public int getPanjang() {
        return panjang;
    }

    public int getLebar() {
        return lebar;
    }

    public String getTime() {
        return time.getDay() + " day, " + time.getHour() + " hour, " + time.getMinute() + " minute, " + time.getSecond()
                + " second";
    }

    public int getDay() {
        return time.getDay();
    }

    public int getHour() {
        return time.getHour();
    }

    public int getMinute() {
        return time.getMinute();
    }

    public int getSecond() {
        return time.getSecond();
    }

    public ArrayList<Rumah> getListRumah() {
        return this.listRumah;
    }
}
