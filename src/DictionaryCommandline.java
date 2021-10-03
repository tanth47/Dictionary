public class DictionaryCommandline extends Dictionary {
    /**
     * print all words from dictionary.
     * @author Kyoraku
     */
    public void showAllWords() {
        Dictionary newDictionary = null; // new dictionary
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
        DictionaryManagement dicManagement = new DictionaryManagement(); // management class
        DictionaryCommandline dicCommandline = new DictionaryCommandline(); // commandline class

        dicManagement.insertFromCommandline();
        dicCommandline.showAllWords();
    }
}

class test2 {
    public static void main(String[] args) {
        DictionaryCommandline a = new DictionaryCommandline();
        a.showAllWords();
    }
}

