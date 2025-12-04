package edu.easternflorida.revard;

import edu.easternflorida.joneso.MainMenuTeamC;
import edu.easternflorida.teamb.TPCHViewerB;
import edu.easternflorida.villegas.TPC_DBAPI;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MainLauncher extends Application {
    public static TPC_DBAPI TPC_API = new TPC_DBAPI();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TPC Screen Chooser");

        Button openTeamBBtn = new Button("Open Team B GUI");        
        Button openTeamCBtn = new Button("Open Team C GUI");

        openTeamBBtn.setOnAction(e -> {
            try {
                new TPCHViewerB().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        openTeamCBtn.setOnAction(e -> {
            try {
                new MainMenuTeamC().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        FlowPane layout = new FlowPane(openTeamBBtn, openTeamCBtn);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
