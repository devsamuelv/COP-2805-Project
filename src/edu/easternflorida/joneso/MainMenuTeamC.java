package edu.easternflorida.joneso;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Correct package imports
import edu.easternflorida.martinezi.App;
import edu.easternflorida.wardb.PartTableDisplay;
import edu.easternflorida.joneso.PartSuppTableDisplay;
import edu.easternflorida.joneso.OrdersTableDisplay;

public class MainMenuTeamC extends Application {

  @Override
  public void start(Stage primaryStage) {

    Button customerBtn = new Button("View Customers");
    Button partsBtn = new Button("View Parts");
    Button partsuppBtn = new Button("View PartSupp");
    Button ordersBtn = new Button("View Orders");

    customerBtn.setOnAction(e -> new App().start(new Stage()));
    partsBtn.setOnAction(e -> new PartTableDisplay().start(new Stage()));
    partsuppBtn.setOnAction(e -> new PartSuppTableDisplay().start(new Stage()));
    ordersBtn.setOnAction(e -> new OrdersTableDisplay().start(new Stage()));

    VBox layout = new VBox(20, customerBtn, partsBtn, partsuppBtn, ordersBtn);
    layout.setSpacing(20);

    primaryStage.setTitle("TPC-H Dashboard – Team C");
    primaryStage.setScene(new Scene(layout, 400, 300));
    primaryStage.show();
  }

//  public static void main(String[] args) {
//    launch(args);
//  }
}
