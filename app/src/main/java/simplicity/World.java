package simplicity;

import java.util.ArrayList;

public class World{
    private int panjang = 0;
    private int lebar = 0;
    private Waktu time;
    private ArrayList<Rumah> listRumah;

    public World(int panjang, int lebar, ArrayList<Rumah> listRumah){
        this.panjang = panjang;
        this.lebar = lebar;
        this.listRumah = listRumah;
        this.time = new Waktu();

    }
    public void createSIM(){

    }
    public int getPanjang(){
        return panjang;
    }
    public int getLebar(){
        return lebar;
    }
    public String getTime(){
        return time.getDay() + " day, " + time.getHour() + " hour, " + time.getMinute() + " minute, " + time.getSecond() + " second";
    }
    public int getDay(){
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

    public ArrayList<Rumah> getListRumah(){
        return this.listRumah;
    }
}
