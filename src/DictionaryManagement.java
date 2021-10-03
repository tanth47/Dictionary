import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary{

    /**
     * insert new words from commandline.
     * @author kyoraku
     */
    public void insertFromCommandline() {
        Dictionary newDictionary = null; // new dictionary
        Scanner input = new Scanner(System.in); // input from commandline
        int numberOfWords = input.nextInt(); // number of words

        for (int i = 0; i < numberOfWords; ++i) {
            Word newWord = new Word(input.nextLine(), input.nextLine());
            newDictionary.words.add(newWord);
        }
    }

    /**
     * insert new words from File
     * @author Taaan
     */
    public void insertFromFile() {
        File file = new File("D:/Documents/OOP/Dictionary/data/dictionaries.txt");
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
            }
        } catch (FileNotFoundException ex) {}

        int wordCount = 0; // number of words
        System.out.println("No\t| English\t\t| Vietnamese");
        for (Word i : words) {
            System.out.println(++wordCount + "\t| " + i.word_target + "\t\t| " + i.word_explain);
        }
    }
}

class test {
    public static void main(String[] args) {
        DictionaryManagement a= new DictionaryManagement();
        a.insertFromFile();
    }
}
