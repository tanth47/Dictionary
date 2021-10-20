import java.util.Scanner;

public class DictionaryCommandline extends Dictionary {
    /**
     * print all words from dictionary.
     * @author Kyoraku
     */
    public void showAllWords() {
        int wordCount = 0; // number of words

        System.out.println("No\t| English\t\t| Vietnamese");
        for (Word i : words) {
            System.out.println(++wordCount + "\t| " + i.word_target + "\t\t| " + i.word_explain);
        }
    }

    /**
     * call insertFromCommandline() and showAllWords().
     * @author Kyoraku
     */
    public void dictionaryBasic() {
        DictionaryManagement temp1 = new DictionaryManagement(); // call required class
        DictionaryCommandline temp2 = new DictionaryCommandline(); // call required class

        temp1.insertFromCommandline();
        temp2.showAllWords();
    }

    /**
     * call method insertFromFile(), dictionaryLookup() and showAllWords().
     * @author Taan
     */
    public void dictionaryAdvanced() {
        DictionaryManagement a = new DictionaryManagement();
        a.insertFromFile();
        showAllWords();
        a.dictionaryLookup();
    }

    /**
     * print all words with prefix using depth-first search.
     * @param prefix String prefix
     * @param suffix String suffix
     * @param id int id
     */
    public void printAllWords(String prefix, String suffix, int id) {
        for (char c = 'a'; c <= 'z'; ++c) {
            int change = c - 'a';

            if (c == ' ') {
                c = 27;
            }
            if (nxt[id][c] > 0) {
                if (check[id]) {
                    System.out.println(prefix + suffix);
                }
                printAllWords(prefix, suffix + c, nxt[id][c]);
            }
        }
    }

    /**
     * search words.
     * @author Kyoraku
     */
    public void dictionarySearcher() {
        Scanner input = new Scanner(System.in); // input from commandline
        String searchingWord = input.nextLine(); // word to search
        int id = 0; // id of node in trie tree

        for (int i = 0; i < searchingWord.length(); ++i) {
            char c = searchingWord.charAt(i); // character at position i
            int change = c - 'a'; // change to ascii

            if (c == ' ') {
                change = 27;
            }
            id = nxt[id][change];
        }
        printAllWords(searchingWord, "", id);
    }
}

class test2 {
    public static void main(String[] args) {
        DictionaryCommandline b = new DictionaryCommandline();
        DictionaryManagement a = new DictionaryManagement();
//        a.insertFromFile();
//        b.dictionaryAdvanced();
        b.showAllWords();
    }
}