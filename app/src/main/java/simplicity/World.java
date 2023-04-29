package simplicity;

import java.util.ArrayList;
import java.util.Scanner;

public class World {
    private int panjang = 0;
    private int lebar = 0;
    private Waktu time;
    private Sim currentSim;
    private ArrayList<Rumah> listRumah;

    public World(int panjang, int lebar, ArrayList<Rumah> listRumah) {
        this.panjang = panjang;
        this.lebar = lebar;
        this.listRumah = listRumah;
        this.time = new Waktu();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama SIM: ");
        this.currentSim = new Sim(scanner.nextLine());
    }

    public void createSIM() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama SIM: ");
        Sim sim = new Sim(scanner.nextLine());
    }

    public Sim getCurrentSim() {
        return currentSim;
    }

    public void setCurrentSim(Sim sim) {
        currentSim = sim;
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
