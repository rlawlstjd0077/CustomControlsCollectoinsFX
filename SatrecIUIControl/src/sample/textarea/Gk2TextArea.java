package sample.textarea;

import javafx.scene.control.TextArea;

/**
 * Created by GSD on 2016-12-27.
 */
public class Gk2TextArea extends TextArea {
    public Gk2TextArea(String text) {
        super(text);
        getStylesheets().add("/commons/ui/control/textarea/textarea.css");
        getStyleClass().add("textarea");
    }

    public Gk2TextArea() {
        this(null);
    }
}
