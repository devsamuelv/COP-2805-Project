package edu.easternflorida.villegas;

import java.sql.Connection;

/**
 * Abstract base class for database connection.
 * TPC_DBAPI will extend this and implement the connect method.
 */
public abstract class TPC_DBBase {
    public abstract Connection connect();
}
