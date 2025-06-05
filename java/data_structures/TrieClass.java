//Trie or prefix tree
class TrieNode{
    TrieNode[] child = new TrieNode[26];
    boolean flag=false; //decides as end of the word 
    int count=0; //if it contains duplicate better of using count 
}
class Trie{
    TrieNode root;
    Trie(){
        root = new TrieNode();
    }
    public void insert(String s)
    {
        TrieNode node = root;
        for(char i : s.toCharArray()){
            int ch = i-'a';
            if(node.child[ch]==null){
                node.child[ch]=new TrieNode();
            }
            node = node.child[ch];
        }
        node.flag=true;
        node.count++;
    }
    public boolean search(String s){
        TrieNode node = root;
        for(char i : s.toCharArray() ){
            int ch = i-'a';
            if(node.child[ch]==null)
            return false;
            node = node.child[ch];
        }
        return node.flag;
    }
    public boolean startsWith(String s){
        TrieNode node = root;
        for(char i : s.toCharArray() ){
            int ch = i-'a';
            if(node.child[ch]==null)
            return false;
            node = node.child[ch];
        }
        return true;
    }

}
public class TrieClass {
    
}
