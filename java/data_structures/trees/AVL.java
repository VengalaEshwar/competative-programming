class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val){
        this.val=val;
    }
    TreeNode(int val,TreeNode left,TreeNode right){
        this.val=val;
        this.left=left;
        this.right=right;
    }

}
public class AVL {
    private static  TreeNode insert(TreeNode root,int val){
        if(root==null)
        return new TreeNode(val);
        if(val<=root.val)
        root.left = insert(root.left,val);
        else
        root.right = insert(root.right, val);
        return rotate(root);
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
            return rotate(root);
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
    private static int height(TreeNode root){
        if(root==null)
        return 0;
        return 1+Math.max(height(root.left),height(root.right));
    }
    private static TreeNode rotate(TreeNode root)
    {
        if(height(root.left)-height(root.right)>1)
        {
            if(height(root.left.left)-height(root.left.right)>0)
            {
                return rightRotate(root);
            }
            else
            {
                root.left=leftRotate(root.left);
                return rightRotate(root);
            }

        }
        if((height(root.left)-height(root.right)<-1))
        {
            if(height(root.right.right)-height(root.right.left)>0)
            {
                return leftRotate(root);
            }
            else
            {
                root.right=rightRotate(root.right);
                return leftRotate(root);
            }
        }
        return root;
    }
    private static TreeNode rightRotate(TreeNode root) {
    if (root == null || root.left == null) return root;

    TreeNode left = root.left;
    root.left = left.right;
    left.right = root;
    return left;
}

private static TreeNode leftRotate(TreeNode root) {
    if (root == null || root.right == null) return root;

    TreeNode right = root.right;
    root.right = right.left;
    right.left = root;
    return right;
}

    public static void main(String[] args) {
     TreeNode root = null;
    root = insert(root, 10);
    root = insert(root, 20);
    root = insert(root, 30);
    root = insert(root, 40);
    root = insert(root, 50);
    print(root); // Should be balanced AVL
    System.out.println("\nAfter deletion:");
    root = delete(root, 30);
    System.out.println(height(root));
    print(root); // Should still be balanced
    }
}
