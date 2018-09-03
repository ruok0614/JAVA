package jpl.ch03.ex01;

/**
 * 練習問題 3.1
 *  Vehicle クラスを拡張してPassengerVehicleクラスを作成しなさい
 *  車の座席数と現在座っている人数を返す機能を追加しなさい。
 *  Passengerメソッドに新たなmainメソッドを定義して
 *  PassengerVehicleオブジェクトを数個生成して表示するようにしなさい。
 **/

public class PassengerVehicle {
    private String name;    //名前
    private double velocity; //　速度
    private double angle; //方向
    private String owner; // 所有者
    private static long VehicleCode = 0;    //識別番号
    private int seatNumber; //座席数
    private int passengerNumber;    //乗客数

    {
        VehicleCode ++;
    }

    private long VehicleCodeLocal = VehicleCode;    //識別番号（ローカル）

    PassengerVehicle(String name, double velocity, double angle, String owner,int seatNumber,int passengerNumber){
        this.name = name;
        this.velocity = velocity;
        this.angle = angle;
        this.owner = owner;
        this.seatNumber = seatNumber;
        this.passengerNumber = passengerNumber;
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
        String desc = String.format("名前：%s\n速度：%.1f\n所有者：%s\n座席数：%d\n乗客数：%d\n",
                name,velocity,owner,seatNumber,passengerNumber);

        return desc;
    }

    void turn(double angle){
        this.angle += angle;
    }


    /* インスタンス化と同時に識別番号が足されるので現在の識別番号がMAX */
    long getMaxCode() {
        return VehicleCode;
    }

    public static void main(String args[]){
        PassengerVehicle twinBus = new PassengerVehicle("twin Bus",60,0,"taro",120,80);
        System.out.println(twinBus.toString());

        PassengerVehicle car = new PassengerVehicle("taxi",60,0,"Syuzo",5,2);
        System.out.println(car.toString());

    }


}
