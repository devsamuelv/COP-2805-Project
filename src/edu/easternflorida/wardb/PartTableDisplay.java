package edu.easternflorida.wardb;

import static edu.easternflorida.revard.MainLauncher.TPC_API;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Collection;

public class PartTableDisplay extends Application {

    @Override
    public void start(Stage stage) {
        TableView<Part> tableOfParts = new TableView<>();

        // Define columns
        TableColumn<Part, Integer> partIdColumn = new TableColumn<>("Part ID");
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("P_PARTKEY"));

        TableColumn<Part, String> partNameColumn = new TableColumn<>("Name");
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("P_NAME"));

        TableColumn<Part, String> partMFGRColumn = new TableColumn<>("Manufacturer");
        partMFGRColumn.setCellValueFactory(new PropertyValueFactory<>("P_MFGR"));

        TableColumn<Part, String> partBrandColumn = new TableColumn<>("Brand");
        partBrandColumn.setCellValueFactory(new PropertyValueFactory<>("P_BRAND"));

        TableColumn<Part, String> partTypeColumn = new TableColumn<>("Type");
        partTypeColumn.setCellValueFactory(new PropertyValueFactory<>("P_TYPE"));

        TableColumn<Part, Integer> partSizeColumn = new TableColumn<>("Size");
        partSizeColumn.setCellValueFactory(new PropertyValueFactory<>("P_SIZE"));

        TableColumn<Part, String> partContainerColumn = new TableColumn<>("Container");
        partContainerColumn.setCellValueFactory(new PropertyValueFactory<>("P_CONTAINER"));

        TableColumn<Part, Double> partPriceColumn = new TableColumn<>("Retail Price");
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("P_RETAILPRICE"));

        TableColumn<Part, String> partCommentColumn = new TableColumn<>("Comment");
        partCommentColumn.setCellValueFactory(new PropertyValueFactory<>("P_COMMENT"));

        // Add columns to table
        tableOfParts.getColumns().addAll(
                partIdColumn,
                partNameColumn,
                partMFGRColumn,
                partBrandColumn,
                partTypeColumn,
                partSizeColumn,
                partContainerColumn,
                partPriceColumn,
                partCommentColumn
        );

        // Load data from Derby
        ObservableList<Part> parts = FXCollections.observableArrayList();
        
        try {
            System.out.println("Loading parts...");
            
            Collection<edu.easternflorida.villegas.interfaces.Part> dbParts = TPC_API.readAllParts().values();
            dbParts.forEach((part) -> {
                parts.add(new Part(part.getP_PARTKEY(), part.getP_NAME(), part.getP_MFGR(), part.getP_BRAND(), part.getP_TYPE(), part.getP_SIZE(), part.getP_CONTAINER(), part.getP_RETAILPRICE().doubleValue(), part.getP_COMMENT()));
            });
            
            System.out.println("✅ Loaded " + parts.size() + " parts from database.");
        } catch (Exception err) {
            System.out.println("⚠️ Failed to load parts from the database.");
            err.printStackTrace();
        }

        // Attach data to the table
        tableOfParts.setItems(parts);
        tableOfParts.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Layout
        VBox layout = new VBox(tableOfParts);
        Scene scene = new Scene(layout, 1200, 600);
        stage.setTitle("Parts Table – Team C");
        stage.setScene(scene);
        stage.show();
    }
}
