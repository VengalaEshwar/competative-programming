import java.util.*;
public class Array {    
    //This function also work as ceil of val
    private static int lowerBound(int[] arr,int val){
        // return Math.abs(Arrays.binarySearch(arr,val));
        int low=0;
        int high=arr.length-1;
        int ans=0;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(arr[mid]>=val){
                ans=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return ans;
    }
    //This function also work as floor of val
    private static  int upperBound(int[] arr,int val){
        int low=0;
        int high=arr.length-1;
        int ans=0;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(arr[mid]<=val){
                ans=mid;
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return ans;
    }
    // order-agnostic bs ->which array is sorted in non increasing order.
    public static void main(String[] args) {
        List<Integer> crr = new ArrayList<>();
        for(int i=0;i<10;i++){
            crr.add((int)(Math.random()*100));
        }
        int[][][] arr = {{{1,2}},{{3,4}},{{5,6}}};
        int[][][] brr = {{{1,2}},{{3,4}},{{5,6}}};
        System.out.println(Arrays.toString(arr)); 
        System.out.println(Arrays.deepEquals(arr,brr)); //it act as python string now
        System.out.println(Arrays.equals(arr,brr));
        Arrays.sort(arr,(a,b)->b[0][1]-a[0][1]);
        System.out.println(Arrays.deepToString(arr)); //it act as python string now
         crr.forEach((i)->{System.out.print(i+" ");});
         System.out.println();
        int[] drr = new int[]{1,2,4,5,6,7};
        System.out.println(lowerBound(drr,3));
        System.out.println(upperBound(drr,3));
        Collections.swap(crr,10,9);        
        int n = 7;


        
        int mask = 0b1010010;
        
        // First, build the complement mask
        int full = (1 << n) - 1;   // mask with all bits set
        int unsetMask = full ^ mask;  // bits available (unset)
        
        for (int sub = unsetMask; sub > 0; sub = (sub - 1) & unsetMask) {
            // sub is a subset of unset bits
            int newMask = mask | sub;  
            // do something with newMask
        }



        
    }
}
