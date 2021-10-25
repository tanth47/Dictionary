package com.example.dictapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;

public class Translator {
    /**
     * main method.
     * @author Kyoraku
     * @param args String[] input
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Translator http = new Translator();
        String word = http.callUrlAndParseResult("en", "vi", "tân stupid");

        System.out.println(word);
    }

    /**
     * call api to google.
     * @author Kyoraku
     * @param langFrom String language need to translate
     * @param langTo String translated language
     * @param word String text to translate
     * @return String text after translating
     * @throws Exception
     */
    public static String callUrlAndParseResult(String langFrom, String langTo,
                                               String word) throws Exception {
        String url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl="
                + langFrom + "&tl=" + langTo + "&dt=t&q=" + URLEncoder.encode(word, "UTF-8");
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return parseResult(response.toString());
    }

    /**
     * function required.
     * @param inputJson String inputJson
     * @return String parse result
     * @throws Exception
     */
    public static String parseResult(String inputJson) throws Exception {
        JSONArray jsonArray = new JSONArray(inputJson);
        JSONArray jsonArray2 = (JSONArray) jsonArray.get(0);
        JSONArray jsonArray3 = (JSONArray) jsonArray2.get(0);

        return jsonArray3.get(0).toString();
    }
}