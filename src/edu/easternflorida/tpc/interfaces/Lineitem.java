package edu.easternflorida.tpc.interfaces;

import java.math.BigDecimal;
import java.sql.Date;

public class Lineitem {
  private int L_ORDERKEY;
  private int L_PARTKEY;
  private int L_SUPPKEY;
  private int L_LINENUMBER;
  private BigDecimal L_QUANTITY;
  private BigDecimal L_EXTENDEDPRICE;
  private BigDecimal L_DISCOUNT;
  private BigDecimal L_TAX;
  private String L_RETURNFLAG;
  private String L_LINESTATUS;
  private Date L_SHIPDATE;
  private Date L_COMMITDATE;
  private Date L_RECEIPTDATE;
  private String L_SHIPINSTRUCT;
  private String L_SHIPMODE;
  private String L_COMMENT;

  public Lineitem(int orderKey, int partKey, int suppKey, int lineNumber, BigDecimal quantity,
      BigDecimal extendedPrice, BigDecimal discount, BigDecimal tax, String returnFlag, String lineStatus,
      Date shipDate, Date commitDate, Date receiptDate, String shipInstruct, String shipMode, String comment) {
    this.L_ORDERKEY = orderKey;
    this.L_PARTKEY = partKey;
    this.L_SUPPKEY = suppKey;
    this.L_LINENUMBER = lineNumber;
    this.L_QUANTITY = quantity;
    this.L_EXTENDEDPRICE = extendedPrice;
    this.L_DISCOUNT = discount;
    this.L_TAX = tax;
    this.L_RETURNFLAG = returnFlag;
    this.L_LINESTATUS = lineStatus;
    this.L_SHIPDATE = shipDate;
    this.L_COMMITDATE = commitDate;
    this.L_RECEIPTDATE = receiptDate;
    this.L_SHIPINSTRUCT = shipInstruct;
    this.L_SHIPMODE = shipMode;
    this.L_COMMENT = comment;
  }

  public int getL_ORDERKEY() {
    return L_ORDERKEY;
  }

  public int getL_PARTKEY() {
    return L_PARTKEY;
  }

  public int getL_SUPPKEY() {
    return L_SUPPKEY;
  }

  public int getL_LINENUMBER() {
    return L_LINENUMBER;
  }

  public BigDecimal getL_QUANTITY() {
    return L_QUANTITY;
  }

  public BigDecimal getL_EXTENDEDPRICE() {
    return L_EXTENDEDPRICE;
  }

  public BigDecimal getL_DISCOUNT() {
    return L_DISCOUNT;
  }

  public BigDecimal getL_TAX() {
    return L_TAX;
  }

  public String getL_RETURNFLAG() {
    return L_RETURNFLAG;
  }

  public String getL_LINESTATUS() {
    return L_LINESTATUS;
  }

  public Date getL_SHIPDATE() {
    return L_SHIPDATE;
  }

  public Date getL_COMMITDATE() {
    return L_COMMITDATE;
  }

  public Date getL_RECEIPTDATE() {
    return L_RECEIPTDATE;
  }

  public String getL_SHIPINSTRUCT() {
    return L_SHIPINSTRUCT;
  }

  public String getL_SHIPMODE() {
    return L_SHIPMODE;
  }

  public String getL_COMMENT() {
    return L_COMMENT;
  }
}

