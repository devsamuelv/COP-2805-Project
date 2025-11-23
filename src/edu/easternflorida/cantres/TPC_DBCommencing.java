package edu.easternflorida.cantres;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TPC_DBCommencing
 *
 *  - Create/initialize the Derby Java database if it does not exist.
 */
public class TPC_DBCommencing {

    private static final String DATABASE_URL = "jdbc:derby:JavaDB;create=true";
    private static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";

    private Connection connection;

    /**
     * Connects to the Java database and creates the TPC-H tables if they
     * are missing.
     */
    public Connection initDatabase() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connection = DriverManager.getConnection(DATABASE_URL);
        System.out.println("Connected to JavaDB.");

        if (!tableExists("APP", "REGION")) {
            System.out.println("TPC-H tables not found. Creating schema...");
            createTables();
            System.out.println("TPC-H tables created.");
        } else {
            System.out.println("TPC-H tables already exist. No DDL needed.");
        }

        return connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection to JavaDB closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //helpers 

    private boolean tableExists(String schema, String table) throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        try (ResultSet rs =
                     meta.getTables(null, schema.toUpperCase(), table.toUpperCase(), null)) {
            return rs.next();
        }
    }

    /**
     * Creates all TPC-H tables using the layout from TPC-H_DDL-1.sql.
     */
    private void createTables() throws SQLException {
        String[] ddlStatements = new String[]{

            // REGION
            "CREATE TABLE REGION (" +
            "    R_REGIONKEY INTEGER NOT NULL," +
            "    R_NAME      CHAR(25) NOT NULL," +
            "    R_COMMENT   VARCHAR(152)," +
            "    PRIMARY KEY (R_REGIONKEY)" +
            ")",

            // NATION
            "CREATE TABLE NATION (" +
            "    N_NATIONKEY INTEGER NOT NULL," +
            "    N_NAME      CHAR(25) NOT NULL," +
            "    N_REGIONKEY INTEGER NOT NULL," +
            "    N_COMMENT   VARCHAR(152)," +
            "    PRIMARY KEY (N_NATIONKEY)," +
            "    FOREIGN KEY (N_REGIONKEY) REFERENCES REGION(R_REGIONKEY)" +
            ")",

            // PART
            "CREATE TABLE PART (" +
            "    P_PARTKEY     INTEGER NOT NULL," +
            "    P_NAME        VARCHAR(55) NOT NULL," +
            "    P_MFGR        CHAR(25) NOT NULL," +
            "    P_BRAND       CHAR(10) NOT NULL," +
            "    P_TYPE        VARCHAR(25) NOT NULL," +
            "    P_SIZE        INTEGER NOT NULL," +
            "    P_CONTAINER   CHAR(10) NOT NULL," +
            "    P_RETAILPRICE DECIMAL(15,2) NOT NULL," +
            "    P_COMMENT     VARCHAR(23) NOT NULL," +
            "    PRIMARY KEY (P_PARTKEY)" +
            ")",

            // SUPPLIER
            "CREATE TABLE SUPPLIER (" +
            "    S_SUPPKEY   INTEGER NOT NULL," +
            "    S_NAME      CHAR(25) NOT NULL," +
            "    S_ADDRESS   VARCHAR(40) NOT NULL," +
            "    S_NATIONKEY INTEGER NOT NULL," +
            "    S_PHONE     CHAR(15) NOT NULL," +
            "    S_ACCTBAL   DECIMAL(15,2) NOT NULL," +
            "    S_COMMENT   VARCHAR(101) NOT NULL," +
            "    PRIMARY KEY (S_SUPPKEY)," +
            "    FOREIGN KEY (S_NATIONKEY) REFERENCES NATION(N_NATIONKEY)" +
            ")",

            // CUSTOMER
            "CREATE TABLE CUSTOMER (" +
            "    C_CUSTKEY    INTEGER NOT NULL," +
            "    C_NAME       VARCHAR(25) NOT NULL," +
            "    C_ADDRESS    VARCHAR(40) NOT NULL," +
            "    C_NATIONKEY  INTEGER NOT NULL," +
            "    C_PHONE      CHAR(15) NOT NULL," +
            "    C_ACCTBAL    DECIMAL(15,2) NOT NULL," +
            "    C_MKTSEGMENT CHAR(10) NOT NULL," +
            "    C_COMMENT    VARCHAR(117) NOT NULL," +
            "    PRIMARY KEY (C_CUSTKEY)," +
            "    FOREIGN KEY (C_NATIONKEY) REFERENCES NATION(N_NATIONKEY)" +
            ")",

            // ORDERS
            "CREATE TABLE ORDERS (" +
            "    O_ORDERKEY      INTEGER NOT NULL," +
            "    O_CUSTKEY       INTEGER NOT NULL," +
            "    O_ORDERSTATUS   CHAR(1) NOT NULL," +
            "    O_TOTALPRICE    DECIMAL(15,2) NOT NULL," +
            "    O_ORDERDATE     DATE NOT NULL," +
            "    O_ORDERPRIORITY CHAR(15) NOT NULL," +
            "    O_CLERK         CHAR(15) NOT NULL," +
            "    O_SHIPPRIORITY  INTEGER NOT NULL," +
            "    O_COMMENT       VARCHAR(79) NOT NULL," +
            "    PRIMARY KEY (O_ORDERKEY)," +
            "    FOREIGN KEY (O_CUSTKEY) REFERENCES CUSTOMER(C_CUSTKEY)" +
            ")",

            // PARTSUPP
            "CREATE TABLE PARTSUPP (" +
            "    PS_PARTKEY    INTEGER NOT NULL," +
            "    PS_SUPPKEY    INTEGER NOT NULL," +
            "    PS_AVAILQTY   INTEGER NOT NULL," +
            "    PS_SUPPLYCOST DECIMAL(15,2) NOT NULL," +
            "    PS_COMMENT    VARCHAR(199) NOT NULL," +
            "    PRIMARY KEY (PS_PARTKEY, PS_SUPPKEY)," +
            "    FOREIGN KEY (PS_PARTKEY) REFERENCES PART(P_PARTKEY)," +
            "    FOREIGN KEY (PS_SUPPKEY) REFERENCES SUPPLIER(S_SUPPKEY)" +
            ")",

            // LINEITEM
            "CREATE TABLE LINEITEM (" +
            "    L_ORDERKEY      INTEGER NOT NULL," +
            "    L_PARTKEY       INTEGER NOT NULL," +
            "    L_SUPPKEY       INTEGER NOT NULL," +
            "    L_LINENUMBER    INTEGER NOT NULL," +
            "    L_QUANTITY      DECIMAL(15,2) NOT NULL," +
            "    L_EXTENDEDPRICE DECIMAL(15,2) NOT NULL," +
            "    L_DISCOUNT      DECIMAL(15,2) NOT NULL," +
            "    L_TAX           DECIMAL(15,2) NOT NULL," +
            "    L_RETURNFLAG    CHAR(1) NOT NULL," +
            "    L_LINESTATUS    CHAR(1) NOT NULL," +
            "    L_SHIPDATE      DATE NOT NULL," +
            "    L_COMMITDATE    DATE NOT NULL," +
            "    L_RECEIPTDATE   DATE NOT NULL," +
            "    L_SHIPINSTRUCT  CHAR(25) NOT NULL," +
            "    L_SHIPMODE      CHAR(10) NOT NULL," +
            "    L_COMMENT       VARCHAR(44) NOT NULL," +
            "    PRIMARY KEY (L_ORDERKEY, L_LINENUMBER)," +
            "    FOREIGN KEY (L_ORDERKEY) REFERENCES ORDERS(O_ORDERKEY)," +
            "    FOREIGN KEY (L_PARTKEY) REFERENCES PART(P_PARTKEY)," +
            "    FOREIGN KEY (L_SUPPKEY) REFERENCES SUPPLIER(S_SUPPKEY)," +
            "    FOREIGN KEY (L_PARTKEY, L_SUPPKEY) REFERENCES PARTSUPP(PS_PARTKEY, PS_SUPPKEY)" +
            ")"
        };

        try (Statement statement = connection.createStatement()) {
            for (String ddl : ddlStatements) {
                System.out.println("Executing DDL: " + ddl);
                statement.executeUpdate(ddl);
            }
        }
    }
}
