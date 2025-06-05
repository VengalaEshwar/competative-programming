class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val){
        this.val=val;
    }
}
public class BST {
    private static  TreeNode insert(TreeNode root,int val){
        if(root==null)
        return new TreeNode(val);
        if(val<=root.val)
        root.left = insert(root.left,val);
        else
        root.right = insert(root.right, val);
        return root;
    }
    private static int getMin(TreeNode node){
        TreeNode pre=node;
        while(node!=null){
            pre=node;
            node=node.left;
        }
        return pre.val;
    }
    private static TreeNode delete(TreeNode root,int val){
        if(root==null)
        return null;
        if(val==root.val){
            if(root.left==null && root.right==null)
            return null;
            if(root.left==null)
            return root.right;
            if(root.right == null)
            return root.left;
            int change = getMin(root.right);
            root.val=change;
            root.right=delete(root.right,change);
            return root;
        }
        if(val<root.val)
        root.left = delete(root.left, val);
        else
        root.right = delete(root.right, val);
        return root;
    }
    private static void print(TreeNode root){
        if(root==null)
        return;
        print(root.left);
        System.out.print(root.val+"->");
        print(root.right);
    }
    public static void main(String[] args) {
        TreeNode root=null;
        int val=0;
        for(int i=0;i<10;i++){
            val=(int)(100*Math.random());
            root=insert(root,val);
        }
        print(root);
        System.out.println();
        root=delete(root, val);
        print(root);
        System.out.println();
    }
}
