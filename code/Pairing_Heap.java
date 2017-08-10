/**
 * Created by jiantaozhang on 2017/3/28.
 */
public class Pairing_Heap {
    int size;
    PriorityQueue_Node root;

    public Pairing_Heap() {
        size = 0;
        this.root = null;
    }

    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void insert(PriorityQueue_Node insertedNode) {
        if (this.size == 0) {
            this.root = insertedNode;
            root.prev = root;
            root.next = root;
        } else {
            if (insertedNode.freq < root.freq) {
                PriorityQueue_Node secNode = root;
                root = insertedNode;
                root.child = secNode;
                root.prev = root;
                root.next = root;
            } else if (insertedNode.freq >= root.freq) {
                if (root.child == null) {
                    root.child = insertedNode;
                    insertedNode.next = insertedNode;
                    insertedNode.prev = insertedNode;
                } else {
                    insertedNode.next = root.child;
                    root.child.prev = insertedNode;
                    insertedNode.prev = insertedNode;
                    root.child = insertedNode;
                }
            }
        }
        size++;
    }

    public PriorityQueue_Node removeMin() {
        if (this.size <= 0) {
            System.out.println("Error, empty heap");
            return null;
        } else {
            PriorityQueue_Node rootNode = root;
            int num_child = num_child(root);

            if (num_child == 0) {
                this.root = null;
            } else if (num_child == 1) {
                this.root = root.child;
            } else {
                PriorityQueue_Node[] nodeSet = new PriorityQueue_Node[num_child];
                PriorityQueue_Node firstNode = root.child;
                for(int i = 0; i < num_child; i++) {
                    nodeSet[i] = firstNode;
                    firstNode = firstNode.next;
                }

                if (num_child % 2 == 0) {
                    for (int i = 0; i < num_child/2; i++) {
                        nodeSet[i] = meld(nodeSet[2 * i], nodeSet[2 * i + 1]);
                    }
                    for(int i = num_child/2 - 1; i > 0; i--) {
                        nodeSet[i - 1] = meld(nodeSet[i], nodeSet[i - 1]);
                    }
                }

                if (num_child % 2 == 1) {
                    for (int i = 0; i < num_child/2; i++) {
                        nodeSet[i] = meld(nodeSet[2 * i], nodeSet[2 * i + 1]);
                    }
                    nodeSet[num_child / 2] = nodeSet[num_child - 1];
                    for (int i = num_child /2; i > 0; i--) {
                        nodeSet[i - 1] = meld(nodeSet[i], nodeSet[i - 1]);
                    }
                }

                this.root = nodeSet[0];
            }

            size--;
            return rootNode;
        }
    }

    public PriorityQueue_Node meld(PriorityQueue_Node firstNode, PriorityQueue_Node secNode) {

        firstNode.prev = firstNode;
        firstNode.next = firstNode;
        secNode.prev = secNode;
        secNode.next = secNode;

        if (firstNode.freq < secNode.freq) {
            if (firstNode.child == null) {
                firstNode.child = secNode;
                return firstNode;
            } else {
                firstNode.child.prev = secNode;
                secNode.next = firstNode.child;
                firstNode.child = secNode;
                return firstNode;
            }
        } else {
            if (secNode.child == null) {
                secNode.child = firstNode;
                return secNode;
            } else {
                secNode.child.prev = firstNode;
                firstNode.next = secNode.child;
                secNode.child = firstNode;
                return secNode;
            }
        }
    }

    public int num_child(PriorityQueue_Node PQNode) {
        if (PQNode.child == null) {
            return 0;
        } else {
            int num = 1;
            PriorityQueue_Node next = PQNode.child;
            while (next.hasNext()) {
                num++;
                next = next.next;
            }
            return num;
        }
    }

}
