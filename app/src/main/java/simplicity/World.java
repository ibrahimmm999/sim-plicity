package simplicity;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.NoSuchElementException;

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
    }

    public void createSIM1() {
        Scanner scanner = new Scanner(System.in);
        Rumah rumah;
        String namaSim;
        Sim sim;
        while (true) {
            try {
                System.out.print("Masukkan nama SIM: ");
                namaSim = scanner.nextLine();
                if (checkSimAvailability(namaSim)) {
                    sim = new Sim(namaSim);
                    listSim.add(sim);
                    break;
                } else {
                    System.out.println("Nama SIM sudah terpakai!");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Input salah, silakan coba lagi.");
                scanner.nextLine();
            }
        }
        while (true) {
            try {
                System.out.println("Masukkan koordinat rumah yang diinginkan:");
                System.out.print("Koordinat X : ");
                int x = scanner.nextInt();
                System.out.print("Koordinat Y : ");
                int y = scanner.nextInt();
                // scanner.nextLine(); // clear newline character
                if (listRumah == null) {
                    listRumah = new ArrayList<Rumah>(); // initialize listRumah if null
                }
                if (checkKoordinatAvailability(x, y)) {
                    rumah = new Rumah(new Point(x, y), sim);
                    sim.setRumahSim(rumah);
                    sim.setRuanganSim(rumah.getRuangan("Ruang 1"));
                    sim.setPosisiSim(rumah.getRuangan("Ruang 1").getPosisi());
                    sim.getRuanganSim().addObject1(new SingleBed(), new Point(0, 1), false, sim.getInventory());
                    sim.getRuanganSim().addObject1(new Toilet(), new Point(4, 1), false, sim.getInventory());
                    sim.getRuanganSim().addObject1(new KomporGas(), new Point(0, 3), false, sim.getInventory());
                    sim.getRuanganSim().addObject1(new Jam(), new Point(5, 1), false, sim.getInventory());
                    sim.getRuanganSim().addObject1(new MejaKursi(), new Point(1, 4), false,
                            sim.getInventory());
                    sim.setPosisiSim(rumah.getRuangan("Ruang 1").getPosisi());
                    listRumah.add(rumah); // add rumah to listRumah
                    break;
                } else {
                    System.out.println("Koordinat sudah terpakai!");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Input salah, silakan coba lagi.");
                scanner.nextLine();
            }
        }
        System.out.println("Sim berhasil dibuat");
        currentSim = sim;
    }

    public void createSIMNext() {
        Scanner scanner = new Scanner(System.in);
        Rumah rumah;
        String namaSim;
        Sim sim;
        while (true) {
            try {
                System.out.print("Masukkan nama SIM: ");
                namaSim = scanner.nextLine();
                if (checkSimAvailability(namaSim)) {
                    sim = new Sim(namaSim);
                    listSim.add(sim);
                    break;
                } else {
                    System.out.println("Nama SIM sudah terpakai!");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Input salah, silakan coba lagi.");
                scanner.nextLine();
            }
        }
        while (true) {
            try {
                System.out.println("Masukkan koordinat rumah yang diinginkan:");
                System.out.print("Koordinat X : ");
                int x = scanner.nextInt();
                System.out.print("Koordinat Y : ");
                int y = scanner.nextInt();
                scanner.nextLine(); // clear newline character
                if (listRumah == null) {
                    listRumah = new ArrayList<Rumah>(); // initialize listRumah if null
                }
                if (checkKoordinatAvailability(x, y)) {
                    rumah = new Rumah(new Point(x, y), sim);
                    sim.setRumahSim(rumah);
                    sim.setRuanganSim(rumah.getRuangan("Ruang 1"));
                    sim.getRuanganSim().addObject1(new SingleBed(), new Point(0, 1), false, sim.getInventory());
                    sim.getRuanganSim().addObject1(new Toilet(), new Point(4, 1), false, sim.getInventory());
                    sim.getRuanganSim().addObject1(new KomporGas(), new Point(0, 3), false, sim.getInventory());
                    sim.getRuanganSim().addObject1(new Jam(), new Point(5, 1), false, sim.getInventory());
                    sim.getRuanganSim().addObject1(new MejaKursi(), new Point(1, 4), false,
                            sim.getInventory());
                    sim.setPosisiSim(rumah.getRuangan("Ruang 1").getPosisi());
                    listRumah.add(rumah); // add rumah to listRumah
                    break;
                } else {
                    System.out.println("Koordinat sudah terpakai!");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Input salah, silakan coba lagi.");
                scanner.nextLine();
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

    public int getHari() {
        return time.getDay();
    }

    public ArrayList<Rumah> getListRumah() {
        return this.listRumah;
    }
}
