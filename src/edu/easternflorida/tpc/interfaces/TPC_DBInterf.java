package edu.easternflorida.tpc.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class TPC_DBInterf {
  public abstract void insertPart(Part part) throws IllegalArgumentException, SQLException;

  public abstract HashMap<Integer, Part> readAllParts();

  public abstract void insertRegion(Region region) throws IllegalArgumentException, SQLException;

  public abstract HashMap<Integer, Region> readAllRegions();

  public abstract void insertNation(Nation nation) throws IllegalArgumentException, SQLException;

  public abstract HashMap<Integer, Nation> readAllNations();

  public abstract void insertSupplier(Supplier supplier) throws IllegalArgumentException, SQLException;

  public abstract HashMap<Integer, Supplier> readAllSuppliers();
}
