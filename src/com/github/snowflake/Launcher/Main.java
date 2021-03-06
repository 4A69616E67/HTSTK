package com.github.snowflake.Launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        root.show();
        primaryStage.setTitle("HTSTK");
        primaryStage.setScene(new Scene(root, 1500, 900));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
