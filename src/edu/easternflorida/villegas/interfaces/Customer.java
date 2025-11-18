package edu.easternflorida.villegas.interfaces;

import java.math.BigDecimal;

public class Customer {
  private int C_CUSTKEY;
  private String C_NAME;
  private String C_ADDRESS;
  private int C_NATIONKEY;
  private String C_PHONE;
  private BigDecimal C_ACCTBAL;
  private String C_MKTSEGMENT;
  private String C_COMMENT;

  public Customer(int custKey, String name, String address, int nationKey, String phone, BigDecimal acctBal,
      String mktSegment, String comment) {
    this.C_CUSTKEY = custKey;
    this.C_NAME = name;
    this.C_ADDRESS = address;
    this.C_NATIONKEY = nationKey;
    this.C_PHONE = phone;
    this.C_ACCTBAL = acctBal;
    this.C_MKTSEGMENT = mktSegment;
    this.C_COMMENT = comment;
  }

  public int getC_CUSTKEY() {
    return C_CUSTKEY;
  }

  public String getC_NAME() {
    return C_NAME;
  }

  public String getC_ADDRESS() {
    return C_ADDRESS;
  }

  public int getC_NATIONKEY() {
    return C_NATIONKEY;
  }

  public String getC_PHONE() {
    return C_PHONE;
  }

  public BigDecimal getC_ACCTBAL() {
    return C_ACCTBAL;
  }

  public String getC_MKTSEGMENT() {
    return C_MKTSEGMENT;
  }

  public String getC_COMMENT() {
    return C_COMMENT;
  }
}
