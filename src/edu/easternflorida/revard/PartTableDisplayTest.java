package edu.easternflorida.revard;

import edu.easternflorida.villegas.interfaces.Part;
import edu.easternflorida.villegas.TPC_DBAPI;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;

public class PartTableDisplayTest extends Application {

    private TableView<Part> tableOfParts = new TableView<>();
    private ObservableList<Part> partList = FXCollections.observableArrayList();
    private TPC_DBAPI dbapi = new TPC_DBAPI();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Part Table Display");

        TableColumn<Part, Integer> partIdColumn = new TableColumn<>("Part ID");
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("P_PARTKEY"));

        TableColumn<Part, String> partNameColumn = new TableColumn<>("Name");
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("P_NAME"));

        TableColumn<Part, String> partMFGRColumn = new TableColumn<>("MFGR");
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

        tableOfParts.getColumns().addAll(
            partIdColumn, partNameColumn, partMFGRColumn, partBrandColumn,
            partTypeColumn, partSizeColumn, partContainerColumn,
            partPriceColumn, partCommentColumn
        );

        tableOfParts.setItems(partList);
        tableOfParts.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        loadParts();

        VBox layout = new VBox(tableOfParts);
        Scene scene = new Scene(layout, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void loadParts() {
        partList.clear();
        HashMap<Integer, Part> parts = dbapi.readAllParts();
        partList.addAll(parts.values());
    }
}
