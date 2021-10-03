public class DictionaryCommandline extends Dictionary {
    /**
     * print all words from dictionary.
     * @author kyoraku
     */
    public void showAllWords() {
        Dictionary newDictionary = null; // new dictionary
        int wordCount = 0; // number of words

        System.out.println("No    | English          | Vietnamese");
        for (Word i : newDictionary.words) {
            System.out.println(++wordCount + "    | " + i.word_target + "    | " + i.word_explain);
        }
    }

    /**
     * call insertFromCommandline() and showAllWords().
     * @author kyoraku
     */
    public void dictionaryBasic() {
        DictionaryManagement dicManagement = new DictionaryManagement(); // management class
        DictionaryCommandline dicCommandline = new DictionaryCommandline(); // commandline class

        dicManagement.insertFromCommandline();
        dicCommandline.showAllWords();
    }
}

