package com.scrabble.dto;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Created by pruthford on 2/19/18.
 */
public class WordTest {

  @Test
  public void addLettersContainedHappy() throws Exception {
    String test = "test";
    Word word = new Word(test);

    assertTrue(word.getLettersContained().size() == 4);
  }

  @Test
  public void addLettersContainedEmpty() throws Exception {
    String test = "";
    Word word = new Word(test);

    assertTrue(word.getLettersContained().size() == 0);
  }

  @Test
  public void addLettersContainedNull() throws Exception {
    String test = null;
    Word word = new Word(test);

    assertTrue(word.getLettersContained().size() == 0);
  }

  @Test
  public void addLettersContainedDuplicateLetters() throws Exception {
    String test = "testt";
    Word word = new Word(test);

    assertTrue(word.getLettersContained().size() == 5);
  }

  @Test
  public void calculateValueHappy() throws Exception {
    String test = "test";
    Word w = new Word(test);

    assertTrue(w.getPointValue() == 4 );
  }

  @Test
  public void calculateValueEmpty() throws Exception {
    String test = "";
    Word w = new Word(test);

    assertTrue(w.getPointValue() == 0 );
  }

  @Test
  public void toStringTest() throws Exception {
    Word w = new Word ("test");
    assertTrue(w.toString().equals("\"test\""));
  }
}