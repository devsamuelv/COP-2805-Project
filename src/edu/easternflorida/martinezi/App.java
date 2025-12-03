package edu.easternflorida.martinezi;

import edu.easternflorida.villegas.TPC_DBAPI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.*;
import java.util.Collection;
import java.util.List;
import javafx.collections.*;

public class App extends Application {

    private TableView<Customer> table = new TableView<>();
    private ObservableList<Customer> data = FXCollections.observableArrayList();
    private TPC_DBAPI dbapi = new TPC_DBAPI();
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        loadCustomers();

        TableColumn<Customer, Integer> keyCol = new TableColumn<>("ID");
        keyCol.setCellValueFactory(c -> c.getValue().custKeyProperty().asObject());

        TableColumn<Customer, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(c -> c.getValue().nameProperty());

        TableColumn<Customer, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(c -> c.getValue().phoneProperty());

        table.getColumns().addAll(keyCol, nameCol, phoneCol);
        table.setItems(data);

        Button addBtn = new Button("Add Customer");
        addBtn.setOnAction(e -> showAddDialog());

        VBox root = new VBox(10, table, addBtn);
        root.setPrefSize(600, 400);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Customer Table - JavaFX + Derby");
        primaryStage.show();
    }

    private void loadCustomers() {
        Collection<edu.easternflorida.villegas.interfaces.Customer> customers = dbapi.readAllCustomers().values();
        
        customers.forEach((c) -> {
            data.add(new Customer(c.getC_CUSTKEY(), c.getC_NAME(), c.getC_PHONE()));
        });
    }

    private void showAddDialog() {
        Dialog<Customer> dialog = new Dialog<>();
        dialog.setTitle("Add Customer");

        TextField idField = new TextField();
        TextField nameField = new TextField();
        TextField phoneField = new TextField();

        VBox content = new VBox(10,
                new Label("Customer ID:"), idField,
                new Label("Name:"), nameField,
                new Label("Phone:"), phoneField);
        dialog.getDialogPane().setContent(content);

        ButtonType addBtnType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addBtnType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addBtnType) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    String name = nameField.getText();
                    String phone = phoneField.getText();

                    addCustomer(id, name, phone);
                    return new Customer(id, name, phone);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid ID");
                    alert.showAndWait();
                }
            }
            return null;
        });

        dialog.showAndWait();
        loadCustomers();
    }

    private void addCustomer(int id, String name, String phone) {
        String sql = "INSERT INTO Customer (c_custkey, c_name, c_address, c_nationkey, c_phone, c_acctbal, c_mktsegment, c_comment) VALUES (?, ?, '', 0, ?, 0.0, '', '')";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, phone);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
