package jpl.ch04.ex01;

public class GasTank implements jpl.ch04.ex01.EnergySource {
    private int nowGasoline;
    private final int maxCapa;
    private int emptyLine;

    GasTank(int maxCapa,int nowGasoline){
        this.maxCapa = maxCapa;

        if(nowGasoline >= maxCapa){
            System.out.println("キャパシティオーバー");
        }else{
            this.nowGasoline = nowGasoline;
            this.emptyLine = maxCapa * 20/100;
        }
    }

    void setNowBattery(int nowGasoline){
        if(nowGasoline >= maxCapa){
            System.out.println("キャパシティオーバー");
        }else{
            this.nowGasoline = nowGasoline;
        }
    }


    public boolean empty() {

        if (nowGasoline <= emptyLine){
            return true;
        }else {
            return false;
        }
    }
}