import java.util.*;
class Main {
    static int[] fibo;
    public int DFS(int n){
        if(n >= 3) {
            fibo[n-1] = DFS(n - 1) + fibo[n-3];
            return fibo[n-2]+fibo[n-3];
        }
        else
            return fibo[n-1] = 1;
    }

    public static void main(String[] args){
        Main T = new Main();
        int n=45;
        fibo = new int[n+1];

        for(int i=1; i<=n; i++)
            System.out.print(T.DFS(i)+" ");
    }
}