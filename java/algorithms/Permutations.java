import java.util.*;
public class Permutations {
    private static void permutations(String p,String up,List<String> res){
        if(up.isEmpty()){
            res.add(p);
            return;
        }
        String next = up.substring(1);
        char ch = up.charAt(0);
        for(int i=0;i<=p.length();i++){
            String nextP = p.substring(0,i) + ch + p.substring(i);
            permutations(nextP,next,res);
        }
    }
    private static List<String> permutations(String s){
        List<String> res = new ArrayList<>();
        permutations("",s,res);
        return res;
    }
    private static void swap(int[] arr,int i,int j){
        int temp =arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    private static void permutations(int[] arr,int index,List<int[]> res){
        if(index==arr.length){
            res.add(arr.clone());
            return;
        }
        for(int i=index;i<arr.length;i++){
            swap(arr,index,i);
            permutations(arr,index+1,res);
            swap(arr,index,i);
        }
    }
    private static List<int[]> permuations(int[] arr){
        List<int[]> res = new ArrayList<>();
        permutations(arr,0,res);
        return res;
    }
    public static void main(String[] args) {
        List<String> per = permutations("ab");
        System.out.println(per);
        List<int[]> arr = permuations(new int[]{1,2,3});
        System.out.println(Arrays.deepToString(arr.toArray(new int[0][])));
    }
}
