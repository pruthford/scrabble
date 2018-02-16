package com.scrabble.util;

import static com.scrabble.ScrabbleWordsMain.wordMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


import com.google.common.collect.Lists;
import com.scrabble.dto.Word;

/**
 * Created by pruthford on 2/16/18.
 */
public class WordListUtil {

  public static void retrieveWords(String uri) {
    try {

      URL url = new URL(uri);
      BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

      String inputLine;
      while ((inputLine = in.readLine()) != null) {
        System.out.println(inputLine);
        loadWordMap(inputLine);
      }

      in.close();

    } catch (MalformedURLException e) {
      System.out.println("Bad URL");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void loadWordMap(String word){
    Word w = new Word(word);

    for (String c : w.getLettersContained()) {
      if (wordMap.containsKey(c)){
        wordMap.get(c).add(w);
      }
      else {
        wordMap.put(c, Lists.newArrayList(w));
      }
    }
  }
}
