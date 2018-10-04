package jpl.ch07.ex03;

public class PascalsTriangle {
    private int pascal[][];
    public PascalsTriangle(int num) {
        pascal = new int[num][];
        for(int i=0,j=0;i<pascal.length;i++){
            pascal[i] = new int[i+1];
            pascal[i][j]=1;
            pascal[i][i]=1;
        }
        calc();
    }
    public int[][] calc(){
        for(int j=2;j<pascal.length;j++)
            for(int i=1;i<pascal[j].length-1;i++)
                pascal[j][i]=pascal[j-1][i-1]+pascal[j][i-1];
        return pascal;
    }
    public void show(){
        for (int[] array : pascal) {
            for (int i : array)
                System.out.printf(" %d ", i);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        PascalsTriangle pt = new PascalsTriangle(12);
        pt.show();
    }
}
