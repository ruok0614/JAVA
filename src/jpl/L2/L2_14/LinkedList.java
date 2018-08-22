package jpl.L2.L2_14;

/**
 * 練習問題 2.14
 * 　LinkedListのクラスのフィールドをprivateにして，フィールドに対するアクセッサーメソッドを追加しなさい．
 *
 * どのフィールドが変更を許すメソッドを持ち，どのフィールドがそのようなメソッドを持つべきではないですか．
 *
 * 変更を許さないフィールド：Obj
 * 変更を許すメソッド：nextList
 **/

public class LinkedList {
    private Object obj;
    private LinkedList nextList;

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

    void SetObj(Object obj){
        this.obj = obj;
    }

    public String toString(){
        String desc = obj.toString();
        if(nextList != null){
            desc += "\n" + nextList.toString();
        }
        return desc;
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

        System.out.println(vehicleList1);

    }


}
