package com.github.snowflake.Other_Tools_Fx;

import com.github.snowflake.Archive.Path;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.File;

public class DataView {

    public TreeView DataView_TreeView;

    public void init() {
        TreeItem<File> root = new TreeItem<>(new File(Path.ProgramPath));
        root.setExpanded(true);
        for (File f : root.getValue().listFiles()) {
            TreeItem<File> file_view = new TreeItem<>(f);
//            file_view
            root.getChildren().add(file_view);
        }
        DataView_TreeView.setRoot(root);

    }

    private void add_dir(TreeView<String> view){

    }
}
