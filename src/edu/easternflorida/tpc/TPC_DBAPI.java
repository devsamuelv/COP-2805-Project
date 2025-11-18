/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.easternflorida.tpc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import edu.easternflorida.tpc.interfaces.Nation;
import edu.easternflorida.tpc.interfaces.Part;
import edu.easternflorida.tpc.interfaces.PartSupp;
import edu.easternflorida.tpc.interfaces.Region;
import edu.easternflorida.tpc.interfaces.Supplier;
import edu.easternflorida.tpc.interfaces.TPC_DBInterf;

/**
 *
 * @author samuel
 */
public class TPC_DBAPI extends TPC_DBInterf {
  private final String DATABASE_URL = "jdbc:derby:JavaDB";
  private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
  private Statement statement;

  public TPC_DBAPI() {
    try {
      Class.forName(driver).newInstance();
    } catch (Exception err) {
      err.printStackTrace();
    }

    try {
      Connection connection = DriverManager.getConnection(DATABASE_URL);
      statement = connection.createStatement();
    } catch (SQLException err) {
      err.printStackTrace();
    }
  }

  @Override
  public void insertPart(Part part) throws IllegalArgumentException, SQLException {
    boolean isDuplicate = checkForDuplicatePart(part);
    String insertPartQuery = String.format(
        "INSERT INTO APP.PART VALUES (%d, \'%s\', \'%s\', \'%s\', \'%s\', %d, \'%s\', %f, \'%s\')",
        part.getP_PARTKEY(), part.getP_NAME(),
        part.getP_MFGR(), part.getP_BRAND(), part.getP_TYPE(), part.getP_SIZE(), part.getP_CONTAINER(),
        part.getP_RETAILPRICE().doubleValue(), part.getP_COMMENT());

    if (!isDuplicate) {
      statement.execute(insertPartQuery);
    } else {
      throw new IllegalArgumentException("Part key already exists!");
    }
  }

  @Override
  public HashMap<Integer, Part> readAllParts() {
    String readAllParts = "SELECT * FROM APP.PART";
    HashMap<Integer, Part> parts = new HashMap<Integer, Part>();

    try {
      ResultSet result = statement.executeQuery(readAllParts);
      ResultSetMetaData meta = result.getMetaData();

      while (result.next()) {
        int P_PARTKEY = 0;
        String P_NAME = "";
        String P_MFGR = "";
        String P_BRAND = "";
        String P_TYPE = "";
        int P_SIZE = 0;
        String P_CONTAINER = "";
        BigDecimal P_RETAILPRICE = BigDecimal.ZERO;
        String P_COMMENT = "";

        for (int i = 1; i < meta.getColumnCount(); i++) {
          if (i == 1) {
            P_PARTKEY = (int) result.getObject(i);
          } else if (i == 2) {
            P_NAME = (String) result.getObject(i);
          } else if (i == 3) {
            P_MFGR = (String) result.getObject(i);
          } else if (i == 4) {
            P_BRAND = (String) result.getObject(i);
          } else if (i == 5) {
            P_TYPE = (String) result.getObject(i);
          } else if (i == 6) {
            P_SIZE = (int) result.getObject(i);
          } else if (i == 7) {
            P_CONTAINER = (String) result.getObject(i);
          } else if (i == 8) {
            P_RETAILPRICE = (BigDecimal) result.getObject(i);
          } else if (i == 9) {
            P_COMMENT = (String) result.getObject(i);
          }
        }

        if (P_PARTKEY != 0) {
          parts
              .put(P_PARTKEY,
                  new Part(P_PARTKEY, P_NAME, P_MFGR, P_BRAND, P_TYPE, P_SIZE, P_CONTAINER, P_RETAILPRICE, P_COMMENT));
        }
      }
    } catch (Exception err) {
      err.printStackTrace();
    }

    return parts;
  }

  private boolean checkForDuplicatePart(Part part) {
    HashMap<Integer, Part> parts = readAllParts();

    return parts.containsKey(part.getP_PARTKEY());
  }

  @Override
  public void insertRegion(Region region) throws IllegalArgumentException, SQLException {
    boolean isDuplicate = checkForDuplicateRegion(region);
    String insertRegionQuery = String.format(
        "INSERT INTO APP.REGION VALUES (%d, \'%s\', \'%s\')",
        region.getR_REGIONKEY(), region.getR_NAME(),
        region.getR_COMMENT());

    if (!isDuplicate) {
      statement.execute(insertRegionQuery);
    } else {
      throw new IllegalArgumentException("Region key already exists!");
    }
  }

