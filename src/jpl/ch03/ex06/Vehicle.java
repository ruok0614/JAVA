package jpl.ch03.ex06;

/**
 * 練習問題 3.6
 *  　Vehicleを変更してコンストラクタでVehicleと関連付けられるEnergySourceオブジェクト
 *  の参照を持つようにしなさい．EnergySourceクラスはabstractクラスでならなければならない．
 *  EnergySourceにabstractのemptyメソッドを入れて，GasTankとBatteryクラスでそのメソッドを実装しなさい
 *  動力源がから出ないことを保証するstartメソッドをVehicleに追加しなさい．
 *
 **/

public class Vehicle {
    private String name;    //名前
    private double velocity; //　速度
    private double angle; //方向
    private String owner; // 所有者
    private static long VehicleCode = 0;    //識別番号
    private EnergySource powerSource;

    {
        VehicleCode ++;
    }

    private long VehicleCodeLocal = VehicleCode;    //識別番号

    Vehicle(String name, double velocity, double angle, String owner){
        this.name = name;
        this.velocity = velocity;
        this.angle = angle;
        this.owner = owner;
    }

    void setPowerSource(EnergySource powerSource){
        this.powerSource = powerSource;
    }

    void SetName(String name){
        this.name = name;
    }

    void SetVelocity(double velocity){
        this.velocity = velocity;
    }

    void SetAngle(double angle){
        this.angle = angle;
    }

    void SetOwner(String owner){
        this.owner = owner;
    }

    void changeSpeed(double velocity){
        this.velocity = velocity;
    }

    void stop(){
        velocity = 0;
    }

    public String toString(){
        String desc = VehicleCodeLocal + " " + name + " " + velocity + "km/h "
                + angle + " " + owner;
        return desc;
    }

    void turn(double angle){
        this.angle += angle;
    }


    void ShowField(){
        System.out.println(this.name);
        System.out.println("速度:" + this.velocity);
        System.out.println("方向:" + this.angle);
        System.out.println("所有者:" + this.owner);
        System.out.println();
    }

    /* インスタンス化と同時に識別番号が足されるので現在の識別番号がMAX */
    long getMaxCode() {
        return VehicleCode;
    }

    void sart(){
        boolean torf = powerSource.empty();

        if(torf == false){
            System.out.println("燃料はあります");
        }else{
            System.out.println("給油してください");
        }
    }

    public static void main(String args[]){
        Vehicle car = new Vehicle("cocoa",60,0,"Taro");
        car.setPowerSource(new GasTank(30,20));
        car.sart();
    }



}
