import java.util.ArrayList;

/**
 * Created by jiantaozhang on 2017/3/30.
 */
public class FourWayOptimalHeap {
    public ArrayList<PriorityQueue_Node> fourWayHeap;

    public FourWayOptimalHeap() {
        fourWayHeap = new ArrayList<>();
        fourWayHeap.add(new PriorityQueue_Node(-1, -1));
        fourWayHeap.add(new PriorityQueue_Node(-1, -1));
        fourWayHeap.add(new PriorityQueue_Node(-1, -1));
    }



    public void insert(PriorityQueue_Node insertedNode) {

        if (this.fourWayHeap.size() -3 == 0) {
            fourWayHeap.add(insertedNode);
        } else {
            fourWayHeap.add(insertedNode);
            int position = fourWayHeap.size()-1;
            goUp(position);
        }
    }

    public PriorityQueue_Node removeMin() {
        PriorityQueue_Node minNode = null;
        if (this.fourWayHeap.size() -3 == 0) {
            System.out.println("Empty heap, cannot remove");
            minNode = null;
        } else if (this.fourWayHeap.size() -3 == 1) {
            minNode = fourWayHeap.get(3);
            fourWayHeap.remove(3);
        } else if (fourWayHeap.size() -3 >= 2) {
            minNode = fourWayHeap.get(3);
            int last_position = this.fourWayHeap.size() - 1;
            if (fourWayHeap.get(last_position) != null) {
                swap(last_position, 3);
                this.fourWayHeap.remove(last_position);
                goDown(3);
            }
        }

        return minNode;
    }

    public void goUp(int position) {
        if (position >= fourWayHeap.size()) {
            System.out.println("Cannot find this node");
        } else {
            int par_position = (position - 4) / 4 + 3;
            if (par_position >= 3 && fourWayHeap.get(position).freq < fourWayHeap.get(par_position).freq) {
                swap(position, par_position);
                if (par_position > 3) {
                    goUp(par_position);
                }
            }
        }
    }

    public void goDown(int position) {
        if (position >= fourWayHeap.size()) {
            System.out.println("get a wrong positon");
        } else {
            int first_pos = (position - 3) * 4 + 4;
            int fourth_pos = first_pos + 3;
            int small = 0;
            int small_pos = -1;

            if (first_pos <= fourWayHeap.size() - 1) {
                for(int i = first_pos; i <= fourth_pos; i++) {
                    if (i <= fourWayHeap.size() - 1) {
                        if (fourWayHeap.get(position).freq - fourWayHeap.get(i).freq > small
                                || (fourWayHeap.get(position).freq - fourWayHeap.get(i).freq == small
                                && fourWayHeap.get(i).isLeaf)) {
                            small = fourWayHeap.get(position).freq - fourWayHeap.get(i).freq;
                            small_pos = i;
                        }
                    }
                }
                if (small_pos != -1) {
                    swap(small_pos, position);
                    goDown(small_pos);
                }
            }
        }
    }


    public void swap(int bottom, int up) {
        PriorityQueue_Node temp = fourWayHeap.get(bottom);
        fourWayHeap.set(bottom, fourWayHeap.get(up));
        fourWayHeap.set(up, temp);
    }
}
