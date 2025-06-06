import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
public class Kosaraju{
private static void sort(int node,List<List<Integer>> adj,boolean[] isVisited,Stack<Integer> st){
    isVisited[node]=true;
    for(int ng : adj.get(node)){
        if(!isVisited[ng]){
            sort(ng,adj,isVisited,st);
        }
    }
    st.push(node);
}
private static void dfs(int node,List<List<Integer>> adj,boolean[] isVisited){
    isVisited[node]=true;
    for(int ng : adj.get(node)){
        if(!isVisited[ng]){
            dfs(ng,adj,isVisited);
        }
    }
}
private int kosaraju(List<List<Integer>> adj){
    int n=adj.size();
    //step-1 : sort based on finishing time
    //scc1->scc2->ss3->ss4
    boolean[] isVisited = new boolean[n];
    Stack<Integer> st = new Stack<>();
    for(int i=0;i<n;i++){
        if(!isVisited[i]){
            sort(i,adj,isVisited,st);
        }
    }
    //step-2 : reverse all the nodes direction
    //scc1<-scc2<-scc3<-scc4
    List<List<Integer>> graph = new ArrayList<>();
    for(int i=0;i<n;i++) graph.add(new ArrayList<>());
    for(int i=0;i<n;i++){
        for(int ng : adj.get(i)){
            graph.get(ng).add(i);
        }
    }
    //step-2 perfrom dfs on enitre ordered graph(stack) 1dfs == 1 scc
    Arrays.fill(isVisited,false);
    int res=0;
    while(!st.isEmpty())
    {
        int node = st.pop();
        if(!isVisited[node]){
            res++;
            dfs(node,graph,isVisited);
        }
    }
    return res;
}
public static void main(String[] args) {
    
}
}