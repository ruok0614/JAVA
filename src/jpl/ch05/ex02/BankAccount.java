/**
 * 練習問題5.2
 * 口座に対する最後の10個を記録するBankAccountを作成しなさい．
 * historyメソッドを追加して，Historyオブジェクトを返すようにしなさい．
 * HistoryオブジェクトはnextメソッドでActionオブジェクトを1つ返して，
 * リストの最後ではnullを返すようにしなさい．Historyはネストしたクラスにすべきか．
 * すべきならstaticにすべきか．
 */

package jpl.ch05.ex02;

import jpl.ch03.ex06.Battery;

public class BankAccount {
    private long number;
    private long balance;
    private Action lastAct;
    private History history;

    BankAccount(long number,long balance){
        this.history = new History();
        this.number = number;
        this.balance = balance;
    }

    public class Action{
        private String act;
        private long amount;
        Action(String act, long amount){
            this.act = act;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return String.format("%d: %s %d",number,act,amount);
        }
    }

    public class History{

        private int index;
        private Action[] array;
        final private int maxIndex = 10;
        {
            array = new Action[maxIndex];
        }
        History(){
            index = 10;
        };
        History(Action act){
            add(act);
        }

        public void add(Action action){
            for (int i = maxIndex -1; i != 0; i--){
                this.array[i] = this.array[i - 1];
            }
            this.array[0] = action;
        }
        public Action next(){
            if(index < 0){
                return null;
            }else {
                Action action = array[index];
                index--;
                return action;
            }
        }

    }
    public void deposit(long amount){
        balance += amount;
        lastAct = new Action("deposit",amount);
        history.add(lastAct);
    }

    public void withdraw(long amount){
        balance -= amount;
        lastAct = new Action("withdraw",amount);
        history.add(lastAct);
    }

    public History history(){
        return history;
    }

    public static void main(String args[]){
        BankAccount bank1 = new BankAccount(123456,1000000);
        for(int i = 0; i < 10; i++){
            bank1.deposit(i*100);
        }
        System.out.println("a");

    }
}
