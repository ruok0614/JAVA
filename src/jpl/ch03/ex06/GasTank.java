package jpl.ch03.ex06;

public class GasTank extends EnergySource {
    int nowGasoline;
    int maxCapa;
    private int emptyLine = maxCapa * 20/100;

    @Override
    boolean empty() {
        if (nowGasoline <= emptyLine){
            return true;
        }else {
            return false;
        }
    }
}
