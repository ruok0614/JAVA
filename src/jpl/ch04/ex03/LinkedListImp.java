package jpl.ch04.ex03;


/**
 * 練習問題 4.3
 *  LinkedListはインターフェースであるべきか．
 *  またLinkedListをインターフェースを使用して書き直しなさい
 *
 *  A.
 *  多重継承が必要でもないし，インターフェースである必要はない
 *
 *
 *
 **/


public class LinkedListImp implements LinkedList<LinkedList>{
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

    public int getLength(){
        if (nextList == null){
            return 1;
        }else {
            // ネストの数だけ+1する
            return 1 + nextList.getLength();
        }
    }

    public void setObj(Object obj){
        this.obj = obj;
    }

    public void setNextList(LinkedList next){
        nextList = next;
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

        LinkedList vehicleList1 = new LinkedListImp();
        LinkedList vehicleList2 = new LinkedListImp();
        LinkedList vehicleList3 = new LinkedListImp();

        vehicleList1.setObj(car);
        vehicleList1.setNextList(vehicleList2);
        vehicleList2.setObj(bicycle);
        vehicleList2.setNextList(vehicleList3);
        vehicleList3.setObj(airplane);
        vehicleList3.setNextList(null);

        System.out.println(vehicleList1);
        System.out.println("length : " + vehicleList1.getLength());

    }


}
