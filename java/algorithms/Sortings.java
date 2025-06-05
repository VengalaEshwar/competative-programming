import java.util.Arrays;
import java.util.PriorityQueue;

public class Sortings {
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void bubbleSort(int[] arr){
        int n=arr.length;
        for(int i=0;i<n-1;i++){
            for(int j=0;j<=i;j++){
                if(arr[j]>arr[j+1]){
                    swap(arr,j+1,j);
                }
            }
        }
    }
    private static  void selectionSort(int[] arr){
        int n=arr.length;
        for(int i=0;i<n-1;i++){
            int index=i;
            for(int j=i;j<n;j++){
                if(arr[index]>arr[j]){
                    index=j;
                }
            }
            if(index!=i){
                swap(arr,i,index);
            }
        }
    }
    private static void insertionSort(int[] arr){
        int n=arr.length;
        for(int i=1;i<n;i++){
            int val=arr[i];
            int j=i-1;
            for(;j>=0 && arr[j]>val;j--){
                arr[j+1]=arr[j];
            }
            arr[j+1]=val;
        }
    }
    private static void merge(int[] arr,int low,int mid,int high){
        int[] brr = new int[high-low+1];
        int k=0;
        int i=low;
        int j=mid+1;
        while(i<=mid && j<=high){
            if(arr[i]<arr[j]){
                brr[k++]=arr[i++];
            }else{
                brr[k++]=arr[j++];
            }
        }
        while(i<=mid){
            brr[k++]=arr[i++];
        }
        while(j<=high){
            brr[k++]=arr[j++];
        }
        k=0;
        while(low<=high){
            arr[low++]=brr[k++];
        }
    }
    private static void mergeSort(int[] arr,int low,int high){
        if(low>=high)
        return;
        int mid = low + (high-low)/2;
        mergeSort(arr,low,mid);
        mergeSort(arr,mid+1,high);
        merge(arr,low,mid,high);
    }
    private static void mergeSort(int[] arr){
        mergeSort(arr,0,arr.length-1);
    }
    private static int findPivot(int[] arr,int low,int high){
       int pivot=arr[low];
       int i=low+1;
       int j=high;
       do{
        while(arr[i]<=pivot) i++;
        while(pivot<arr[j]) j--;
        if(i<j){
            swap(arr,i,j);
        }
    }while(i<j);
    swap(arr,low,j);
    return j;
    }
    private static void quickSort(int[] arr,int low,int high){
        if(low>=high)
        return ;
        int pivot=findPivot(arr,low,high);
        quickSort(arr,low,pivot-1);
        quickSort(arr, pivot+1, high);
    }
    private static void quickSort(int[] arr){
        quickSort(arr,0,arr.length-1);
    }
    private static void heapSort(int[] arr){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int num : arr) pq.offer(num);
        int i=0;
        while(!pq.isEmpty()) arr[i++]=pq.poll();
    }
    public static void main(String[] args) {
        int[] arr = { 5, 8, 1, 8, 2, 4, 11 };
        // bubbleSort(arr);
        // selectionSort(arr);
        // insertionSort(arr);
        // mergeSort(arr);
        // quickSort(arr);
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}