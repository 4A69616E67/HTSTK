package com.github.snowflake.Launcher;

import com.github.snowflake.Launcher.Content.Content;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Controller {

    public AnchorPane Content_AnchorPane;
    public Button ChIP_Seq_Button;
    public Button RNA_Seq_Button;
    public Button Other_Tools_Button;

    public void Show_ChIP_Content(MouseEvent mouseEvent) throws Exception {
        Content_AnchorPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Content/content.fxml"));
        AnchorPane pane = loader.load();
        Content controller = loader.getController();
        controller.InitChIPSeqContent();
        Content_AnchorPane.getChildren().addAll(pane.getChildren());
    }

    public void Show_RNA_Content(MouseEvent mouseEvent) throws Exception {
        Content_AnchorPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Content/content.fxml"));
        AnchorPane pane = loader.load();
        Content content_controller = loader.getController();
        content_controller.Content_TextArea.setText("RNA-Seq test Area");
        Content_AnchorPane.getChildren().addAll(pane.getChildren());
    }

    public void Show_Other_Content(MouseEvent mouseEvent) throws Exception {
        Content_AnchorPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Content/content.fxml"));
        AnchorPane pane = loader.load();
        Content controller = loader.getController();
        controller.InitOtherToolsContent();
        Content_AnchorPane.getChildren().addAll(pane.getChildren());
    }
}
