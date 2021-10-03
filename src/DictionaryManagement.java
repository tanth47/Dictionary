import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
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
}
