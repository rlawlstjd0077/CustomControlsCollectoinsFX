package sample.secondaryButton;

import javafx.scene.control.Button;

/**
 * Created by GSD on 2016-12-27.
 */
public class SecondaryButton extends Button {
    public SecondaryButton(){
        getStyleClass().add("secondary-button");
        getStylesheets().add("sample/secondaryButton/secondarybutton.css");
    }
}
