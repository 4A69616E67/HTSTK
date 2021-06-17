package com.github.snowflake.Launcher.Content;

import com.github.snowflake.Archive.Path;
import com.github.snowflake.Controls.ImageLabelButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Content {
    public FlowPane Content_FlowPane;
    public TextArea Content_TextArea;
    public VBox Content_VBox;

    public void InitChIPSeqContent(){
        Content_FlowPane.getChildren().clear();
        Content_TextArea.setText("ChIP-Seq test Area");
        ImageLabelButton Pipeline = new ImageLabelButton(Path.TextIcon,"ChIP-Seq pipeline");
        Pipeline.setOnMouseClicked(event -> {
            Stage ChIP_Seq_Pipeline_Stage = new Stage();
            ChIP_Seq_Pipeline_Stage.setTitle("ChIP-Seq pipeline");
            ChIP_Seq_Pipeline_Stage.show();
        });
        ImageLabelButton Statistic = new ImageLabelButton(Path.TextIcon,"Statistic");
        ImageLabelButton PeakCompare = new ImageLabelButton(Path.TextIcon,"PeakCompare");
        Content_FlowPane.getChildren().addAll(Pipeline, Statistic, PeakCompare);
        Content_FlowPane.setPrefHeight(Pipeline.getHeight());
//        Content_TextArea.setPrefHeight(Content_VBox.getHeight()-Content_FlowPane.getHeight());
    }

    public void InitOtherToolsContent(){
        Content_FlowPane.getChildren().clear();
        Content_TextArea.setText("Other tools test Area");
        ImageLabelButton DataView = new ImageLabelButton(Path.TextIcon,"Data view");
        DataView.setOnMouseClicked(event -> {
            Stage ChIP_Seq_Pipeline_Stage = new Stage();
            ChIP_Seq_Pipeline_Stage.setTitle("Data view");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(Path.DataViewFxml));
                ChIP_Seq_Pipeline_Stage.setScene(new Scene(loader.load(),1500,900));
                com.github.snowflake.Other_Tools_Fx.DataView DataView_Controller = loader.getController();
                DataView_Controller.init();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ChIP_Seq_Pipeline_Stage.show();
        });
        Content_FlowPane.getChildren().addAll(DataView);
    }
}
