package edu.easternflorida.villegas.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract interface TPC_DBInterf {
  public abstract void insertPart(Part part) throws IllegalArgumentException, SQLException;

  public abstract HashMap<Integer, Part> readAllParts();

  public abstract void insertRegion(Region region) throws IllegalArgumentException, SQLException;

  public abstract HashMap<Integer, Region> readAllRegions();

  public abstract void insertNation(Nation nation) throws IllegalArgumentException, SQLException;

  public abstract HashMap<Integer, Nation> readAllNations();

  public abstract void insertSupplier(Supplier supplier) throws IllegalArgumentException, SQLException;

  public abstract HashMap<Integer, Supplier> readAllSuppliers();

  public abstract void insertPartSupp(PartSupp partSupp) throws IllegalArgumentException, SQLException;

  public abstract ArrayList<PartSupp> readAllPartSupp();

  public abstract void insertCustomer(Customer customer) throws IllegalArgumentException, SQLException;

  public abstract HashMap<Integer, Customer> readAllCustomers();

  public abstract void insertOrders(Orders orders) throws IllegalArgumentException, SQLException;

  public abstract HashMap<Integer, Orders> readAllOrders();

  public abstract void insertLineitem(Lineitem lineitem) throws IllegalArgumentException, SQLException;

  public abstract ArrayList<Lineitem> readAllLineitems();
}
