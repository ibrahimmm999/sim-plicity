package simplicity;

public class Waktu {
    private int day;
    private int jam;
    private int menit;
    private int detik;

    public Waktu(int day, int jam, int menit, int detik) {
        this.day = day;
        this.jam = jam;
        this.menit = menit;
        this.detik = detik;
    }

    public Waktu(int jam, int menit, int detik) {
        this(0, jam, menit, detik);
    }

    public Waktu(int menit, int detik) {
        this(0, 0, menit, detik);
    }

    public Waktu(int sec) {
        this((int) (sec / (24 * 3600)), (int) (sec / 3600 % 24), (int) (sec / 60 % 60), (int) (sec % 60));
    }

    public Waktu() {
        this(0, 0, 0, 0);
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return jam;
    }

    public int getMinute() {
        return menit;
    }

    public int getSecond() {
        return detik;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int jam) {
        this.jam = jam;
    }

    public void setMinute(int menit) {
        this.menit = menit;
    }

    public void setSecond(int detik) {
        this.detik = detik;
    }

    public int convertToSecond() {
        return day * 24 * 60 * 60 + jam * 60 * 60 + menit * 60 + detik;
    }

    public Waktu addTime(Waktu time) {
        return new Waktu(this.convertToSecond() + time.convertToSecond());
    }
    
    public String remainingTime() {
        int remaining = 720 - (jam * 60 + menit) * 60 + detik;
        return "Remaining time: " + (remaining / 60) + " minute(s) and " + (remaining % 60) + " second(s) in the day.";
    }
}