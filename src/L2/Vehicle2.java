package L2;

/**
 * 練習問題 2.3
 * 　次の乗り物の識別番号を保持するstaticフィールドと，車単位でID番号を保持するための
 * 非staticフィールドをVehicleクラスに追加しなさい
 */
public class Vehicle2 {
    double velocity;
    double angle;
    String owner;
    static long Vehicle_code; //乗り物の識別番号
    long car_ID; // 車のID

}
