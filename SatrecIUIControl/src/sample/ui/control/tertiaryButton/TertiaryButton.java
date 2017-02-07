package sample.ui.control.tertiaryButton;

import javafx.scene.control.Button;

/**
 * Created by GSD on 2016-12-27.
 */
public class TertiaryButton extends Button{
    public TertiaryButton(){
        getStyleClass().add("tertiary-button");
        getStylesheets().add("sample/ui/control/tertiaryButton/tertiarybutton.css");
    }
}