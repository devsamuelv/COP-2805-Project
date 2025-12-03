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

public class OrdersTableDisplay extends Application {

    @Override
    public void start(Stage stage) {

        TableView<Order> table = new TableView<>();

        TableColumn<Order, Integer> orderKeyCol = new TableColumn<>("O_ORDERKEY");
        orderKeyCol.setCellValueFactory(new PropertyValueFactory<>("orderKey"));

        TableColumn<Order, Integer> custKeyCol = new TableColumn<>("O_CUSTKEY");
        custKeyCol.setCellValueFactory(new PropertyValueFactory<>("custKey"));

        TableColumn<Order, String> statusCol = new TableColumn<>("O_ORDERSTATUS");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<Order, Double> priceCol = new TableColumn<>("O_TOTALPRICE");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        TableColumn<Order, String> dateCol = new TableColumn<>("O_ORDERDATE");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

        table.getColumns().addAll(orderKeyCol, custKeyCol, statusCol, priceCol, dateCol);

        ObservableList<Order> list = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/tpchdb;user=app;password=app");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM ORDERS")) {

            while (rs.next()) {
                list.add(new Order(
                        rs.getInt("O_ORDERKEY"),
                        rs.getInt("O_CUSTKEY"),
                        rs.getString("O_ORDERSTATUS"),
                        rs.getDouble("O_TOTALPRICE"),
                        rs.getDate("O_ORDERDATE").toString()
                ));
            }

            System.out.println("Loaded " + list.size() + " Orders.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        table.setItems(list);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox layout = new VBox(table);
        Scene scene = new Scene(layout, 1200, 600);

        stage.setTitle("Orders Table – Team C");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
