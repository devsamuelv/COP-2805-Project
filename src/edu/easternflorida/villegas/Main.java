package edu.easternflorida.villegas;

import java.math.BigDecimal;
import java.util.HashMap;

import edu.easternflorida.villegas.interfaces.Part;

public class Main {
  static TPC_DBAPI api = new TPC_DBAPI();

  public static void main(String[] args) {
    HashMap<Integer, Part> parts = api.readAllParts();
    Part magicPart = new Part(1000000000, "yeaa", "yeaa", "yeaa", "yeaa", 0, "yeaa", BigDecimal.ONE, "yeaa");

    System.out.println("has record " + parts.containsKey(magicPart.getP_PARTKEY()));

    try {
      api.insertPart(magicPart);
    } catch (Exception err) {
      err.printStackTrace();
    }

    System.out.println("has record " + parts.containsKey(magicPart.getP_PARTKEY()));
  }
}
