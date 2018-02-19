package com.scrabble.handlers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.scrabble.dto.Word;
import com.scrabble.util.WordListUtil;

public class WordsHandler extends AbstractHandler {

  public void handle(String s, Request request, HttpServletRequest httpServletRequest,
                     HttpServletResponse response) throws IOException, ServletException {
    response.setContentType("text/html; charset=utf-8");
    response.setStatus(HttpServletResponse.SC_OK);

    String body = "Scrabble Words:\n";

    String w = httpServletRequest.getPathInfo().toLowerCase();

    Word wrd = new Word(w.substring(1, (w.length())));

    Set<Word> wordSubset = WordListUtil.getWordSubset(wrd.getLettersContained(), wrd.getLength());

    Comparator<Word> byPoints = (Word o1, Word o2) -> o2.getPointValue().compareTo(o1.getPointValue());

    List<Word> sortedWords = wordSubset.stream().sorted(byPoints).collect(Collectors.toList());

    for (Word word : sortedWords) {
      body += StringUtils.join("[" + word.getWord() + ": " + word.getPointValue() + "]", "\n");
    }

    PrintWriter out = response.getWriter();

    if (body != null) {
      out.println(body);
    }

    request.setHandled(true);
  }
}

