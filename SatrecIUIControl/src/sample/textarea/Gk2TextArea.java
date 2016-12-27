package sample.textarea;

import javafx.scene.control.TextArea;

/**
 * Created by GSD on 2016-12-27.
 */
public class Gk2TextArea extends TextArea {
    public Gk2TextArea(){
        getStylesheets().add("sample/textarea/textarea.css");
        getStyleClass().add("textarea");
    }
}
