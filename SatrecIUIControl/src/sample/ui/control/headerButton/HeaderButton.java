package sample.ui.control.headerButton;

import javafx.scene.control.ToggleButton;

/**
 * Created by GSD on 2016-12-28.
 */
public class HeaderButton extends ToggleButton {
    public HeaderButton(){
        getStylesheets().add("sample/ui/control/headerButton/headerbutton.css");
        getStyleClass().add("header-button");
    }
}