package com.scrabble.dto;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Strings;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * Created by pruthford on 2/16/18.
 */
public class Word {

  private Integer length;
  private String word;
  private Multiset<String> lettersContained;
  private Integer pointValue;

  /***
   *
   * @param word String
   */
  public Word(String word) {
    this.word = word;

    this.length = Strings.isNullOrEmpty(word) ? 0 : word.length();

    addLettersContained(word);
    calculateValue();
  }

  /***
   *
   * @return Integer
   */
  public Integer getPointValue() {
    return pointValue;
  }

  /***
   *
   * @param pointValue Integer
   */
  public void setPointValue(Integer pointValue) {
    this.pointValue = pointValue;
  }

  /***
   *
   * @return Integer
   */
  public Integer getLength() {
    return length;
  }

  /***
   *
   * @param length Integer
   */
  public void setLength(Integer length) {
    this.length = length;
  }

  /***
   *
   * @return String
   */
  public String getWord() {
    return word;
  }

  /***
   *
   * @param word String
   */
  public void setWord(String word) {
    this.word = word;
  }

  /***
   *
   * @return Multiset<String>
   */
  public Multiset<String> getLettersContained() {
    return lettersContained;
  }

  /***
   *
   * @param lettersContained Multiset<String>
   */
  public void setLettersContained(Multiset<String> lettersContained) {
    this.lettersContained = lettersContained;
  }

  /***
   *
   * @param word String
   */
  protected void addLettersContained(String word) {
    if (lettersContained == null) {
      lettersContained = HashMultiset.create();
    }

    if (StringUtils.isNotBlank(word)) {
      for (char c : word.toCharArray()) {
        lettersContained.add(Character.toString(c));
      }
    }
  }

  /***
   * Calculated the total point value of a scrabble word
   */
  protected void calculateValue() {
    Integer points = 0;
    LetterValue letterValue = new LetterValue();

    for (String letter : lettersContained) {
      try {
        points += letterValue.values.get(letter) !=
            null ? letterValue.values.get(letter) : 0;
      } catch (NullPointerException e) {
        System.out.println("Cannot find letter: " + letter);
        System.out.println("Word: " + word);
        e.printStackTrace();
      }
    }

    setPointValue(points);
  }

  /***
   *
   * @return Word surrounded by "'s
   */
  @Override
  public String toString() {
    return "\"" + word + "\"";
  }
}
