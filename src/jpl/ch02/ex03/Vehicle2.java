package jpl.ch02.ex03;

/**
 * 練習問題 2.3
 * 　次の乗り物の識別番号を保持するstaticフィールドと，車単位でID番号を保持するための
 * 非staticフィールドをVehicleクラスに追加しなさい
 */
public class Vehicle2 {
    double velocity; //　速度
    double angle; // 角度
    String owner; // 所有者
    static long VehicleCode; //乗り物の識別番号
    long carID; // 車のID

}
