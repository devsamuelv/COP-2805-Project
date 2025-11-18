package edu.easternflorida.villegas.interfaces;

import java.math.BigDecimal;
import java.sql.Date;

public class Orders {
  private int O_ORDERKEY;
  private int O_CUSTKEY;
  private String O_ORDERSTATUS;
  private BigDecimal O_TOTALPRICE;
  private Date O_ORDERDATE;
  private String O_ORDERPRIORITY;
  private String O_CLERK;
  private int O_SHIPPRIORITY;
  private String O_COMMENT;

  public Orders(int orderKey, int custKey, String orderStatus, BigDecimal totalPrice, Date orderDate,
      String orderPriority, String clerk, int shipPriority, String comment) {
    this.O_ORDERKEY = orderKey;
    this.O_CUSTKEY = custKey;
    this.O_ORDERSTATUS = orderStatus;
    this.O_TOTALPRICE = totalPrice;
    this.O_ORDERDATE = orderDate;
    this.O_ORDERPRIORITY = orderPriority;
    this.O_CLERK = clerk;
    this.O_SHIPPRIORITY = shipPriority;
    this.O_COMMENT = comment;
  }

  public int getO_ORDERKEY() {
    return O_ORDERKEY;
  }

  public int getO_CUSTKEY() {
    return O_CUSTKEY;
  }

  public String getO_ORDERSTATUS() {
    return O_ORDERSTATUS;
  }

  public BigDecimal getO_TOTALPRICE() {
    return O_TOTALPRICE;
  }

  public Date getO_ORDERDATE() {
    return O_ORDERDATE;
  }

  public String getO_ORDERPRIORITY() {
    return O_ORDERPRIORITY;
  }

  public String getO_CLERK() {
    return O_CLERK;
  }

  public int getO_SHIPPRIORITY() {
    return O_SHIPPRIORITY;
  }

  public String getO_COMMENT() {
    return O_COMMENT;
  }
}
