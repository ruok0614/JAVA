package jpl.ch02.ex18;

/**
 * 練習問題 2.18
 *  Vehicle.mainを変更して，コマンドプロンプトで指定された名前を所有者として持つ車を作成し，
 *  表示するようにしなさい
 **/

public class Vehicle {
    private String name;    //名前
    private double velocity; //　速度
    private double angle; //方向
    private String owner; // 所有者
    private static long VehicleCode = 0;    //識別番号
    private static final int TURN_LEFT = 0;
    private static final int TURN_RIGHT = 1;

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

    void turn(int turnC){
        switch (turnC){
            case TURN_LEFT:
                this.angle -= 90;
                break;
            case TURN_RIGHT:
                this.angle += 90;
                break;
            default:
                break;
        }
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
        Vehicle car = new Vehicle("Aqua" ,50,0,args[0]);
        car.ShowField();
    }


}
