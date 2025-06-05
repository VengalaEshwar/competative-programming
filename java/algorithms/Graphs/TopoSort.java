import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
public class TopoSort {
    private static int[] topoSort1(List<List<Integer>> adj){
        int n=adj.size();
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        boolean[] isVisited = new boolean[n];
        for(int i=0;i<n;i++){
            if(!isVisited[i]){
                topSortRec(adj,i,st,isVisited);
            }
        }
        int i=0;
        while(!st.isEmpty()){
            res[i++]=st.pop();
        }
        return res;
    }
    //kahn's algorithm
    private static int[] topoSort(List<List<Integer>> adj){
        int n = adj.size();
        int[] res = new int[n];
        //preparation of inDegree
        int[] inDegree = new int[n];
        for(int i=0;i<n;i++){
            for(int  ng : adj.get(i)){
                inDegree[ng]++;
            }
        }
        Queue<Integer> q  = new LinkedList<>();
        int k=0;
        for(int i=0;i<n;i++){
            if(inDegree[i]==0)
            q.offer(i);
        }
        while(!q.isEmpty()){
            int node = q.poll();
            res[k++]= node;
            for(int ng : adj.get(node)){
                inDegree[ng]--;
                if(inDegree[ng]==0)
                    q.offer(ng);
            }
        }
        return res;
    }
    private static void topSortRec(List<List<Integer>> adj, int start, Stack<Integer> st, boolean[] isVisited) {
       isVisited[start]=true;
       for(int ng : adj.get(start)){
        if(!isVisited[ng]){
            topSortRec(adj, ng, st, isVisited);
        }
       }
       st.push(start);
    }
    public static void main(String[] args) {
    int n = 6; // Nodes 0 to 5
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < n; i++) {
        adj.add(new ArrayList<>());
    }

    // Graph edges as per above
    adj.get(5).add(2);
    adj.get(5).add(0);
    adj.get(4).add(0);
    adj.get(4).add(1);
    adj.get(2).add(3);
    adj.get(3).add(1);

    // int[] topoOrder = topoSort1(adj); // You can also run this for all unvisited nodes to cover all components
    int[] topoOrder = topoSort(adj); // You can also run this for all unvisited nodes to cover all components

    System.out.print("Topological Order starting from 5: ");
    for (int node : topoOrder) {
        System.out.print(node + " ");
    }
}
}
