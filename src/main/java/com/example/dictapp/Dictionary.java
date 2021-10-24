package com.example.dictapp;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Dictionary {
    public static List<Word> words = new ArrayList<>(); // list of words
    public static HashMap<String, String> explain = new HashMap<>(); // search meaning
    public static int[][] nxt = new int[10000][28]; // Tree contain list of words
    public static boolean[] check = new boolean[10000]; // check end of word on trie
    public static int cnt = 1; // number of vertices on trie

    public Dictionary() {
        getData();
    }

    /**
     * add or remove word on trie.
     * @param s word target
     * @param ck false is remove and true is add
     * @author Taaan
     */
    public void fixTrie(String s, boolean ck) {
        int pos = 0;
        for(char i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int id = c - 'a';
            if(c == ' ') id = 27;
            if((c < 'a' || c > 'z') && c != ' ') break;
            if(nxt[pos][id] == 0) {
                nxt[pos][id] = cnt++;
            }
            pos = nxt[pos][id];
            if(i == s.length() - 1) {
                check[pos] = ck;
            }
        }
    }

    /**
     * export word data to file.
     * @author Kyoraku
     */
    public void exportToFile() {
        try {
            FileOutputStream fos = new FileOutputStream("./data/data.txt");
            DataOutputStream dos = new DataOutputStream(fos);
            for (Word i : words) {
                dos.writeBytes(i.word_target + "\n" + i.word_explain + "\n");
            }
            fos.close();
            dos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Get data when program run.
     * @author Taaan
     */
    public void getData() {
        File file = new File(".\\src\\main\\data\\data.txt");
        try {
          //  System.out.println("tannnnnn");
           // System.out.println(cnt);
            Scanner sc = new Scanner(file);
            //System.out.println(cnt);
            while (sc.hasNextLine()) {
                String target = sc.nextLine();
                String definition = sc.nextLine();
          //      System.out.println(target + " " + definition);
                words.add(new Word(target, definition));
                explain.put(target, definition);
                fixTrie(target, true);
            }
            //System.out.println(cnt);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
