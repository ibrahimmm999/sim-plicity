package simplicity;

public class Waktu {
    private int hariKe;
    private int totalDetik; // TOTAL DETIK YANG SUDAH BERJALAN

    public Waktu() {
        hariKe = 1;
    }

    public void updateWaktu(int duration) {
        this.totalDetik += duration;
    }

    public int getHari() {
        hariKe = (totalDetik / 720) + 1;
        return hariKe;
    }

    public int gettotalDetik() {
        return totalDetik;
    }

    public int getSisaWaktu() {
        getHari();
        return hariKe * 720 - totalDetik;
    }

    public void wait(int duration) {
        try {
            Thread.sleep(duration * 1000);
        } catch (Exception e) {
            Thread.interrupted();
        }
    }

}