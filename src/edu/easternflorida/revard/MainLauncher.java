package edu.easternflorida.revard;

import edu.easternflorida.teamb.TPCHViewerB;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainLauncher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Main Screen");

        Button openPartsBtn = new Button("Open Part Table");        
        Button openOtherBtn = new Button("Open Other Table");

        openPartsBtn.setOnAction(e -> {
            try {
                new PartTableDisplayTest().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        openOtherBtn.setOnAction(e -> {
            try {
                new TPCHViewerB().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        FlowPane layout = new FlowPane(openPartsBtn, openOtherBtn);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
