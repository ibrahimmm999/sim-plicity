package simplicity;

public class Waktu {
    private int hariKe;
    private int detikHarian;
    private boolean addSim;
    private int totalDetik; // TOTAL DETIK DALAM GAME

    public Waktu() {
        hariKe = 1;
        totalDetik = 0;
        detikHarian = 0;
        addSim = true;
    }

    public void updateWaktu(int duration) {
        this.totalDetik += duration;
        this.detikHarian += duration;
        if (this.detikHarian >= 720) {
            hariKe++;
            detikHarian -= 720;
            addSim = true;
        }
    }

    public int getDetikHarian() {
        return detikHarian;
    }

    public boolean getStatusAddSim() {
        return addSim;
    }

    public int getHari() {
        return hariKe;
    }

    public int getTotalDetik() {
        return totalDetik;
    }

    public int getSisaWaktu() {
        return 720 - detikHarian;
    }

    public void delayWaktu(int duration) {
        try {
            Thread.sleep(duration * 1000);
        } catch (Exception e) {
            Thread.interrupted();
        }
    }

    public void setAddSim(boolean bool) {
        addSim = bool;
    }
}
