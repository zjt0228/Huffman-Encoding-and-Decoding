import java.io.*;

/**
 * Created by jiantaozhang on 2017/4/4.
 */
public class decoder {


    public static void main(String[] args) {
        decoder build = new decoder();
        build.decode(args[0], args[1], "decoded.txt");
    }


    public void decode(String encodeFile, String code_table_file, String decoded_file) {

        File encode_input = new File(encodeFile);
        File input_codeTable = new File(code_table_file);
        File output_decoded = new File(decoded_file);

        // Param to get encoded string
        StringBuffer encoded_code = new StringBuffer();
        byte[] b = null;


        // Three params to build decode tree
        String[] read_codeTable = new String[2000000];
        StringBuffer decode_string;
        PriorityQueue_Node decode_root = new PriorityQueue_Node();


        try {
            BufferedInputStream encode_buffer = new BufferedInputStream(new FileInputStream(encode_input));
            BufferedReader codeTable_buffer = new BufferedReader(new FileReader(input_codeTable));
            BufferedWriter writer_buffer = new BufferedWriter(new FileWriter(output_decoded));

            // Read the encoded file into a StringBuffer
            int length = (int) encode_input.length();
            b = new byte[length];
            encode_buffer.read(b);
            for (byte bt : b) {
                if (bt != -1) {
                    encoded_code.append(byteToBit(bt));
                } else if (byteToBit(bt).equals("11111111")) {
                    encoded_code.append(byteToBit(bt));
                }
            }


            // Read the code table file and build up the decode tree
            String next ="";
            String[] code_temp;
            while ((next = codeTable_buffer.readLine()) != null) {
                code_temp = next.split(" ");
                read_codeTable[Integer.parseInt(code_temp[0])] = code_temp[1];
            }
            for(int i = 0; i < read_codeTable.length; i++) {
                if (read_codeTable[i] != null) {
                    decode_string = new StringBuffer(read_codeTable[i]);
                    build_decode_tree(decode_string, decode_root, i);
                }
            }



            PriorityQueue_Node testNode = decode_root;
            int i = 0;
            while (i < encoded_code.length()) {
                int temp = Integer.parseInt(encoded_code.substring(i, i + 1));
                if (temp == 0) {
                    if (testNode.left != null) {
                        testNode = testNode.left;
                    } else {
                        System.out.println("Wrong code, no item found!!");
                        break;
                    }
                } else if (temp == 1) {
                    if (testNode.right != null) {
                        testNode = testNode.right;
                    } else {
                        System.out.println("Wrong code, no item found!!!");
                        break;
                    }
                } else {
                    System.out.println("Wrong code, no item found!!!!!");
                    break;
                }

                if (testNode.isLeaf) {
                    writer_buffer.write(testNode.value + "\n");
                    testNode = decode_root;
                    i++;
                } else {
                    i++;
                }
            }


            encode_buffer.close();
            codeTable_buffer.close();
            writer_buffer.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }


    public void build_decode_tree(StringBuffer read_code, PriorityQueue_Node PQNode, int value) {

        for(int i = 0; i < read_code.length(); i++) {
            int code = Integer.parseInt(read_code.substring(i, i + 1));
            if (code == 0) {
                if (PQNode.left == null) {
                    PQNode.left = new PriorityQueue_Node(value, -1);
                    PQNode.isLeaf = false;
                    PQNode.value = -1;
                    PQNode = PQNode.left;
                } else {
                    PQNode = PQNode.left;
                }
            } else if (code == 1) {
                if (PQNode.right == null) {
                    PQNode.right = new PriorityQueue_Node(value, -1);
                    PQNode.isLeaf = false;
                    PQNode.value = -1;
                    PQNode = PQNode.right;
                } else {
                    PQNode = PQNode.right;
                }
            } else {
                System.out.println("Wrong code number");
                break;
            }
        }
    }



    public static String byteToBit(byte b) {
        return ""
                + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)
                + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)
                + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)
                + (byte) ((b >> 1) & 0x1) + (byte) (b & 0x1);
    }
}
