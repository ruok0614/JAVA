package jpl.L2.L2_6;

/**
 * 練習問題 2.6
 * 　LinkedListクラスにmainメメソッドを書いて，Vehicle型のオブジェクトを数個作成して，
 * リストの連続したノードに入れなさい
 */

public class LinkedList {
    Object obj;
    LinkedList nextList;

    /* 確認用 */
    public void show(){
        if(obj instanceof Vehicle){
            Vehicle tmpVehicle = (Vehicle) obj;
            tmpVehicle.ShowField();
        }
        /* nextListがnullでないなら次へ　*/
        if (nextList != null){
            nextList.show();
        }
    }

    public static void main(String[] args){
        Vehicle car = new Vehicle("車",40 , 1, "Taro");
        Vehicle bicycle = new Vehicle("自転車",12,1,"Ichiro");
        Vehicle airplane = new Vehicle("飛行機",200,-1,"Ebizo");

        LinkedList vehicleList1 = new LinkedList();
        LinkedList vehicleList2 = new LinkedList();
        LinkedList vehicleList3 = new LinkedList();

        vehicleList1.obj = car;
        vehicleList1.nextList = vehicleList2;
        vehicleList2.obj = bicycle;
        vehicleList2.nextList = vehicleList3;
        vehicleList3.obj = airplane;
        vehicleList3.nextList = null;

        vehicleList1.show();

    }


}
