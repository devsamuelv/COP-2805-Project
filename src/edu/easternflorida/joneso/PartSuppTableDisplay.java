package edu.easternflorida.joneso;

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

import java.sql.*;
import java.util.Collection;

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
    
    Collection<edu.easternflorida.villegas.interfaces.PartSupp> partSupp = TPC_API.readAllPartSupp();
    partSupp.forEach((p) -> {
        list.add(new PartSupp(p.getPS_PARTKEY(), p.getPS_SUPPKEY(), p.getPS_AVAILQTY(), p.getPS_SUPPLYCOST().doubleValue(), p.getPS_COMMENT()));
    });

    table.setItems(list);
    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    VBox layout = new VBox(table);
    Scene scene = new Scene(layout, 1000, 600);

    stage.setTitle("PartSupp Table – Team C");
    stage.setScene(scene);
    stage.show();
  }

//  public static void main(String[] args) {
//    launch(args);
//  }
}
