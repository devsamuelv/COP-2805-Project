package edu.easternflorida.tpc.interfaces;

import java.math.BigDecimal;

public class Part {
  private int P_PARTKEY;
  private String P_NAME;
  private String P_MFGR;
  private String P_BRAND;
  private String P_TYPE;
  private int P_SIZE;
  private String P_CONTAINER;
  private BigDecimal P_RETAILPRICE;
  private String P_COMMENT;

  public Part(int key, String name, String mfgr, String brand, String type, int size, String container,
      BigDecimal price,
      String comment) {
    this.P_PARTKEY = key;
    this.P_NAME = name;
    this.P_MFGR = mfgr;
    this.P_BRAND = brand;
    this.P_TYPE = type;
    this.P_SIZE = size;
    this.P_CONTAINER = container;
    this.P_RETAILPRICE = price;
    this.P_COMMENT = comment;
  }

  public int getP_PARTKEY() {
    return P_PARTKEY;
  }

  public String getP_NAME() {
    return P_NAME;
  }

  public String getP_MFGR() {
    return P_MFGR;
  }

  public String getP_BRAND() {
    return P_BRAND;
  }

  public String getP_TYPE() {
    return P_TYPE;
  }

  public int getP_SIZE() {
    return P_SIZE;
  }

  public String getP_CONTAINER() {
    return P_CONTAINER;
  }

  public BigDecimal getP_RETAILPRICE() {
    return P_RETAILPRICE;
  }

  public String getP_COMMENT() {
    return P_COMMENT;
  }
}
