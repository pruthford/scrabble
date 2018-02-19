package com.scrabble.dto;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Created by pruthford on 2/16/18.
 *
 * Points | Letters
 * -------+-----------------------------
 * 1   | A, E, I, L, N, O, R, S, T, U
 * 2   | D, G
 * 3   | B, C, M, P
 * 4   | F, H, V, W, Y
 * 5   | K
 * 8   | J, X
 * 10  | Q, Z
 */
public class LetterValue {

  public Map<String, Integer> values = Maps.newHashMap();

  public LetterValue() {
    values.put("a", 1);
    values.put("e", 1);
    values.put("i", 1);
    values.put("l", 1);
    values.put("n", 1);
    values.put("o", 1);
    values.put("r", 1);
    values.put("s", 1);
    values.put("t", 1);
    values.put("u", 1);

    values.put("d", 2);
    values.put("g", 2);

    values.put("b", 3);
    values.put("c", 3);
    values.put("m", 3);
    values.put("p", 3);

    values.put("f", 4);
    values.put("h", 4);
    values.put("v", 4);
    values.put("w", 4);
    values.put("y", 4);

    values.put("k", 5);

    values.put("j", 8);
    values.put("x", 8);

    values.put("q", 10);
    values.put("z", 10);
  }
}
