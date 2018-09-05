package jpl.ch03.ex01;

/**
 * 練習問題 3.1
 *  車が持っている座席数と現在座っている人数を返す機能を追加しなさい．
 *  また，新たにmain分を追加定義してPassengerVehicleを数個生成して表示するようにしなさい
 *
 **/

public class PassengerVehicle {
    private String name;    //名前
    private double velocity; //　速度
    private double angle; //方向
    private String owner; // 所有者
    private static long VehicleCode = 0;    //識別番号
    private int passengerCount; // 乗客人数
    private int seatNumber; // 座席数

    {
        VehicleCode ++;
    }

    private long VehicleCodeLocal = VehicleCode;    //識別番号（個体）

    PassengerVehicle(String name, double velocity, double angle, String owner,int passengerCount,
                     int seatNumber){
        this.name = name;
        this.velocity = velocity;
        this.angle = angle;
        this.owner = owner;
        this.passengerCount = passengerCount;
        this.seatNumber = seatNumber;
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
//        String desc = VehicleCodeLocal + " " + name + " " + velocity + "km/h "
//                + angle + " " + owner;
        String desc = String.format(" name : %s\n owner : %s\n" +
                " velocity : %03.1f\n angle : %02.1f\n passengerCount : %d\n" +
                " seatNumber : %d\n VehicleCode : %d",
                name,owner,velocity,angle,passengerCount,seatNumber,VehicleCode);
        return desc;
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

    public static void main(String args[]){
        PassengerVehicle twinBus = new PassengerVehicle("twinBus",60,0,"Taro",40,120);
        System.out.println(twinBus.toString());
    }


}
