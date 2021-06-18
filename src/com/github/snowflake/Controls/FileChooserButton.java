package com.github.snowflake.Controls;

import com.github.snowflake.Archive.Path;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class FileChooserButton extends Button {
    private final FileChooser chooser = new FileChooser();
    private TextInputControl text = new TextField();

    public FileChooserButton() {
        super();
        init();
    }

    private void init() {
        setText("Select");
        setAlignment(Pos.CENTER);
        chooser.setInitialDirectory(new File(Path.ProgramPath));
        setOnMouseClicked(event -> {
            text.setText(chooser.showOpenDialog(new Stage()).getPath());
        });
    }

    public FileChooser getChooser() {
        return chooser;
    }


    public void setText(TextInputControl t) {
        text = t;
    }
}
