package jpl.ch03.ex06;

abstract class EnergySource {
    int nowGasoline;
    int maxCapa;
    private int emptyLine;
    abstract boolean empty();

}
