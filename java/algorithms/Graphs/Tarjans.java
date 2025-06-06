import java.util.ArrayList;
import java.util.List;
//https://leetcode.com/problems/critical-connections-in-a-network/description/
public class Tarjans {
    int time=0;
    public List<List<Integer>> tarjans(int n, List<List<Integer>> edges) {
        List<List<Integer>> res = new ArrayList<>();
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0;i<n;i++)
        adj.add(new ArrayList<>());
        for(List<Integer> edge : connections){
            int a = edge.get(0);
            int b = edge.get(1);
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        boolean[] isVisited = new boolean[n];
        int[] tin = new int[n];
        int[] low = new int[n];
        tarjons(adj,0,-1,isVisited,tin,low,res);
        return res;
    }
    private void tarjons(List<List<Integer>> adj,int node,int parent,boolean[] isVisited,int[] tin,int[] low,List<List<Integer>> res){
        isVisited[node]=true;
        time++;
        low[node]=tin[node]=time;
        for(int ng : adj.get(node)){
            if(ng==parent)
            continue;
            if(isVisited[ng]){
                // it means it must be the before node travelled
                low[node]=Math.min(low[node],low[ng]);
            }else{
                //go further
                tarjons(adj,ng,node,isVisited,tin,low,res);
                // now update low[node] 
                low[node]=Math.min(low[node],low[ng]);
                //check whether it forms bridge with the child
                if(tin[node]<low[ng]){ //#caution with this condition dont keep (low[node]<low[ng])
                    List<Integer> temp = new ArrayList<>();
                    temp.add(node);
                    temp.add(ng);
                    res.add(temp);
                }
            }
        }
    }
    public static void main(String[] args) {
        
    }
}
