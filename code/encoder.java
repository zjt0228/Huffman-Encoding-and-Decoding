import java.io.*;
import java.util.HashMap;

/**
 * Created by jiantaozhang on 2017/4/4.
 */
public class encoder {

    private String[] code_table = new String[1000000];

    public static void main(String[] args) {
        encoder build = new encoder();
        HuffmanTree workTree = new HuffmanTree();


        HashMap<String, Integer> freq_table = workTree.buildFreqTable(args[0]);
        FourWayOptimalHeap fh = workTree.fourWayTest(freq_table);
        PriorityQueue_Node huffmanTree = fh.fourWayHeap.get(3);


        build.build_codeTable(huffmanTree);
        build.write_code_table("code_table.txt");
        build.encode(args[0], "encoded.bin");
    }


    public void build_codeTable(PriorityQueue_Node PQNode) {

        if (PQNode.isLeaf) {
            int value = PQNode.value;
            String code = PQNode.code;
            this.code_table[value] = code;
        }
        if (PQNode.left != null) {
            if (PQNode.code == null) {
                PQNode.left.code = "0";
            } else {
                PQNode.left.code = PQNode.code + "0";
            }
            build_codeTable(PQNode.left);
        }
        if (PQNode.right != null) {
            if (PQNode.code == null) {
                PQNode.right.code = "1";
            } else {
                PQNode.right.code = PQNode.code + "1";
            }
            build_codeTable(PQNode.right);
        }
    }

    public void write_code_table(String out_put_file) {
        try {
            FileWriter fw = new FileWriter(out_put_file);
            BufferedWriter writer = new BufferedWriter(fw);

            for (int i = 0; i < this.code_table.length; i++) {
                if (this.code_table[i] != null) {
                    String code = this.code_table[i];
                    writer.write(i + " " + code + "\n");
                }
            }
            writer.close();
            fw.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public void encode(String input_filename, String out_put_file) {
        File out_put = new File(out_put_file);
        StringBuffer content = new StringBuffer();
        byte[] code;
        byte extra_code = -1;
        try {
            BufferedReader br = new BufferedReader(new FileReader(input_filename));
            BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(out_put));
            String next;
            while ((next = br.readLine()) != null) {                            // Save each line of the input file into a StringBuffer
                int index = Integer.parseInt(next);
                if (this.code_table[index] != null) {
                    content.append(code_table[index]);
                }
            }
            int len = content.length();
            code = new byte[len / 8 ];
            int i = 0;
            int k = 0;
            while (k < content.length() && i < len/8 ) {
                code[i] = Integer.valueOf(content.substring(k, k + 8), 2).byteValue();          // Save every 8 bits into a byte
                k += 8;
                i++;
            }
            if (len % 8 != 0) {
                extra_code = Integer.valueOf(content.substring(k), 2).byteValue();              // for extra bits which cannot form a complete byte
                extra_code = (byte) (extra_code << (8 - len % 8));
            }
            for (byte temp : code) {
                writer.write(temp);
            }
            if (extra_code != -1) {
                writer.write(extra_code);
            }
            br.close();
            writer.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
}
