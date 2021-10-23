package com.example.dictapp;

public class test {
    static String s;
    public static void ini() {
        s = new String();
    }
    public static void check() {
        System.out.println(s == null);
    }
    public static void main(String[] args) {
        ini();
        check();
    }
}
