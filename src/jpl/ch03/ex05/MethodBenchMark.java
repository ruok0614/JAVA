package jpl.ch03.ex05;

/**
 * 練習問題　3.5
 *  他のベンチマークを行う新たな拡張したクラスを作成しなさい
 *  たとえば，0からパラメータとして渡された値までループするのに要する時間を計るとか
 */

public class MethodBenchMark extends Benchmark{
    private int loopCount;

    MethodBenchMark(int loopCount){
        setLoopCount(loopCount);
    }

    void setLoopCount(int loopCount){
        this.loopCount = loopCount;
    }

    @Override
    void benchmark(){
        for (int i = 0; i < loopCount; i++ ){

        }
    }


    public static void main(String args[]){
        int count = Integer.parseInt(args[0]);
        int loopCount = Integer.parseInt(args[1]);
        long time = new MethodBenchMark(loopCount).repeat(count);
        System.out.println(count + " method in " + time + " 3nanosecond");
    }
}
