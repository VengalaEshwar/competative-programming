import java.util.*;
// an articualtion point is something on removing that points it divides the graph into 2 more indvidual components
// we need to ensure with the starting node 
public class ArticulationPoints {
    public static List<Integer> articulationPoints(int n, List<List<Integer>> adj) {
        List<Integer> res = new ArrayList<>();
        boolean[] isVisited= new boolean[n];
        int[] tin = new int[n];
        int[] low = new int[n];
        int[] mark = new int[n];
        for(int i=0;i<n;i++){
            if(!isVisited[i]){
                articulationPoints(adj,i,-1,tin,low,isVisited,mark);
            }
        }
        for(int i=0;i<n;i++){
            if(mark[i]==1){
                res.add(i);
            }
        }
        if(res.size()==0)
        {
            res.add(-1);
            return res;
        }
        return res;
    }
    private static int timer=0;
    private static void articulationPoints(List<List<Integer>> adj,int node,int parent,int[] tin,int[] low,boolean[] isVisited,int[] mark){
        isVisited[node]=true;
        timer++;
        low[node]=tin[node]=timer;
        int child=0;
        for(int ng : adj.get(node)){
            if(ng==parent)
            continue;
            if(!isVisited[ng]){
            articulationPoints(adj,ng,node,tin,low,isVisited,mark);
            low[node]=Math.min(low[node],low[ng]);
            if(tin[node]<=low[ng] && parent!=-1){
                //we are marking it as a_point instead of directly adding into res bcz we may have same point for many components and get duplicates
                mark[node]=1;
            } 
            child++;
            }else{
                low[node]=Math.min(low[node],tin[ng]);
            }
        }
        //for starting node if child>1 it means it an articulation point
        if(child>1 && parent==-1){
            mark[node]=1;
        }
    }
    public static void main(String[] args){
        System.out.println("working");
    }
}