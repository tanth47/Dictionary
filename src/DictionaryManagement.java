import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary{

    /**
     * insert new words from commandline.
     * @author Kyoraku
     */
    public void insertFromCommandline() {
        Scanner input = new Scanner(System.in); // input from commandline
        int numberOfWords = input.nextInt(); // number of words

        input.nextLine();
        for (int i = 0; i < numberOfWords; ++i) {
            String word_target = input.nextLine();
            String word_explain = input.nextLine();
            Word newWord = new Word(word_target, word_explain);
            words.add(newWord);
            explain.put(word_target, word_explain);
        }
    }

    /**
     * insert new words from File
     * @author Taaan
     */
    public void insertFromFile() {
        File file = new File("./data/dictionaries.txt");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String word = sc.nextLine();
                String word_target = "", word_explain = "";
                boolean ok = false;
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == '\t') {
                        ok = true;
                        continue;
                    }
                    if (ok) {
                        word_explain += word.charAt(i);
                    }
                    else {
                        word_target += word.charAt(i);
                    }
                }
                words.add(new Word(word_target, word_explain));
                explain.put(word_target, word_explain);
            }
        } catch (FileNotFoundException ex) {}

        int wordCount = 0; // number of words
        System.out.println("No\t| English\t\t| Vietnamese");
        for (Word i : words) {
            System.out.println(++wordCount + "\t| " + i.word_target + "\t\t| " + i.word_explain);
        }
    }

class test1 {
    public static void main(String[] args) {
        DictionaryManagement a = new DictionaryManagement();
//        a.insertFromFile();
        a.insertFromCommandline();
        DictionaryCommandline txz = new DictionaryCommandline();
        txz.showAllWords();
    }
}
