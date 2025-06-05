class ListNode{
    int val;
    ListNode next;
    ListNode(int val){
        this.val=val;
    }
    ListNode(int val,ListNode next){
        this.val=val;
        this.next=next;
    }
}
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
class Stack{
    ListNode top;
    int size=0;
    public void push(int val){
        if(top==null){
            top=new ListNode(val);
        }
        else{
            ListNode node =new ListNode(val);
            node.next=top;
            top=node;
        }
        size++;
    }
    public void pop(){
        if(top!=null){
            ListNode node = top;
            top=top.next;
            node.next=null;
        }
    }
}
public class DataStructures {
    public static void main(String[] args) {
        ListNode node = new ListNode(69);
        System.out.println(node.val);
        TreeNode tree = new TreeNode(10);
        System.out.println(tree.val);
    }
    
}
