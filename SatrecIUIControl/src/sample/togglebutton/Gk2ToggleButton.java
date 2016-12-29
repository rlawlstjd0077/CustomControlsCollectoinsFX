package sample.togglebutton;

import javafx.scene.control.ToggleButton;

/**
 * Created by GSD on 2016-12-29.
 */
public class Gk2ToggleButton extends ToggleButton{
    public Gk2ToggleButton(){
        getStylesheets().add("sample/togglebutton/gk2togglebutton.css");
        getStyleClass().add("toggle-button");
    }
}
