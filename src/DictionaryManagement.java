import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary{

    /**
     * insert new words from commandline.
     * @author kyoraku
     */
    public void insertFromCommandline() {
        Scanner input = new Scanner(System.in); // input from commandline
        int numberOfWords = input.nextInt(); // number of words

        input.nextLine();
        for (int i = 0; i < numberOfWords; ++i) {
            String target = input.nextLine();
            String definition = input.nextLine();
            target = target.toLowerCase();
            definition = definition.toLowerCase();
            Word newWord = new Word(target, definition);
            words.add(newWord);
            explain.put(target, definition);
            fixTrie(target, true);
        }
    }

    /**
     * insert new words from File.
     * @author Taaan
     */
    public void insertFromFile() {
        File file = new File("./data/dictionaries.txt");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String word = sc.nextLine();
                String target = "";
                String definition = "";
                boolean ok = false;
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == '\t') {
                        ok = true;
                        continue;
                    }
                    if (ok) {
                        definition += word.charAt(i);
                    }
                    else {
                        target += word.charAt(i);
                    }
                }
                target = target.toLowerCase();
                definition = definition.toLowerCase();
                words.add(new Word(target, definition));
                explain.put(target, definition);
                fixTrie(target, true);
            }
        } catch (FileNotFoundException ex) {}

        int wordCount = 0; // number of words
        System.out.println("No\t| English\t\t| Vietnamese");
        for (Word i : words) {
            System.out.println(++wordCount + "\t| " + i.word_target + "\t\t| " + i.word_explain);
        }
    }

    /**
     * tra cuu tu.
     * @author Taan
     */
    public void dictionaryLookup() {
        Scanner sc = new Scanner(System.in);
        String target = sc.nextLine();
        target = target.toLowerCase();
        if(explain.containsKey(target)) {
            System.out.println(explain.get(target));
        }
        else System.out.println("Khong tim thay trong tu dien :(");
    }
}

class test {
    public static void main(String[] args) {
        DictionaryManagement a= new DictionaryManagement();
        a.insertFromFile();
    }
}
