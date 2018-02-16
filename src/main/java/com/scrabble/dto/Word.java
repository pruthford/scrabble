package com.scrabble.dto;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Sets;

/**
 * Created by pruthford on 2/16/18.
 */
public class Word {
  private Integer length;
  private String word;
  private Set<String> lettersContained;


  public Word( String word ) {
    this.word = word;
    this.length = word.length();

    addLettersContained(word);
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

  public Set<String> getLettersContained() {
    return lettersContained;
  }

  public void setLettersContained(Set<String> lettersContained) {
    this.lettersContained = lettersContained;
  }

  private void addLettersContained(String word){
    if (lettersContained == null){
      lettersContained = Sets.newHashSet();
    }

    if (StringUtils.isNotBlank(word)){
      for (char c : word.toCharArray()) {
        lettersContained.add(Character.toString(c));
      }
    }
  }

}
