import java.util.ArrayList;

/**
 * Created by jiantaozhang on 2017/3/28.
 */
public class Binary_Heap {
    public ArrayList<PriorityQueue_Node> binaryHeap;

    public Binary_Heap() {
        binaryHeap = new ArrayList<>();
    }



    public void insert(PriorityQueue_Node insertedNode) {

        if (this.binaryHeap.size() == 0) {
            binaryHeap.add(insertedNode);
        } else {
            binaryHeap.add(insertedNode);
            int position = binaryHeap.size()-1;
            goUp(position);
        }
    }

    public PriorityQueue_Node removeMin() {
        PriorityQueue_Node minNode = null;
        if (this.binaryHeap.size() == 0) {
            System.out.println("Empty heap, cannot remove");
            minNode = null;
        } else if (this.binaryHeap.size() == 1) {
            minNode = binaryHeap.get(0);
            binaryHeap.remove(0);
        } else if (binaryHeap.size() >= 2) {
            minNode = binaryHeap.get(0);
            int last_position = this.binaryHeap.size() - 1;
            if (binaryHeap.get(last_position) != null) {
                swap(last_position, 0);
                this.binaryHeap.remove(last_position);
                goDown(0);
            }
        }

        return minNode;
    }

    public void goUp(int position) {
        if (position >= binaryHeap.size()) {
            System.out.println("Cannot find this node");
        } else {
            int par_position = (position - 1) / 2;
            if (par_position >= 0 && binaryHeap.get(position).freq < binaryHeap.get(par_position).freq) {
                swap(position, par_position);
                if (par_position > 0) {
                    goUp(par_position);
                }
            }
        }
    }

    public void goDown(int position) {
        if (position >= binaryHeap.size()) {
            System.out.println("get a wrong positon");
        } else {
            int first_pos = position * 2 + 1;
            int fourth_pos = first_pos + 1;
            int small = 0;
            int small_pos = -1;

            if (first_pos <= binaryHeap.size() - 1) {
                for(int i = first_pos; i <= fourth_pos; i++) {
                    if (i <= binaryHeap.size() - 1) {
                        if (binaryHeap.get(position).freq - binaryHeap.get(i).freq > small
                                || (binaryHeap.get(position).freq - binaryHeap.get(i).freq == small
                                && binaryHeap.get(i).isLeaf)) {
                            small = binaryHeap.get(position).freq - binaryHeap.get(i).freq;
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
        PriorityQueue_Node temp = binaryHeap.get(bottom);
        binaryHeap.set(bottom, binaryHeap.get(up));
        binaryHeap.set(up, temp);
    }
}
