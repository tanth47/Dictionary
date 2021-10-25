import java.util.Scanner;

public class DictionaryCommandline extends Dictionary {
    public DictionaryCommandline() {
        super();
    }
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
        if (check[id]) {
            System.out.println(prefix + suffix);
        }
        for (int i = 0; i <= 30; ++i) {
            char c = (char)('a' + i); // change to character

            if (i == 27) {
                c = ' ';
            }
            if (i == 28) {
                c = '-';
            }
            if (i == 29) {
                c = '\'';
            }
            if (i == 30) {
                c = '.';
            }
            if (nxt[id][i] > 0) {
                printAllWords(prefix, suffix + c, nxt[id][i]);
            }
        }
    }

    /**
     * search words.
     * @author Kyoraku
     */
    public void dictionarySearcher() {
        Scanner input = new Scanner(System.in); // input from commandline
        System.out.println("Moi nhap tu can tim:");
        String searchingWord = input.nextLine(); // word to search
        int id = 0; // id of node in trie tree

        for (int i = 0; i < searchingWord.length(); ++i) {
            char c = searchingWord.charAt(i); // character at position i
            int change = c - 'a'; // change to ascii

            if (c == ' ') {
                change = 27;
            }
            if (c == '-') {
                change = 28;
            }
            if (c == '\'') {
                change = 29;
            }
            if (c == '.') {
                change = 30;
            }
            if(change < 0 || change > 30) {
                System.out.println("Khong co tu nay trong tu dien!");

                break;
            }
            if (nxt[id][change] > 0) {
                id = nxt[id][change];
            } else {
                System.out.println("Khong co tu nay trong tu dien!");

                return;
            }
        }
        printAllWords(searchingWord, "", id);
    }
}
