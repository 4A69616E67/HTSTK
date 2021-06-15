package com.github.snowflake.Launcher;

import com.github.snowflake.Launcher.Content.Content;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class Controller {

    public AnchorPane Content_AnchorPane;
    public Button ChIP_Seq_Button;
    public Button RNA_Seq_Button;

    public void Show_ChIP_Content(MouseEvent mouseEvent) throws Exception{
        Content_AnchorPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Content/content.fxml"));
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane pane = loader.load() ;
        Content content_controller = loader.getController();
        content_controller.ChIP_Seq_TextArea.setText("ChIP-Seq test Area");
        Content_AnchorPane.getChildren().addAll(pane.getChildren());
    }

    public void Show_RNA_Content(MouseEvent mouseEvent) throws Exception{
        Content_AnchorPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Content/content.fxml"));
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane pane = loader.load() ;
        Content content_controller = loader.getController();
        content_controller.ChIP_Seq_TextArea.setText("RNA-Seq test Area");
        Content_AnchorPane.getChildren().addAll(pane.getChildren());
    }
}
