package com.example.dictapp;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary{
    public DictionaryManagement() {
        super();
    }

    /**
     * print all words with prefix using depth-first search.
     * @param prefix String prefix
     * @param suffix String suffix
     * @param id int id
     */
    public void printAllWords(LinkedList<String> result, String prefix, String suffix, int id) {
        if (check[id]) {
            result.add(prefix + suffix);
        }
        for (int c = 0; c <= 27; ++c) {
            char t = (char)('a' + c);
            if (c == 27) {
                t = ' ';
            }
            if (nxt[id][c] > 0) {
                printAllWords(result, prefix, suffix + t, nxt[id][c]);
            }
        }
    }

    /**
     * search words.
     * @author Kyoraku
     * @param searchingWord searchingWord
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
            if(nxt[id][change] > 0) {
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
     * @author Taaan
     */
    public String dictionaryLookup(String target) {
        target = target.toLowerCase();
        if(explain.containsKey(target)) {
            return explain.get(target);
        }
        return "Khong tim thay trong tu dien :(";
    }

    /**
     * fix data.
     * @author Kyoraku
     */
    public void addWord(String target, String definition) {
        Word newWord = new Word(target, definition); // this word
        words.add(newWord);
        explain.put(target, definition);
        fixTrie(target, true);
    }

    public void deleteWord(String target) {
        words.removeIf((Word temp) -> {
            boolean check = (temp.word_target == target); // check word
            return check;
        });
        explain.remove(target);
        fixTrie(target, false);
    }
    /*
    public void fixData() {


        switch (type) {
            case 2:
                System.out.println("Nhap tu can xoa:" + "\n");
                String target1 = input.nextLine().toLowerCase(); // english word


            case 3:
                System.out.println("Nhap tu can sua:" + "\n");
                String target2 = input.nextLine().toLowerCase(); // english word

                System.out.println("Nhap nghia cua tu vua sua: " + "\n");
                String definition1 = input.nextLine().toLowerCase(); // vietnamese word
                Word newWord1 = new Word(target2, definition1); // this word

                words.removeIf((Word temp) -> {
                    boolean check = (temp.word_target == target2); // check word

                    return check;
                });
                explain.remove(target2);
                fixTrie(target2, false);
                words.add(newWord1);
                explain.put(target2, definition1);
                fixTrie(target2, true);
            default:
                System.out.println("Ban da dua ra lua chon loi!");
        }
    }*/
}

