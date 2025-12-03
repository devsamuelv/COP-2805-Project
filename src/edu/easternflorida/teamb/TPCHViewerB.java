package edu.easternflorida.teamb;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.*;

public class TPCHViewerB extends Application {

  private static final String JDBC_URL = "jdbc:derby:JavaDB";

  private ObservableList<Region> regionData = FXCollections.observableArrayList();
  private ObservableList<Nation> nationData = FXCollections.observableArrayList();
  private ObservableList<Supplier> supplierData = FXCollections.observableArrayList();
  private ObservableList<LineItem> lineItemData = FXCollections.observableArrayList();

  private TableView<Region> regionTable = new TableView<>();

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {

    setupRegionTable();
    TableView<Nation> nationTable = setupNationTable();
    TableView<Supplier> supplierTable = setupSupplierTable();
    TableView<LineItem> lineItemTable = setupLineItemTable();

    loadAllData();

    Button addRegionButton = new Button("Insert New Region");
    addRegionButton.setOnAction(e -> showAddRegionDialog());

    HBox controls = new HBox(10, addRegionButton);
    controls.setPadding(new Insets(10));

    HBox topTables = new HBox(20, regionTable, nationTable);
    topTables.setPadding(new Insets(10));
    topTables.setStyle("-fx-background-color: #f4f4f4;");

    HBox bottomTables = new HBox(20, supplierTable, lineItemTable);
    bottomTables.setPadding(new Insets(10));
    bottomTables.setStyle("-fx-background-color: #e8e8e8;");

    VBox root = new VBox(10, controls, topTables, bottomTables);
    root.setPadding(new Insets(10));

    Scene scene = new Scene(root, 1200, 700);

    primaryStage.setTitle("Team B - TPC-H Viewer (Region, Nation, Supplier, LineItem)");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void setupRegionTable() {

    TableColumn<Region, Integer> rKeyCol = new TableColumn<>("R_REGIONKEY");
    rKeyCol.setCellValueFactory(new PropertyValueFactory<>("r_regionkey"));

    TableColumn<Region, String> rNameCol = new TableColumn<>("R_NAME");
    rNameCol.setCellValueFactory(new PropertyValueFactory<>("r_name"));

    TableColumn<Region, String> rCommentCol = new TableColumn<>("R_COMMENT");
    rCommentCol.setCellValueFactory(new PropertyValueFactory<>("r_comment"));

    regionTable.getColumns().addAll(rKeyCol, rNameCol, rCommentCol);
    regionTable.setItems(regionData);
    regionTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    regionTable.setPrefWidth(500);
  }

  private TableView<Nation> setupNationTable() {
    TableView<Nation> nationTable = new TableView<>();

    TableColumn<Nation, Integer> nKeyCol = new TableColumn<>("N_NATIONKEY");
    nKeyCol.setCellValueFactory(new PropertyValueFactory<>("n_nationkey"));

    TableColumn<Nation, String> nNameCol = new TableColumn<>("N_NAME");
    nNameCol.setCellValueFactory(new PropertyValueFactory<>("n_name"));

    TableColumn<Nation, Integer> nRKeyCol = new TableColumn<>("N_REGIONKEY");
    nRKeyCol.setCellValueFactory(new PropertyValueFactory<>("n_regionkey"));

    nationTable.getColumns().addAll(nKeyCol, nNameCol, nRKeyCol);
    nationTable.setItems(nationData);
    nationTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    nationTable.setPrefWidth(500);

    return nationTable;
  }

  private TableView<Supplier> setupSupplierTable() {
    TableView<Supplier> supplierTable = new TableView<>();

    TableColumn<Supplier, Integer> sIdCol = new TableColumn<>("S_SUPPKEY");
    sIdCol.setCellValueFactory(new PropertyValueFactory<>("s_id"));

    TableColumn<Supplier, String> sNameCol = new TableColumn<>("S_NAME");
    sNameCol.setCellValueFactory(new PropertyValueFactory<>("s_name"));

    TableColumn<Supplier, String> sAddressCol = new TableColumn<>("S_ADDRESS");
    sAddressCol.setCellValueFactory(new PropertyValueFactory<>("s_address"));

    supplierTable.getColumns().addAll(sIdCol, sNameCol, sAddressCol);
    supplierTable.setItems(supplierData);
    supplierTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    supplierTable.setPrefWidth(600);

    return supplierTable;
  }

  private TableView<LineItem> setupLineItemTable() {
    TableView<LineItem> lineItemTable = new TableView<>();

    TableColumn<LineItem, Integer> lOrderKeyCol = new TableColumn<>("L_ORDERKEY");
    lOrderKeyCol.setCellValueFactory(new PropertyValueFactory<>("l_orderkey"));

    TableColumn<LineItem, Integer> lPartKeyCol = new TableColumn<>("L_PARTKEY");
    lPartKeyCol.setCellValueFactory(new PropertyValueFactory<>("l_partkey"));

    TableColumn<LineItem, Integer> lSuppKeyCol = new TableColumn<>("L_SUPPKEY");
    lSuppKeyCol.setCellValueFactory(new PropertyValueFactory<>("l_suppkey"));

    TableColumn<LineItem, Double> lQtyCol = new TableColumn<>("L_QUANTITY");
    lQtyCol.setCellValueFactory(new PropertyValueFactory<>("l_quantity"));

    lineItemTable.getColumns().addAll(lOrderKeyCol, lPartKeyCol, lSuppKeyCol, lQtyCol);
    lineItemTable.setItems(lineItemData);
    lineItemTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    lineItemTable.setPrefWidth(600);

    return lineItemTable;
  }

  private void loadAllData() {
    loadRegionData();
    loadNationData();
    loadSuppliers();
    loadLineItems();
  }

  private void loadRegionData() {
    ObservableList<Region> list = FXCollections.observableArrayList();
    try (Connection conn = DriverManager.getConnection(JDBC_URL);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT R_REGIONKEY, R_NAME, R_COMMENT FROM REGION")) {

      while (rs.next()) {
        list.add(new Region(
            rs.getInt("R_REGIONKEY"),
            rs.getString("R_NAME"),
            rs.getString("R_COMMENT")));
      }

      regionData.setAll(list);
    } catch (SQLException e) {
      showError("Failed to load Region data: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private void loadNationData() {
    ObservableList<Nation> list = FXCollections.observableArrayList();
    try (Connection conn = DriverManager.getConnection(JDBC_URL);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT N_NATIONKEY, N_NAME, N_REGIONKEY FROM NATION")) {

      while (rs.next()) {
        list.add(new Nation(
            rs.getInt("N_NATIONKEY"),
            rs.getString("N_NAME"),
            rs.getInt("N_REGIONKEY")));
      }
      nationData.setAll(list);
    } catch (SQLException e) {
      showError("Failed to load Nation data: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private void loadSuppliers() {
    ObservableList<Supplier> list = FXCollections.observableArrayList();
    try (Connection conn = DriverManager.getConnection(JDBC_URL);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT S_SUPPKEY, S_NAME, S_ADDRESS FROM SUPPLIER")) {

      while (rs.next()) {
        list.add(new Supplier(
            rs.getInt("S_SUPPKEY"),
            rs.getString("S_NAME"),
            rs.getString("S_ADDRESS")));
      }
      supplierData.setAll(list);
    } catch (SQLException e) {
      showError("Failed to load Supplier data: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private void loadLineItems() {
    ObservableList<LineItem> list = FXCollections.observableArrayList();
    try (Connection conn = DriverManager.getConnection(JDBC_URL);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT L_ORDERKEY, L_PARTKEY, L_SUPPKEY, L_QUANTITY FROM LINEITEM")) {

      while (rs.next()) {
        list.add(new LineItem(
            rs.getInt("L_ORDERKEY"),
            rs.getInt("L_PARTKEY"),
            rs.getInt("L_SUPPKEY"),
            rs.getDouble("L_QUANTITY")));
      }
      lineItemData.setAll(list);
    } catch (SQLException e) {
      showError("Failed to load LineItem data: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private void insertRegion(int regionKey, String name, String comment) throws SQLException {

    String sql = "INSERT INTO REGION (R_REGIONKEY, R_NAME, R_COMMENT) VALUES (?, ?, ?)";
    try (Connection conn = DriverManager.getConnection(JDBC_URL);
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setInt(1, regionKey);
      pstmt.setString(2, name);
      pstmt.setString(3, comment);

      int rowsAffected = pstmt.executeUpdate();
      if (rowsAffected > 0) {
        showSuccess("Region record inserted successfully.");
      } else {
        throw new SQLException("Insert failed, 0 rows affected.");
      }
    }
  }

  private void showAddRegionDialog() {
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Add Region Record");

    TextField regionKeyField = new TextField();
    TextField nameField = new TextField();
    TextArea commentField = new TextArea();

    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20));
    grid.add(new Label("Region Key:"), 0, 0);
    grid.add(regionKeyField, 1, 0);
    grid.add(new Label("Name:"), 0, 1);
    grid.add(nameField, 1, 1);
    grid.add(new Label("Comment:"), 0, 2);
    grid.add(commentField, 1, 2);

    dialog.getDialogPane().setContent(grid);
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    dialog.setResultConverter(button -> {
      if (button == ButtonType.OK) {
        try {

          int regionKey = Integer.parseInt(regionKeyField.getText().trim());
          String name = nameField.getText().trim();
          String comment = commentField.getText().trim();

          if (name.isEmpty()) {
            throw new IllegalArgumentException("Region Name cannot be empty.");
          }

          insertRegion(regionKey, name, comment);

          loadRegionData();
        } catch (NumberFormatException e) {
          showError("Invalid input: Region Key must be a valid integer.");
        } catch (IllegalArgumentException e) {
          showError("Invalid input: " + e.getMessage());
        } catch (Exception e) {

          showError("Insert failed: " + e.getMessage());
        }
      }
      return null;
    });

    dialog.showAndWait();
  }

  private void showError(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }

  private void showSuccess(String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Success");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }

  public static class Region {
    private final int r_regionkey;
    private final String r_name;
    private final String r_comment;

    public Region(int r_regionkey, String r_name, String r_comment) {
      this.r_regionkey = r_regionkey;
      this.r_name = r_name;
      this.r_comment = r_comment;
    }

    public int getR_regionkey() {
      return r_regionkey;
    }

    public String getR_name() {
      return r_name;
    }

    public String getR_comment() {
      return r_comment;
    }
  }

  public static class Nation {
    private final int n_nationkey;
    private final String n_name;
    private final int n_regionkey;

    public Nation(int n_nationkey, String n_name, int n_regionkey) {
      this.n_nationkey = n_nationkey;
      this.n_name = n_name;
      this.n_regionkey = n_regionkey;
    }

    public int getN_nationkey() {
      return n_nationkey;
    }

    public String getN_name() {
      return n_name;
    }

    public int getN_regionkey() {
      return n_regionkey;
    }
  }

  public static class Supplier {
    private final int s_id;
    private final String s_name;
    private final String s_address;

    public Supplier(int s_id, String s_name, String s_address) {
      this.s_id = s_id;
      this.s_name = s_name;
      this.s_address = s_address;
    }

    public int getS_id() {
      return s_id;
    }

    public String getS_name() {
      return s_name;
    }

    public String getS_address() {
      return s_address;
    }
  }

  public static class LineItem {
    private final int l_orderkey;
    private final int l_partkey;
    private final int l_suppkey;
    private final double l_quantity;

    public LineItem(int l_orderkey, int l_partkey, int l_suppkey, double l_quantity) {
      this.l_orderkey = l_orderkey;
      this.l_partkey = l_partkey;
      this.l_suppkey = l_suppkey;
      this.l_quantity = l_quantity;
    }

    public int getL_orderkey() {
      return l_orderkey;
    }

    public int getL_partkey() {
      return l_partkey;
    }

    public int getL_suppkey() {
      return l_suppkey;
    }

    public double getL_quantity() {
      return l_quantity;
    }
  }
}