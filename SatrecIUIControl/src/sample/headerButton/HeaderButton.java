package sample.headerButton;

import javafx.scene.control.ToggleButton;

/**
 * Created by GSD on 2016-12-28.
 */
public class HeaderButton extends ToggleButton {
    public HeaderButton(){
        getStylesheets().add("sample/headerButton/headerbutton.css");
        getStyleClass().add("header-button");
    }
}
