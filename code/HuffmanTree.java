import java.io.*;
import java.util.HashMap;

/**
 * Created by jiantaozhang on 2017/3/29.
 */
public class HuffmanTree {

    public HashMap<String , Integer> buildFreqTable (String filename){
        HashMap<String, Integer> hm = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            String next;
            while ((next = br.readLine()) != null) {
                if (hm.containsKey(next)) {
                    hm.put(next, hm.get(next) + 1);
                } else {
                    hm.put(next, 1);
                }
            }
            br.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return hm;
    }


    /**
     * Build up a Binary heap, read the freq_table and build up Huffman tree with this heap.
     * @param freq_Table
     * @return Binary heap with only one node of huffman tree root
     */
    public Binary_Heap binaryTest(HashMap<String, Integer> freq_Table) {


        Binary_Heap bh = new Binary_Heap();
        for (String value : freq_Table.keySet()) {
            String val = value;
            int freq = freq_Table.get(value);
            bh.insert(new PriorityQueue_Node(Integer.parseInt(val), freq));
        }

        while (bh.binaryHeap.size() > 1) {

            PriorityQueue_Node leftNode = bh.removeMin();
            PriorityQueue_Node rightNode = bh.removeMin();

            int new_freq = leftNode.freq + rightNode.freq;
            int new_value = leftNode.value + rightNode.value;

            PriorityQueue_Node parNode = new PriorityQueue_Node(new_value, new_freq, leftNode, rightNode, false);
            bh.insert(parNode);
        }
        return bh;
    }


    /**
     * Build up a Pairing heap, read the freq_table and build up Huffman tree with this heap.
     * @param freq_Table
     * @return Pairing heap with only one node of huffman tree root
     */
    public Pairing_Heap pairingTest(HashMap<String, Integer> freq_Table) {

        Pairing_Heap pr = new Pairing_Heap();
        for (String value : freq_Table.keySet()) {
            String val = value;
            int freq = freq_Table.get(value);
            pr.insert(new PriorityQueue_Node(Integer.parseInt(val), freq));
        }

        while (pr.size > 1) {
            PriorityQueue_Node firstNode = pr.removeMin();
            PriorityQueue_Node secNode = pr.removeMin();

            int new_freq = firstNode.freq + secNode.freq;
            int new_value = firstNode.value + secNode.value;

            PriorityQueue_Node par_Node = new PriorityQueue_Node(new_value, new_freq, firstNode, secNode, false);
            pr.insert(par_Node);
        }

        return pr;
    }


    /**
     * Build up a 4-way optimal heap, read the freq_table and build up Huffman tree with this heap.
     * @return FourWay heap with only one node of huffman tree root
     */

    public FourWayOptimalHeap fourWayTest(HashMap<String, Integer> freq_Table) {
        FourWayOptimalHeap fh = new FourWayOptimalHeap();
        for (String value : freq_Table.keySet()) {
            String val = value;
            int freq = freq_Table.get(value);
            fh.insert(new PriorityQueue_Node(Integer.parseInt(val), freq));
        }


        while (fh.fourWayHeap.size() > 4) {

            PriorityQueue_Node leftNode = fh.removeMin();
            PriorityQueue_Node rightNode = fh.removeMin();

            int new_freq = leftNode.freq + rightNode.freq;
            int new_value = leftNode.value + rightNode.value;

            PriorityQueue_Node parNode = new PriorityQueue_Node(new_value, new_freq, leftNode, rightNode, false);
            fh.insert(parNode);
        }

        return fh;
    }
}

