package jpl.ch03.ex09;


public class Garage implements Cloneable{
    final int maxCapa;
    Vehicle[] garage;

    Garage(int maxCapa){
        this.maxCapa = maxCapa;
        garage = new Vehicle[maxCapa];
    }

    public void setGarage(int i,Vehicle vehicle) {
        garage[i] = vehicle;
    }

    public Vehicle getGarage(int i){
        return garage[i];
    }

    public void showGarage(int i){
        garage[i].ShowField();
    }

    public Garage clone(){
        try {
            Garage nobj = (Garage) super.clone();
            nobj.garage = garage.clone();
            return nobj;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.toString());
        }
    }

    public static void main(String args[]){
        Garage g = new Garage(5);
        Vehicle car1 = new Vehicle("cocoa",60,0,"Taro");
        g.setGarage(0,car1);
        g.setGarage(1,car1.clone());
        g.garage[1].SetOwner("Ziro");
        g.showGarage(0);
        g.showGarage(1);

    }
}
