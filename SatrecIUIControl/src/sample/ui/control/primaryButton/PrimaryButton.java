package sample.ui.control.primaryButton;

import javafx.scene.control.Button;

/**
 * Created by GSD on 2016-12-23.
 */
public class PrimaryButton extends Button {
    public PrimaryButton(){
        getStyleClass().add("primary-button");
        getStylesheets().add("sample/ui/control/primaryButton/primarybutton.css");
    }
}
