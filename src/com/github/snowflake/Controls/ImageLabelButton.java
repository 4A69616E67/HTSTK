package com.github.snowflake.Controls;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ImageLabelButton extends Button {

    private final double PREFWIDTH=80;
    public ImageLabelButton(String text) {
        setText(text);
        init();
    }

    public ImageLabelButton(String image_path, String text) {
        ImageView view = new ImageView(image_path);
        view.setFitHeight(PREFWIDTH);
        view.setFitWidth(PREFWIDTH);
        setGraphic(view);
        setText(text);
        init();
    }

    private void init() {

        this.setContentDisplay(ContentDisplay.TOP);
        setPrefWidth(PREFWIDTH);
        setWrapText(true);
        setFont(new Font(10));
        setTextAlignment(TextAlignment.CENTER);
    }
}
