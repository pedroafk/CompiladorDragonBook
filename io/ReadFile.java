package io;

import java.nio.file.*;;

public class ReadFile {

  public static String read(String fileName) throws Exception {
    String data = "";
    data = new String(Files.readAllBytes(Paths.get(fileName)));
    return data;
  }
}