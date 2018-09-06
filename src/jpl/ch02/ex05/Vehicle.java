package jpl.ch02.ex05;

/**
 * 練習問題 2.5
 * 　vehicleクラスにmainメゾッドを書いて，数個の乗り物を作成し，
 * それらのオブジェクトのフィールドの値を表示するようにしなさい
 **/
public class Vehicle {
    String name;    //名前
    double velocity; //　速度
    double angle; //方向
    String owner; // 所有者

    Vehicle(String name,double velocity,double angle, String owner){
        this.name = name;
        this.velocity = velocity;
        this.angle = angle;
        this.owner = owner;
    }

    void ShowField(){
        System.out.println(this.name);
        System.out.println("速度:" + this.velocity);
        System.out.println("方向:" + this.angle);
        System.out.println("所有者:" + this.owner);
        System.out.println();
    }

    public static void main(String[] args){
        Vehicle car = new Vehicle("車",40 , 1, "Taro");
        Vehicle bicycle = new Vehicle("自転車",12,1,"Ichiro");
        Vehicle airplane = new Vehicle("飛行機",200,-1,"Ebizo");

        car.ShowField();
        bicycle.ShowField();
        airplane.ShowField();

    }

}
