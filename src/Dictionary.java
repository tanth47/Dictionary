import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Dictionary {
    public static List<Word> words = new ArrayList<>(); // list of words
    public static HashMap<String, String> explain = new HashMap<>(); // search meaning
    public static int[][] nxt = new int[10000][28]; // tree contain list of words
    public static boolean[] check = new boolean[10000]; // check the end of word on trie
    public static int cnt = 1; // number of vertices on trie

    /**
     * add or remove word on trie.
     * @param s word target
     * @param ck false is remove and true is add
     */
    public void fixTrie(String s, boolean ck) {
        int pos = 0;
        for(char i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int id = c - 'a';
            if(c == ' ') id = 27;
            if(nxt[pos][id] == 0) {
                nxt[pos][id] = cnt++;
            }
            pos = nxt[pos][id];
            if(i == s.length() - 1) {
                check[pos] = ck;
            }
        }
    }

    /**
     * export word data to file.
     * @author Kyoraku
     */
    public void exportToFile() {
        try {
            FileOutputStream fos = new FileOutputStream("./data/data.txt");
            DataOutputStream dos = new DataOutputStream(fos);
            for (Word i : words) {
                dos.writeBytes(i.word_target + "\n" + i.word_explain + "\n");
            }
            fos.close();
            dos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}