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

public class DisjointSetDS{
    public static void main(String[] args) {
        System.out.println(1);
    }
}