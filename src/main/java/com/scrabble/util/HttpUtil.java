package com.scrabble.util;

import javax.servlet.http.HttpServletResponse;


public class HttpUtil {

  public static void send418Response( HttpServletResponse response ){
    response.setContentType("text/html; charset=utf-8");
    response.setStatus(418);

  }



}
