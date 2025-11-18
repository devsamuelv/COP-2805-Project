package edu.easternflorida.tpc.interfaces;

import java.math.BigDecimal;

public class PartSupp {
  private int PS_PARTKEY;
  private int PS_SUPPKEY;
  private int PS_AVAILQTY;
  private BigDecimal PS_SUPPLYCOST;
  private String PS_COMMENT;

  public PartSupp(int partKey, int suppKey, int availQty, BigDecimal supplyCost, String comment) {
    this.PS_PARTKEY = partKey;
    this.PS_SUPPKEY = suppKey;
    this.PS_AVAILQTY = availQty;
    this.PS_SUPPLYCOST = supplyCost;
    this.PS_COMMENT = comment;
  }

  public int getPS_PARTKEY() {
    return PS_PARTKEY;
  }

  public int getPS_SUPPKEY() {
    return PS_SUPPKEY;
  }

  public int getPS_AVAILQTY() {
    return PS_AVAILQTY;
  }

  public BigDecimal getPS_SUPPLYCOST() {
    return PS_SUPPLYCOST;
  }

  public String getPS_COMMENT() {
    return PS_COMMENT;
  }
}
