package com.github.snowflake.Launcher;

import com.github.snowflake.Archive.Path;
import com.github.snowflake.Controls.ImageLabelButton;
import com.github.snowflake.Launcher.Content.Content;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller {

    public AnchorPane Content_AnchorPane;
    public Button ChIP_Seq_Button;
    public Button RNA_Seq_Button;
    public Button Other_Tools_Button;

    public void Show_ChIP_Content(MouseEvent mouseEvent) throws Exception {
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
}
