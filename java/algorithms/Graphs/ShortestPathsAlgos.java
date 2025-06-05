import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
public class ShortestPathsAlgos {
    private static int bellmanFord(int[][] edges,int src,int dest,int n){
        int[] dist = new int[n];
        Arrays.fill(dist,(int)1e8); // It act as an infinite number adjust based on conrtaints
        dist[src]=0;
        // we need to iterate only for n-1 times
        //relaxation
        for(int i=0;i<n-1;i++){
            for(int[] edge : edges){
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];
                if(dist[u]!=(int)1e8 && dist[u]+wt < dist[v]){
                    dist[v]=dist[u]+wt;
                }
            }
        }
        //for finding negetive cycles , whenever we are updating any dist it mean it exist of negetive cycle
        for(int[] edge : edges){
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];
                if(dist[u]!=(int)1e8 && dist[u]+wt < dist[v]){
                   return -1;
                }
            }
        return dist[dest];
    }
    private int[][] floydWarshell(List<List<int[]>> adj){
        int n =adj.size();
        int[][] dist = new int[n][n];
        for(int[] d : dist) Arrays.fill(d,Integer.MAX_VALUE);
        for(int i=0;i<n;i++){
            for(int[] j : adj.get(i)){
                dist[i][j[0]]=j[1];
            }
            dist[i][i]=0;
        }
        for(int via=0;via<n;via++){
            for(int v=0;v<n;v++){
                for(int u=0;u<n;u++){
                    if(dist[u][via]!=Integer.MAX_VALUE && dist[via][v]!=Integer.MAX_VALUE && dist[u][via]+dist[via][v]<dist[u][v] ){
                        dist[u][v]=dist[u][via]+dist[via][v];
                    }
                }
            }
        }
        return dist;
    }
    private static int dijkstra(List<List<int[]>> adj,int src,int dest){
        int n=adj.size();
        int[] dist = new int[n];
        Arrays.fill(dist,(Integer.MAX_VALUE/2)-20); // It act as an infinite number adjust based on conrtaints
        dist[src]=0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->dist[a]-dist[b]);
        pq.offer(src);
        while(!pq.isEmpty()){
            int node = pq.poll();
            int currDist = dist[node];
            for(int[] ngData : adj.get(node)){
                int wt = ngData[1];
                int ng = ngData[0];
                int newDist = currDist+wt;
                if(newDist<dist[ng]){
                    dist[ng]=newDist;
                    pq.offer(ng);
                }
            }
        }
        return dist[dest];
    }
    public static void main(String[] args) {
        int n = 5; // number of nodes (0 to 4)
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Sample graph:
        // 0 --1--> 1
        // 0 --4--> 2
        // 1 --1--> 2
        // 1 --6--> 3
        // 2 --1--> 3
        // 3 --1--> 4

        adj.get(0).add(new int[]{1, 1});
        adj.get(0).add(new int[]{2, 4});
        adj.get(1).add(new int[]{2, 1});
        adj.get(1).add(new int[]{3, 6});
        adj.get(2).add(new int[]{3, 1});
        adj.get(3).add(new int[]{4, 1});
        int[][] edges = {
    {0, 1, 1},
    {0, 2, 4},
    {1, 2, 1},
    {1, 3, 6},
    {2, 3, 1},
    {3, 4, -10}
};
        int src = 0;
        int dest = 4;

        // int shortestDist = dijkstra(adj, src, dest);
        // int shortestDist = dijkstra(adj, src, dest);
        int shortestDist = bellmanFord(edges, src, dest, n);
        System.out.println("Shortest distance from " + src + " to " + dest + " is: " + shortestDist);
    }
}