  @Override
  public HashMap<Integer, Region> readAllRegions() {
    String readAllParts = "SELECT * FROM APP.REGION";
    HashMap<Integer, Region> regions = new HashMap<Integer, Region>();

    try {
      ResultSet result = statement.executeQuery(readAllParts);
      ResultSetMetaData meta = result.getMetaData();

      while (result.next()) {
        int R_REGIONKEY = 0;
        String R_NAME = "";
        String R_COMMENT = "";

        for (int i = 1; i < meta.getColumnCount(); i++) {
          if (i == 1) {
            R_REGIONKEY = (int) result.getObject(i);
          } else if (i == 2) {
            R_NAME = (String) result.getObject(i);
          } else if (i == 3) {
            R_COMMENT = (String) result.getObject(i);
          }
        }

        if (R_REGIONKEY != 0) {
          regions
              .put(R_REGIONKEY,
                  new Region(R_REGIONKEY, R_NAME, R_COMMENT));
        }
      }
    } catch (Exception err) {
      err.printStackTrace();
    }

    return regions;
  }

  private boolean checkForDuplicateRegion(Region region) {
    HashMap<Integer, Region> regions = readAllRegions();

    return regions.containsKey(region.getR_REGIONKEY());
  }

  @Override
  public void insertNation(Nation nation) throws IllegalArgumentException, SQLException {
    boolean isDuplicate = checkForDuplicateNation(nation);
    String insertNationQuery = String.format(
        "INSERT INTO APP.NATION VALUES (%d, \'%s\', \'%s\')",
        nation.getN_NATIONKEY(), nation.getN_NAME(),
        nation.getN_COMMENT());

    if (!isDuplicate) {
      statement.execute(insertNationQuery);
    } else {
      throw new IllegalArgumentException("Nation key already exists!");
    }
  }

  @Override
  public HashMap<Integer, Nation> readAllNations() {
    String readAllParts = "SELECT * FROM APP.REGION";
    HashMap<Integer, Nation> nations = new HashMap<Integer, Nation>();

    try {
      ResultSet result = statement.executeQuery(readAllParts);
      ResultSetMetaData meta = result.getMetaData();

      while (result.next()) {
        int N_NATIONKEY = 0;
        String N_NAME = "";
        int N_REGIONKEY = 0;
        String N_COMMENT = "";

        for (int i = 1; i < meta.getColumnCount(); i++) {
          if (i == 1) {
            N_NATIONKEY = (int) result.getObject(i);
          } else if (i == 2) {
            N_NAME = (String) result.getObject(i);
          } else if (i == 3) {
            N_REGIONKEY = (int) result.getObject(i);
          } else if (i == 3) {
            N_COMMENT = (String) result.getObject(i);
          }
        }

        if (N_NATIONKEY != 0) {
          nations
              .put(
                  N_NATIONKEY,
                  new Nation(N_NATIONKEY, N_NAME, N_REGIONKEY, N_COMMENT));
        }
      }
    } catch (Exception err) {
      err.printStackTrace();
    }

    return nations;
  }

  private boolean checkForDuplicateNation(Nation nation) {
    HashMap<Integer, Nation> nations = readAllNations();

    return nations.containsKey(nation.getN_NATIONKEY());
  }

  @Override
  public void insertSupplier(Supplier supplier) throws IllegalArgumentException, SQLException {
    boolean isDuplicate = checkForDuplicateSupplier(supplier);
    String insertSupplierQuery = String.format(
        "INSERT INTO APP.SUPPLIER VALUES (%d, \'%s\', \'%s\', %d, \'%s\', %f, \'%s\')",
        supplier.getS_SUPPKEY(), supplier.getS_NAME(),
        supplier.getS_ADDRESS(), supplier.getS_NATIONKEY(), supplier.getS_PHONE(), supplier.getS_ACCTBAL(),
        supplier.getS_COMMENT());

    if (!isDuplicate) {
      statement.execute(insertSupplierQuery);
    } else {
      throw new IllegalArgumentException("Supplier key already exists!");
    }
  }

