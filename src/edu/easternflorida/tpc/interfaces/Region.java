package edu.easternflorida.tpc.interfaces;

public class Region {
  private int R_REGIONKEY;
  private String R_NAME;
  private String R_COMMENT;

  public Region(int key, String name, String comment) {
    this.R_REGIONKEY = key;
    this.R_NAME = name;
    this.R_COMMENT = comment;
  }

  public String getR_NAME() {
    return R_NAME;
  }

  public int getR_REGIONKEY() {
    return R_REGIONKEY;
  }

  public String getR_COMMENT() {
    return R_COMMENT;
  }
}
