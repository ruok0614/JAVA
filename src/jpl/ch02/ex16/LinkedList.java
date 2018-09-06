package jpl.ch02.ex16;


/**
 * 練習問題 2.16
 *  リスト内の要素数を返すメソッドをLinkedListに追加せよ
 *
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

    int getLength(){
        if (nextList == null){
            return 1;
        }else {
            // ネストの数だけ+1する
            return 1 + nextList.getLength();
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
        System.out.println("length : " + vehicleList1.getLength());

    }


}
