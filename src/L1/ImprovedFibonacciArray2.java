package L1;

/**
 * 数列の値と偶数判定を持つクラス
 */


public class ImprovedFibonacciArray2 {
   public int num;
   public boolean isEvenNumber;

   public void checkEvenNumber(){
       if (this.num % 2 == 0){
           this.isEvenNumber = true;
       }else {
           this.isEvenNumber = false;
       }

    }

    public void setNum(int num){
       this.num = num;
       checkEvenNumber();
    }

}