  @Override
  public HashMap<Integer, Supplier> readAllSuppliers() {
    String readAllParts = "SELECT * FROM APP.SUPPLIER";
    HashMap<Integer, Supplier> suppliers = new HashMap<Integer, Supplier>();

    try {
      ResultSet result = statement.executeQuery(readAllParts);
      ResultSetMetaData meta = result.getMetaData();

      while (result.next()) {
        int S_SUPPKEY = 0;
        String S_NAME = "";
        String S_ADDRESS = "";
        int S_NATIONKEY = 0;
        String S_PHONE = "";
        BigDecimal S_ACCTBAL = BigDecimal.ZERO;
        String S_COMMENT = "";

        for (int i = 1; i < meta.getColumnCount(); i++) {
          if (i == 1) {
            S_SUPPKEY = (int) result.getObject(i);
          } else if (i == 2) {
            S_NAME = (String) result.getObject(i);
          } else if (i == 3) {
            S_ADDRESS = (String) result.getObject(i);
          } else if (i == 4) {
            S_NATIONKEY = (int) result.getObject(i);
          } else if (i == 5) {
            S_PHONE = (String) result.getObject(i);
          } else if (i == 6) {
            S_ACCTBAL = (BigDecimal) result.getObject(i);
          } else if (i == 7) {
            S_COMMENT = (String) result.getObject(i);
          }
        }

        if (S_SUPPKEY != 0) {
          suppliers
              .put(
                  S_SUPPKEY,
                  new Supplier(S_SUPPKEY, S_NAME, S_ADDRESS, S_NATIONKEY, S_PHONE, S_ACCTBAL, S_COMMENT));
        }
      }
    } catch (Exception err) {
      err.printStackTrace();
    }

    return suppliers;
  }

  private boolean checkForDuplicateSupplier(Supplier supplier) {
    HashMap<Integer, Supplier> suppliers = readAllSuppliers();

    return suppliers.containsKey(supplier.getS_SUPPKEY());
  }

  @Override
  public void insertPartSupp(PartSupp partSupp) throws IllegalArgumentException, SQLException {
    boolean isDuplicate = checkForDuplicatePartSupp(partSupp);
    String insertSupplierQuery = String.format(
        "INSERT INTO APP.PARTSUPP VALUES (%d, %d, %d, %f, \'%s\')",
        partSupp.getPS_PARTKEY(), partSupp.getPS_SUPPKEY(),
        partSupp.getPS_AVAILQTY(), partSupp.getPS_SUPPLYCOST(), partSupp.getPS_COMMENT());

    if (!isDuplicate) {
      statement.execute(insertSupplierQuery);
    } else {
      throw new IllegalArgumentException("Supplier key already exists!");
    }
  }

  @Override
  public ArrayList<PartSupp> readAllPartSupp() {
    String readAllParts = "SELECT * FROM APP.PARTSUPP";
    ArrayList<PartSupp> suppliers = new ArrayList<PartSupp>();

    try {
      ResultSet result = statement.executeQuery(readAllParts);
      ResultSetMetaData meta = result.getMetaData();

      while (result.next()) {
        int PS_PARTKEY = 0;
        int PS_SUPPKEY = 0;
        int PS_AVAILQTY = 0;
        BigDecimal PS_SUPPLYCOST = BigDecimal.ZERO;
        String PS_COMMENT = "";

        for (int i = 1; i < meta.getColumnCount(); i++) {
          if (i == 1) {
            PS_PARTKEY = (int) result.getObject(i);
          } else if (i == 2) {
            PS_SUPPKEY = (int) result.getObject(i);
          } else if (i == 3) {
            PS_AVAILQTY = (int) result.getObject(i);
          } else if (i == 4) {
            PS_SUPPLYCOST = (BigDecimal) result.getObject(i);
          } else if (i == 5) {
            PS_COMMENT = (String) result.getObject(i);
          }
        }

        if (PS_PARTKEY != 0) {
          suppliers.add(new PartSupp(PS_PARTKEY, PS_SUPPKEY, PS_AVAILQTY, PS_SUPPLYCOST, PS_COMMENT));
        }
      }
    } catch (Exception err) {
      err.printStackTrace();
    }

    return suppliers;
  }

  private boolean checkForDuplicatePartSupp(PartSupp partSupp) {
    HashMap<Integer, Supplier> partSupps = readAllSuppliers();

    return partSupps.containsKey(partSupp.getPS_SUPPKEY());
  }

}
