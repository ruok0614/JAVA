package jpl.ch04.ex01;

public class Battery implements EnergySource {

    private int nowBattery;
    private final int maxCapa;
    private int emptyLine;

    Battery(int maxCapa,int nowBattery){
        this.maxCapa = maxCapa;

        if(nowBattery >= maxCapa){
            System.out.println("キャパシティオーバー");
        }else{
            this.nowBattery = nowBattery;
            this.emptyLine = maxCapa * 20/100;
        }
    }

    void setNowBattery(int nowBattery){
        if(nowBattery >= maxCapa){
            System.out.println("キャパシティオーバー");
        }else{
            this.nowBattery = nowBattery;
        }
    }


    public boolean empty() {

        if (nowBattery <= emptyLine){
            return true;
        }else {
            return false;
        }
    }
}
