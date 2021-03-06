package com.scrabble.util;

import static com.scrabble.ScrabbleWordsMain.wordMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import com.scrabble.dto.Word;

/**
 * Created by pruthford on 2/16/18.
 */
public class WordListUtil {

  /***
   * Gets all the scrabble words
   * @param w A set of letters to create words
   * @return A list of words sorted by scrabble point value
   */
  public static String getScrabbleWords(String w) {

    String body = "[";

    if (StringUtils.isBlank(w)) {
      return body + "]";
    }

    Word wrd = new Word(w.substring(1, (w.length())));

    Set<Word> wordSubset = WordListUtil.getWordSubset(wrd.getLettersContained(), wrd.getLength());

    Comparator<Word> byPoints = (Word o1, Word o2) -> o2.getPointValue().compareTo(o1.getPointValue());

    List<Word> sortedWords = wordSubset.stream().sorted(byPoints).collect(Collectors.toList());

    if (!sortedWords.isEmpty()) {
      body += StringUtils.join(sortedWords, ",");
    }
    return body + "]";
  }

  /***
   * Gets the list of words from the provided uri
   * @param uri uri to get words
   */
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
    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      System.out.println("Bad URL");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /***
   * Method to load wordMap
   * @param word Adds a word to wordMap
   */
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

  /***
   * Gets the subset of the words from wordmap that contain the letters in the input word
   * @param inputLetters set of letters
   * @param length length of the input phrase
   * @return Unsorted set of words that that are made from the input letters
   */
  public static Set<Word> getWordSubset(Multiset<String> inputLetters, final Integer length) {
    Set<Word> validWords = Sets.newHashSet();

    if (length == 0) {
      return Sets.newHashSet();
    }

    Predicate<Word> wordLength = p -> p.getLength() <= length;
    Predicate<Word> containsWord = p -> inputLetters.containsAll(p.getLettersContained());
    Predicate<Word> and = wordLength.and(containsWord);

    for (String letter : inputLetters) {
      try {
        List<Word> words = wordMap.get(letter);
        validWords.addAll(words.stream().filter(and).collect(Collectors.toList()));
      } catch (NullPointerException e) {
        continue;
      }
    }

    return validWords.stream().filter(word -> checkOccurrences(inputLetters, word) == 0).collect(Collectors.toSet());
  }

  /***
   * Checks the occurrences of a letter in the set to ensure only the correct # are in the scrabble word
   * @param inputLetters a set of letters
   * @param validWords a word
   * @return 0 for success 1 for failure
   */
  public static int checkOccurrences(Multiset<String> inputLetters, Word validWords) {
    if (inputLetters == null || validWords == null) {
      return 1;
    }

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

  /***
   *
   * @param letters A set of letters
   * @return A map of the letters in a word and the number of times they occur
   */
  public static Map<String, Integer> getOccurrenceMap(Multiset<String> letters) {
    Map<String, Integer> occurrences = Maps.newHashMap();

    if (letters == null) {
      return occurrences;
    }

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
