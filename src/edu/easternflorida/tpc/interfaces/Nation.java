package edu.easternflorida.tpc.interfaces;

public class Nation {
  private int N_NATIONKEY;
  private String N_NAME;
  private int N_REGIONKEY;
  private String N_COMMENT;

  public Nation(int nationKey, String name, int regionKey, String comment) {
    this.N_NATIONKEY = nationKey;
    this.N_REGIONKEY = regionKey;
    this.N_NAME = name;
    this.N_COMMENT = comment;
  }

  public int getN_NATIONKEY() {
    return N_NATIONKEY;
  }

  public String getN_NAME() {
    return N_NAME;
  }

  public int getN_REGIONKEY() {
    return N_REGIONKEY;
  }

  public String getN_COMMENT() {
    return N_COMMENT;
  }

}
