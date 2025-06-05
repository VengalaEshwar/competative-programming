import java.util.List;
import java.util.PriorityQueue;
class DisjointSet{
    int n;
    int[] parent;
    int[] size;
    int[] rank;
    DisjointSet(int n){
        this.n = n;
        parent = new int[n];
        size = new int[n];
        rank = new int[n];
        for(int i=0;i<n;i++){
            parent[i]=i;
            size[i]=rank[i]=1;
        }
    }
    public void union(int a,int b){
        // unionByRank(a,b);
        findBySize(a,b);
    }
    private void unionByRank(int a,int b){
        int u_a = find(a);
        int u_b = find(b);
        if(u_a == u_b)
        return;
        if(rank[u_a]<rank[u_b]){
            parent[u_a]=u_b;
        }else if(rank[u_a]>rank[u_b]){
            parent[u_b]=u_a;
        }else {
            rank[u_a]++;
            parent[u_b]=u_a;
        }
    }
    private void findBySize(int a,int b){
        int u_a = find(a);
        int u_b = find(b);
        if(u_a == u_b)
        return;
        if(size[u_a]>size[u_b]){
            parent[u_b]=u_a;
            size[u_a]+=size[u_b];
        }else{
            parent[u_a]=u_b;
            size[u_b]+=size[u_a];
        }
    }
    public int find(int a){
        if(parent[a]==a)
        return a;
        return parent[a]=find(parent[a]);
    }
    public  int getMin(int a){
        for(int i=0;i<n;i++){
            if(parent[i]==a)
            return i;
        }
        return a;
    }
}
public class MST {
    private static int prims(List<List<int[]>> adj){
        int res=0;
        int n=adj.size();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
        pq.offer(new int[]{0,0});
        boolean[] isVisited = new boolean[n];
        while(!pq.isEmpty()){
            int[] temp = pq.poll();
            int node = temp[0];
            int wt = temp[1];
            if(isVisited[node])
            continue;
            isVisited[node]=true;
            res+=wt;
            for(int[] ng : adj.get(node)){
                if(!isVisited[ng[0]]){
                    pq.offer(ng);
                }
            }
        }
        return res;
    }
    private static int  krushkal(List<int[]> edges,int n){
        edges.sort((a,b)->a[2]-b[2]);
        DisjointSet ds = new DisjointSet(n+1);
        int res=0;
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            if(ds.find(v)!=ds.find(u)){
                res+=wt;
                ds.union(u,v);
            }
        }
        return res;
    }
    public static void main(String[] args) {
        
    }
}
