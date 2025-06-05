import java.util.ArrayList;
import java.util.List;
public class Subsets {
    private static void subsets(String p, String up, List<String> res) {
        if(up.isEmpty()){
            res.add(p);
            return;
        }
        subsets(p+up.charAt(0), up.substring(1), res);
        subsets(p, up.substring(1), res);
    }
    private static List<String> subsetsOfString(String s){
        List<String> res = new ArrayList<>();
        subsets("",s,res);
        return res;
    }
    private static List<List<Integer>> subsetsOfNumbers(int[] arr){
        int n = arr.length;
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for(int a : arr){
            int size = res.size();
            for(int i=0;i<size;i++){
                List<Integer> temp = new ArrayList<>(res.get(i));
                temp.add(a);
                res.add(temp);
            }
        }
        return res;
    }
    public static void main(String[] args) {
        List<List<Integer>> res = subsetsOfNumbers(new int[]{1,2,3});
        System.out.println(res);
        List<String> ls = subsetsOfString("abc");
        System.out.println(ls);
    }

}
