package com.scrabble.util;

import static org.junit.Assert.assertTrue;

import static com.scrabble.ScrabbleWordsMain.wordMap;

import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.HashMultiset;
import com.scrabble.dto.Word;

/**
 * Created by pruthford on 2/19/18.
 */
public class WordListUtilTest {

  @Test
  public void getScrabbleWordsHappy() throws Exception {
    String w = "test";
    String scrabbleWords = WordListUtil.getScrabbleWords(w);

    assertTrue(scrabbleWords.length() > 25);
  }

  @Test
  public void getScrabbleWordsNoneFound() throws Exception {
    String w = "zzzz";
    String scrabbleWords = WordListUtil.getScrabbleWords(w);

    assertTrue(scrabbleWords.length() == 2);
  }

  @Test
  public void getScrabbleWordsEmptyInput() throws Exception {
    String w = "";
    String scrabbleWords = WordListUtil.getScrabbleWords(w);

    assertTrue(scrabbleWords.length() == 2);
  }

  @Test
  public void getScrabbleWordsOddChars() throws Exception {
    String w = "ωæáàâåäã";
    String scrabbleWords = WordListUtil.getScrabbleWords(w);

    assertTrue(scrabbleWords.length() == 2);
  }

  @Test
  public void retrieveWordsHappy() throws Exception {
    wordMap.clear();
    String wordListURI = "http://www-01.sil.org/linguistics/wordlists/english/wordlist/wordsEn.txt";
    WordListUtil.retrieveWords(wordListURI);

    assertTrue(wordMap.size() == 26);
  }

  @Test
  public void retrieveWordsBadURL() throws Exception {
    wordMap.clear();

    String notAURL = "http://www.niodnfosingorn.com";
    WordListUtil.retrieveWords(notAURL);

    assertTrue(wordMap.size() == 0);
  }

  @Test
  public void getWordSubsetHappy() throws Exception {
    Word wrd = new Word("test");
    Set<Word> wordSubset = WordListUtil.getWordSubset(wrd.getLettersContained(), wrd.getLength());

    assertTrue(wordSubset.size() > 1);
  }

  @Test
  public void getWordSubsetNoneFound() throws Exception {
    Word wrd = new Word("zzz");
    Set<Word> wordSubset = WordListUtil.getWordSubset(wrd.getLettersContained(), wrd.getLength());

    assertTrue(wordSubset.isEmpty());
  }

  @Test
  public void getWordSubsetEmptyInputSet() throws Exception {
    Word wrd = new Word("");
    Set<Word> wordSubset = WordListUtil.getWordSubset(wrd.getLettersContained(), wrd.getLength());

    assertTrue(wordSubset.isEmpty());
  }

  @Test
  public void checkOccurrencesHappy() throws Exception {
    HashMultiset<String> letters = HashMultiset.create();
    letters.add("t");
    letters.add("e");
    letters.add("s");
    letters.add("t");

    Word w = new Word("test");

    int i = WordListUtil.checkOccurrences(letters, w);

    assertTrue(i == 0);
  }

  @Test
  public void checkOccurrencesFailed() throws Exception {
    HashMultiset<String> letters = HashMultiset.create();
    letters.add("t");
    letters.add("e");
    letters.add("s");
    letters.add("t");

    Word w = new Word("not");

    int i = WordListUtil.checkOccurrences(letters, w);

    assertTrue(i == 1);
  }

  @Test
  public void checkOccurrencesMultiLetterFail() throws Exception {
    HashMultiset<String> letters = HashMultiset.create();
    letters.add("t");
    letters.add("e");
    letters.add("s");
    letters.add("t");

    Word w = new Word("sest");

    int i = WordListUtil.checkOccurrences(letters, w);

    assertTrue(i == 1);
  }

  @Test
  public void checkOccurrencesNullSet() throws Exception {
    HashMultiset<String> letters = null;

    Word w = new Word("set");

    int i = WordListUtil.checkOccurrences(letters, w);

    assertTrue(i == 1);
  }

  @Test
  public void checkOccurrencesNullWord() throws Exception {
    HashMultiset<String> letters = HashMultiset.create();
    letters.add("t");
    letters.add("e");
    letters.add("s");
    letters.add("t");

    Word w = null;

    int i = WordListUtil.checkOccurrences(letters, w);

    assertTrue(i == 1);
  }

  @Test
  public void getOccurrenceMapHappy() throws Exception {
    HashMultiset<String> letters = HashMultiset.create();
    letters.add("t");
    letters.add("e");
    letters.add("s");
    letters.add("t");
    Map<String, Integer> map = WordListUtil.getOccurrenceMap(letters);

    assertTrue(map.get("t") == 2 && map.get("e") == 1 && map.get("s") == 1);
  }

  @Test
  public void getOccurrenceMapEmptySet() throws Exception {
    HashMultiset<String> letters = HashMultiset.create();

    Map<String, Integer> map = WordListUtil.getOccurrenceMap(letters);

    assertTrue(map.isEmpty());
  }

  @Test
  public void getOccurrenceMapNull() throws Exception {
    Map<String, Integer> map = WordListUtil.getOccurrenceMap(null);

    assertTrue(map.isEmpty());
  }
}