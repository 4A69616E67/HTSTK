package com.github.snowflake.Launcher.Content;

import com.github.snowflake.Archive.Path;
import com.github.snowflake.Controls.ImageLabelButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Content {
    public FlowPane Content_FlowPane;
    public TextArea Content_TextArea;
    public VBox Content_VBox;

    public void InitChIPSeqContent()throws Exception{
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


}
