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

  public Word(String word) {
    this.word = word;

    this.length = Strings.isNullOrEmpty(word) ? 0 : word.length();

    addLettersContained(word);
    calculateValue();
  }

  public Integer getPointValue() {
    return pointValue;
  }

  public void setPointValue(Integer pointValue) {
    this.pointValue = pointValue;
  }

  public Integer getLength() {
    return length;
  }

  public void setLength(Integer length) {
    this.length = length;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public Multiset<String> getLettersContained() {
    return lettersContained;
  }

  public void setLettersContained(Multiset<String> lettersContained) {
    this.lettersContained = lettersContained;
  }

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

  @Override
  public String toString() {
    return "\"" + word + "\"";
  }
}
