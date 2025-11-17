package edu.easternflorida.tpc.interfaces;

import java.math.BigDecimal;

public class Supplier {
  private int S_SUPPKEY;
  private String S_NAME;
  private String S_ADDRESS;
  private int S_NATIONKEY;
  private String S_PHONE;
  private BigDecimal S_ACCTBAL;
  private String S_COMMENT;

  public Supplier(int suppKey, String name, String address, int nationKey, String phone, BigDecimal acctBal,
      String comment) {
    this.S_SUPPKEY = suppKey;
    this.S_NAME = name;
    this.S_NATIONKEY = nationKey;
    this.S_ADDRESS = address;
    this.S_NATIONKEY = nationKey;
    this.S_PHONE = phone;
    this.S_ACCTBAL = acctBal;
    this.S_COMMENT = comment;
  }

  public int getS_SUPPKEY() {
    return S_SUPPKEY;
  }

  public String getS_NAME() {
    return S_NAME;
  }

  public String getS_ADDRESS() {
    return S_ADDRESS;
  }

  public int getS_NATIONKEY() {
    return S_NATIONKEY;
  }

  public String getS_PHONE() {
    return S_PHONE;
  }

  public BigDecimal getS_ACCTBAL() {
    return S_ACCTBAL;
  }

  public String getS_COMMENT() {
    return S_COMMENT;
  }
}
