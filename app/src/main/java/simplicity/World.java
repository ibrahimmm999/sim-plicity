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
                if (checkSimAvailability(namaSim) && namaSim.split(" ").length > 0 && namaSim.length() > 0) {
                    sim = new Sim(namaSim);
                    listSim.add(sim);
                    break;
                } else if (namaSim.split(" ").length > 0 || namaSim.length() > 0) {
                    System.out.println("Nama tidak boleh kosong");
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
                int x, y;
                while (true) {
                    System.out.println("Masukkan koordinat rumah yang diinginkan:");
                    System.out.print("Koordinat X : ");
                    x = scanner.nextInt();
                    System.out.print("Koordinat Y : ");
                    y = scanner.nextInt();
                    if (x <= 64 && y <= 64) {
                        break;
                    } else {
                        System.out.println("Titik tidak bisa lebih dari 64");
                    }
                }
                // scanner.nextLine(); // clear newline character
                if (listRumah == null) {
                    listRumah = new ArrayList<Rumah>(); // initialize listRumah if null
                }
                if (checkKoordinatAvailability(x, y)) {
                    rumah = new Rumah(new Point(x, y), sim);
                    sim.setRumahSim(rumah);
                    sim.setRuanganSim(rumah.getRuangan("Ruang 1"));
                    sim.setPosisiSim(rumah.getRuangan("Ruang 1").getPosisi());
                    System.out.print("\n");
                    System.out.println("Generate object Pertama Kalinya untuk SIM yang dibuat :");
                    System.out.print("- ");
                    sim.getRuanganSim().addObject1(new SingleBed(), new Point(0, 0), false);
                    System.out.print("- ");
                    sim.getRuanganSim().addObject1(new Toilet(), new Point(4, 0), false);
                    System.out.print("- ");
                    sim.getRuanganSim().addObject1(new KomporGas(), new Point(0, 5), false);
                    System.out.print("- ");
                    sim.getRuanganSim().addObject1(new Jam(), new Point(3, 1), false);
                    System.out.print("- ");
                    sim.getRuanganSim().addObject1(new MejaKursi(), new Point(0, 1), false);
                    System.out.print("\n");
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
        System.out.println("Sim " + namaSim + " berhasil dibuat!");
        System.out.print("\n");
        currentSim = sim;
    }

    public void createSIMNext() {
        if (time.getStatusAddSim()) {
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
                    } else if (namaSim.split(" ").length > 0 || namaSim.length() > 0) {
                        System.out.println("Nama tidak boleh kosong");
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
                    int x, y;
                    while (true) {
                        System.out.print("Koordinat X : ");
                        x = scanner.nextInt();
                        System.out.print("Koordinat Y : ");
                        y = scanner.nextInt();
                        if (x <= 64 && y <= 64) {
                            break;// clear newline character
                        } else {
                            System.out.println("Titik x dan y harus < 64");
                        }
                    }
                    if (listRumah == null) {
                        listRumah = new ArrayList<Rumah>(); // initialize listRumah if null
                    }
                    if (checkKoordinatAvailability(x, y)) {
                        rumah = new Rumah(new Point(x, y), sim);
                        sim.setRumahSim(rumah);
                        sim.setRuanganSim(rumah.getRuangan("Ruang 1"));
                        sim.setPosisiSim(rumah.getRuangan("Ruang 1").getPosisi());
                        System.out.print("\n");
                        System.out.println("Generate object Pertama Kalinya untuk SIM yang dibuat :");
                        System.out.print("- ");
                        sim.getRuanganSim().addObject1(new SingleBed(), new Point(0, 0), false);
                        System.out.print("- ");
                        sim.getRuanganSim().addObject1(new Toilet(), new Point(4, 0), false);
                        System.out.print("- ");
                        sim.getRuanganSim().addObject1(new KomporGas(), new Point(0, 5), false);
                        System.out.print("- ");
                        sim.getRuanganSim().addObject1(new Jam(), new Point(3, 1), false);
                        System.out.print("- ");
                        sim.getRuanganSim().addObject1(new MejaKursi(), new Point(0, 1), false);
                        System.out.print("\n");
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
            System.out.println("Sim " + namaSim + " berhasil dibuat!");
            time.setAddSim(false);
            System.out.print("\n");
        } else {
            System.out.println("Tunggu sehari dulu baru bisa add Sim");
        }

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

    public Waktu getTime() {
        return time;
    }

    public ArrayList<Rumah> getListRumah() {
        return this.listRumah;
    }

    public void removeSimDanRumah(Sim sim) {
        for (Rumah rumah : listRumah) {
            if (rumah.getPemilikRumah().getNamaLengkap().equals(sim.getNamaLengkap())) {
                listRumah.remove(rumah);
                listSim.remove(sim);
                System.out.println("Sim berhasil dihapus");
                if (listSim.size() <= 0) {
                    System.out.println("Tidak ada Sim yang tersisa!");
                    // throw new RuntimeException("GAME OVER");
                    System.out.println(">>>> GAME OVER <<<<");
                    System.out.println("Keluar dari game...");
                    System.exit(1);
                }
            }
        }

    }
}
