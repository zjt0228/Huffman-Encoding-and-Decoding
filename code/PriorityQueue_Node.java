/**
 * Created by jiantaozhang on 2017/3/28.
 */
public class PriorityQueue_Node {
    public int value;
    public int freq;
    public PriorityQueue_Node left = null;
    public PriorityQueue_Node right = null;
    public boolean isLeaf = true;
    public String code = null;
    public PriorityQueue_Node child = null;
    public PriorityQueue_Node prev = null;
    public PriorityQueue_Node next = null;


    public PriorityQueue_Node(int value, int freq, PriorityQueue_Node lc, PriorityQueue_Node rc, boolean isLeaf) {
        this.value = value;
        this.freq = freq;
        this.left = lc;
        this.right = rc;
        this.isLeaf = isLeaf;
    }

    public PriorityQueue_Node() {
        this.value = -1;
        this.freq = -1;
    }

    public boolean hasNext() {
        if (!this.next.equals(this)) {
            return true;
        } else {
            return false;
        }
    }

    public PriorityQueue_Node(int value, int freq) {
        this.value = value;
        this.freq = freq;
    }
}
