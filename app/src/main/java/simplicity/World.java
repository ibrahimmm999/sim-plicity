package simplicity;

import java.util.ArrayList;

public class World{
    private int panjang = 0;
    private int lebar = 0;
    private int time;
    private int day;
    private ArrayList<Rumah> listRumah;

    public World(int panjang, int lebar, int time, int day, ArrayList<Rumah> listRumah){
        this.panjang = panjang;
        this.lebar = lebar;
        this.time = time;
        this.day = day;
        this.listRumah = listRumah;

    }
    public void createSIM();
    public int getPanjang(){
        return panjang;
    }
    public int getLebar(){
        return lebar;
    }
    public int getTime();
    public int getDay();
    public ArrayList<Rumah> getListRumah(){
        return this.listRumah;
    }
}