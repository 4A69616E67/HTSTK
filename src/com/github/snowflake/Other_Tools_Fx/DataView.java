package com.github.snowflake.Other_Tools_Fx;

import com.github.snowflake.Archive.Path;
import com.github.snowflake.Controls.TreeFile;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class DataView {

    public TreeView<TreeFile> DataView_TreeView;
    public TextField DataView_TextField;
    public Button FileSelectButton;

    public void init() {
        TreeItem<TreeFile> root = new TreeItem<>(new TreeFile(Path.ProgramPath));
        root.setExpanded(true);
        for (File f : root.getValue().listFiles()) {
            TreeItem<TreeFile> file_view = new TreeItem<>(new TreeFile(f.getPath()));
            if (f.isDirectory()) {
                add_dir(file_view, f);
            }
            root.getChildren().add(file_view);
        }
        DataView_TreeView.setRoot(root);
    }

    private void add_dir(TreeItem<TreeFile> root, File f) {
        if (f.listFiles() != null) {
            for (File i : f.listFiles()) {
                TreeItem<TreeFile> tf = new TreeItem<>(new TreeFile(i.getPath()));
                if (i.isDirectory()) {
                    add_dir(tf, i);
                }
                root.getChildren().add(tf);
            }
        }
    }


    public void ShowSelectedItem(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            TreeItem<TreeFile> selected_item = DataView_TreeView.getSelectionModel().getSelectedItem();
            if (selected_item.isLeaf()) {
                DataView_TextField.setText(selected_item.getValue().getPath());
            }
        }
    }

    public void ChoseFile(MouseEvent mouseEvent) {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(Path.ProgramPath));
        File f = chooser.showOpenDialog(new Stage());
        if (f != null) {
            DataView_TextField.setText(f.getPath());
        }
    }
}
