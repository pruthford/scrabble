package com.scrabble.util;

import static com.scrabble.ScrabbleWordsMain.wordMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
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
        if (inputLine.contains("'")) {
          continue;
        }

        loadWordMap(inputLine.toLowerCase());
      }

      in.close();
    } catch (MalformedURLException e) {
      System.out.println("Bad URL");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void loadWordMap(String word) {
    Word w = new Word(word);

    for (String c : w.getLettersContained()) {
      if (wordMap.containsKey(c)) {
        wordMap.get(c).add(w);
      } else {
        wordMap.put(c, Lists.newArrayList(w));
      }
    }
  }

  public static Set<Word> getWordSubset(Multiset<String> inputLetters, final Integer length) {
    Set<Word> validWords = Sets.newHashSet();

    Predicate<Word> wordLength = p -> p.getLength() <= length;
    Predicate<Word> containsWord = p -> inputLetters.containsAll(p.getLettersContained());
    Predicate<Word> and = wordLength.and(containsWord);

    for (String letter : inputLetters) {
      validWords.addAll(wordMap.get(letter).stream().filter(and).collect(Collectors.toList()));
    }

    return validWords.stream().filter(word -> checkOccurrences(inputLetters, word) == 0).collect(Collectors.toSet());
  }

  public static int checkOccurrences(Multiset<String> inputLetters, Word validWords) {
    Map<String, Integer> foundWord = getOccurrenceMap(validWords.getLettersContained());
    Map<String, Integer> input = getOccurrenceMap(inputLetters);

    for (String key : foundWord.keySet()) {
      Integer foundWordNum = foundWord.get(key);
      Integer inputWordNum = input.get(key);

      if (!foundWordNum.equals(inputWordNum)) {
        return 1;
      }
    }

    return 0;
  }

  public static Map<String, Integer> getOccurrenceMap(Multiset<String> letters) {
    Map<String, Integer> occurrences = Maps.newHashMap();
    for (String l : letters) {
      if (occurrences.containsKey(l)) {
        occurrences.put(l, occurrences.get(l) + 1);
      } else {
        occurrences.put(l, 1);
      }
    }
    return occurrences;
  }
}
