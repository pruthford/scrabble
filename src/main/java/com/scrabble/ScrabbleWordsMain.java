package com.scrabble;

import java.util.List;
import java.util.Map;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

import com.google.common.collect.Maps;
import com.scrabble.dto.Word;
import com.scrabble.handlers.WordsHandler;
import com.scrabble.util.WordListUtil;

/**
 * Created by pruthford on 2/16/18.
 */
public class ScrabbleWordsMain {

  public final static Map<String, List<Word>> wordMap = Maps.newHashMap();

  private final static String wordListURI = "http://www-01.sil.org/linguistics/wordlists/english/wordlist/wordsEn.txt";

  public static void main(String[] args) {
    try {
      Server server = new Server(8080);

      ContextHandler contextRoot = new ContextHandler("/");

      ContextHandler contextEMR = new ContextHandler("/words/*");
      contextEMR.setHandler(new WordsHandler());

      ContextHandlerCollection contexts = new ContextHandlerCollection();
      contexts.setHandlers(new Handler[] { contextRoot, contextEMR });

      server.setHandler(contexts);

      WordListUtil.retrieveWords(wordListURI);

      server.start();
      server.join();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
