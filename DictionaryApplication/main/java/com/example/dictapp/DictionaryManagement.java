package com.example.dictapp;

import java.util.LinkedList;

public class DictionaryManagement extends Dictionary {
    public DictionaryManagement() {
        super();
    }

    /**
     * print all words with prefix using depth-first search.
     *
     * @param prefix String prefix
     * @param suffix String suffix
     * @param id     int id
     * @author Kyoraku
     */
    public void printAllWords(LinkedList<String> result, String prefix, String suffix, int id) {
        if (check[id]) {
            result.add(prefix + suffix);
        }
        for (int c = 0; c <= 30; ++c) {
            char t = (char) ('a' + c); // character

            if (c == 27) {
                t = ' ';
            }
            if (c == 28) {
                t = '-';
            }
            if (c == 29) {
                t = '\'';
            }
            if (c == 30) {
                t = '.';
            }

            if (nxt[id][c] > 0) {
                printAllWords(result, prefix, suffix + t, nxt[id][c]);
            }
        }
    }

    /**
     * search words.
     *
     * @param searchingWord searchingWord
     * @author Kyoraku
     */
    public LinkedList<String> dictionarySearcher(String searchingWord) {
        LinkedList<String> result = new LinkedList<>();
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
            if (nxt[id][change] > 0) {
                id = nxt[id][change];
            } else {
                return result;
            }
        }
        printAllWords(result, searchingWord, "", id);
        return result;
    }

    /**
     * tra cuu tu.
     *
     * @author Taaan
     */
    public String dictionaryLookup(String target) {
        target = target.toLowerCase();
        if (explain.containsKey(target)) {
            return explain.get(target);
        }
        return "Khong tim thay trong tu dien :(";
    }

    /**
     * add word.
     *
     * @author Kyoraku
     */
    public void addWord(String target, String definition) {
        Word newWord = new Word(target, definition); // this word
        words.add(newWord);
        explain.put(target, definition);
        fixTrie(target, true);
    }

    /**
     * delete word.
     *
     * @author Kyoraku
     */
    public void deleteWord(String target) {

        words.removeIf((Word temp) -> {
            boolean check = (temp.word_target.equals(target)); // check word
            return check;
        });
        explain.remove(target);
        fixTrie(target, false);
    }

    /**
     * edit word.
     *
     * @author Kyoraku
     */
    public void editWord(String target, String definition) {
        Word newWord1 = new Word(target, definition); // this word

        words.removeIf((Word temp) -> {
            boolean check = (temp.word_target.equals(target)); // check word
            return check;
        });
        words.add(newWord1);
        explain.put(target, definition);
    }
}

