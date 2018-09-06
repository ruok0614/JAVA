package jpl.ch03.ex05;

abstract class Benchmark {
    /* 抽象クラスは実装を書かない　書くのはこのクラスを継承したサブクラスに*/
    abstract void benchmark();

    public final long repeat(int count){
        long start = System.nanoTime();
        for (int i = 1; i < count; i++){
            benchmark();
        }
        return (System.nanoTime() - start);
    }
}
