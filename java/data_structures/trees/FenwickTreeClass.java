import java.util.*;
//binary index tree;
//this is mainly used for subarray sum queries
class BinaryIndexTree{
    int[] bit;
    int n;
    BinaryIndexTree(int[] arr){
        this.n=arr.length;
        bit=new int[n+1];
        build(arr);
    }
    private void build(int[] arr){
        for(int i=1;i<=n;i++)
        {
            update(i,arr[i-1]);
        }
    }
    public void update(int i,int val){
        while(i<=n){
            bit[i]+=val;
            i+=(i&-i);
        }
    }
/*
    sum[i] = bit[all bit weights of i]
    ex
    13(1101)
    sum[13] = bit[8] + bit[4] + bit[1]
*/
   public int query(int i){
        i++;
        int res=0;
        while(i>0){
            res+=bit[i];
            i-=(i&-i); //-i indicates the 2's complement of i
        }
        return res;
   }  
}
public class FenwickTreeClass {
    
}
