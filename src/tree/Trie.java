package tree;


import java.util.Map;
import java.util.TreeMap;

/**
 * 字典树
 */
public class Trie {

    private class Node {
        public boolean isWord;
        public Map<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new TreeMap<>();
        }

        public Node() {
            this.isWord = false;
        }
    }

    private Node root;
    private int size;

    public Trie() {
        this.root = new Node();
        this.size = 0;
    }

    public int size() {
        return size;
    }

    /**
     * 添加一个单词
     *
     * @param word
     */
    public void add(String word) {
        Node cur = root;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (cur.next.get(chars[i]) != null)
                cur.next.put(chars[i], new Node());
            cur = cur.next.get(chars[i]);
        }
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    /**
     * 查询树中是否包含该单词
     *
     * @param word
     * @return
     */
    public boolean contains(String word) {
        Node cur = this.root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }


    /**
     * 通过前缀查找
     *
     * @param prefix
     * @return
     */
    public boolean isPrefix(String prefix) {
        Node cur = this.root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return true;
    }
}
