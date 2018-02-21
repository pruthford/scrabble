package com.scrabble.handlers;

import static com.scrabble.util.WordListUtil.getScrabbleWords;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class WordsHandler extends AbstractHandler {

  /***
   * handler for /words/*
   * @param s
   * @param request
   * @param httpServletRequest
   * @param response
   * @throws IOException
   * @throws ServletException
   */
  public void handle(String s, Request request, HttpServletRequest httpServletRequest,
                     HttpServletResponse response) throws IOException, ServletException {
    response.setContentType("text/html; charset=utf-8");
    response.setStatus(HttpServletResponse.SC_OK);

    String w = httpServletRequest.getPathInfo().toLowerCase();

    String body = getScrabbleWords(w);

    PrintWriter out = response.getWriter();

    out.println(body);

    request.setHandled(true);
  }
}

