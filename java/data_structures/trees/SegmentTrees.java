class SegmentTree{
    //assuming we are dealing with range maxing queries
    int[] seg;
    int n;
    SegmentTree(int[] arr){
        n=arr.length;
        seg = new int[4*n];
        build(arr,0,n-1,0);
    }
    private void build(int[] arr,int low,int high,int index){
        if(low==high){
            //base condition a leaf node holds the actual data
            seg[index]=arr[low];
            return;
        }
        int mid = low + (high-low)/2;
        build(arr,low,mid,2*index+1);
        build(arr,mid+1,high,2*index+2);
        //now what should be the current index value it is based  on it child nodes
        seg[index] = Math.max(seg[2*index + 1],seg[2*index + 2]);
    }
    private int query(int left,int right,int start,int end,int index){
        // 3 conditions will be there 
        //[left,right,start,end] or [start,end,left,right] --> not in the range
        if(right<start || end<left)
        return Integer.MIN_VALUE;
        //[left,start,end,right] -> it is one segment int the range
        if(left<=start && end<=right)
        return seg[index];
        //[start,left,right,end] -> we need to find more
        int mid = start + (end-start)/2;
        int first = query(left,right,start,mid,2*index + 1);
        int second = query(left,right,mid+1,end,2*index + 2);
        return Math.max(first, second);
    }
    public int query(int l,int r){
        return query(l,r,0,n-1,0);
    }
    private void update(int left,int right,int index,int target,int val){
        if(left==right){
            seg[index]=val;
            return;
        }
        int mid = left + (right-left)/2;
        if(target<=mid){
            update(left,mid,2*index+1,target,val);
        }else{
            update(mid+1,right,2*index+2,target,val);
        }
        seg[index] = Math.max(seg[2*index + 1],seg[2*index + 2]);
    }
    public void update(int index,int val){
        update(0,n-1,0,index,val);
    }
}
class SegmentTreeLazy{
    int[] seg;
    int[] lazy;
    int n;
    SegmentTreeLazy(int[] arr){
        n = arr.length;
        seg = new int[4*n];
        lazy = new int[4*n];
        build(arr,0,n-1,0);
    }
    private void build(int[] arr,int low,int high,int index){
        if(low ==  high){
            seg[index]=arr[low];
            return;
        }
        int mid = low + (high-low)/2;
        build(arr,low,mid,2*index+1);
        build(arr,mid+1,high,2*index+2);
        seg[index] = Math.max(seg[2*index + 1],seg[2*index + 2]);
    }
    public  int query(int left,int right){
        return query(0,n-1,left,right,0);
    }
    private int query(int low,int high,int left,int right,int index){
        //[low,high,left,right] or [left,right,low,high] --> not in range
        if(high<left || right<low)
        return Integer.MIN_VALUE;
        //[low,left,right,high]
        updateLazy(index,low,high);
        if(left<=low && high<=right)
        return seg[index];
        int mid = low + (high-low)/2;
        int l = query(low,mid,left,right,2*index+1);
        int r = query(mid+1,high,left,right,2*index+2);
        return Math.max(l,r);
    }
    public  void update(int left,int right,int val){
        update(0,n-1,left,right,0,val);
    }
    private void update(int low,int high,int left,int right,int index,int val){
        //[low,high,left,right] or [left,right,low,high] --> not in range
        if(high<left || right<low)
        return;
        updateLazy(index,low,high);

        if(left<=low && high<=right){
            seg[index]=val;
            if(low!=high){
                lazy[2*index+1]=val;
                lazy[2*index+2]=val;
            }
            return;
        }
        int mid = low + (high-low)/2;
        update(low,mid,left,right,2*index+1,val);
        update(mid+1,high,left,right,2*index+2,val);
        seg[index]=Math.max(seg[2*index+1],seg[2*index+2]);

    }
    private void updateLazy(int index,int low,int high){
        if(lazy[index]==0)
        return;
        seg[index]=lazy[index];
        if(low!=high){
                lazy[2*index+1]=lazy[index];
                lazy[2*index+2]=lazy[index];
            }
        lazy[index]=0;
    }
}

public class SegmentTrees {
    public static void main(String[] args) {
        int[] arr = new int[]{6,3,12,62,62,3,4};
        // SegmentTree sg = new SegmentTree(arr);
        // System.out.println(sg.query(0,7));
        // sg.update(1, 10000);
        // System.out.println(sg.query(0,7));
        SegmentTreeLazy sgl = new SegmentTreeLazy(arr);
        // System.out.println(sgl.query(0, 2));
        sgl.update(0, 2,19);
        for(int i=0;i<arr.length;i++)
        System.out.println(sgl.query(i,i));

    }
}
