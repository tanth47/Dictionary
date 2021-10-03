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
        DictionaryManagement a = new DictionaryManagement();
        a.insertFromCommandline();
        showAllWords();
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
}

class test2 {
    public static void main(String[] args) {
        DictionaryCommandline b = new DictionaryCommandline();
        DictionaryManagement a= new DictionaryManagement();
        a.insertFromFile();
        b.dictionaryAdvanced();
    }
}