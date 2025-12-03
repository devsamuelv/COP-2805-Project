package edu.easternflorida.joneso;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class PartSuppTableDisplay extends Application {

    @Override
    public void start(Stage stage) {

        TableView<PartSupp> table = new TableView<>();

        TableColumn<PartSupp, Integer> partKeyCol = new TableColumn<>("PS_PARTKEY");
        partKeyCol.setCellValueFactory(new PropertyValueFactory<>("psPartKey"));

        TableColumn<PartSupp, Integer> suppKeyCol = new TableColumn<>("PS_SUPPKEY");
        suppKeyCol.setCellValueFactory(new PropertyValueFactory<>("psSuppKey"));

        TableColumn<PartSupp, Integer> qtyCol = new TableColumn<>("PS_AVAILQTY");
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("psAvailQty"));

        TableColumn<PartSupp, Double> costCol = new TableColumn<>("PS_SUPPLYCOST");
        costCol.setCellValueFactory(new PropertyValueFactory<>("psSupplyCost"));

        TableColumn<PartSupp, String> commentCol = new TableColumn<>("PS_COMMENT");
        commentCol.setCellValueFactory(new PropertyValueFactory<>("psComment"));

        table.getColumns().addAll(partKeyCol, suppKeyCol, qtyCol, costCol, commentCol);

        ObservableList<PartSupp> list = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/tpchdb;user=app;password=app");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM PARTSUPP")) {

            while (rs.next()) {
                list.add(new PartSupp(
                        rs.getInt("PS_PARTKEY"),
                        rs.getInt("PS_SUPPKEY"),
                        rs.getInt("PS_AVAILQTY"),
                        rs.getDouble("PS_SUPPLYCOST"),
                        rs.getString("PS_COMMENT")
                ));
            }

            System.out.println("Loaded " + list.size() + " PartSupp rows.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        table.setItems(list);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox layout = new VBox(table);
        Scene scene = new Scene(layout, 1000, 600);

        stage.setTitle("PartSupp Table – Team C");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
