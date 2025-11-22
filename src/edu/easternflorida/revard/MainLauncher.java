package edu.easternflorida.revard;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        openPartsBtn.setOnAction(e -> {
            try {
                new PartTableDisplayTest().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        StackPane layout = new StackPane(openPartsBtn);
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
