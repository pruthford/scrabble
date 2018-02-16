package com.scrabble.handlers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class WordsHandler extends AbstractHandler {

  final String body = "This is the body method";

  public void handle(String s, Request request, HttpServletRequest httpServletRequest,
                     HttpServletResponse response) throws IOException, ServletException {
    response.setContentType("text/html; charset=utf-8");
    response.setStatus(HttpServletResponse.SC_OK);

    System.out.println(httpServletRequest.getContextPath());


    PrintWriter out = response.getWriter();

    if (body != null) {
      out.println(body);
    }

    request.setHandled(true);
  }
}

