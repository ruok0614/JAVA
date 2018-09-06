package jpl.ch02.ex10;

/**
 * 練習問題 2.10
 * 　vehicleクラスにToString()を追加しなさい
 **/
public class Vehicle {
    String name;    //名前
    double velocity; //　速度
    double angle; //方向
    String owner; // 所有者
    private static long VehicleCode = 0;    //識別番号

    {
        VehicleCode ++;
    }

    private long VehicleCodeLocal = VehicleCode;    //ローカルな識別番号

    Vehicle(String name, double velocity, double angle, String owner){
        this.name = name;
        this.velocity = velocity;
        this.angle = angle;
        this.owner = owner;
    }

    public String toString(){
        String desc = VehicleCodeLocal + " " + name + " " + velocity + "km/h "
                + angle + " " + owner;
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



}
