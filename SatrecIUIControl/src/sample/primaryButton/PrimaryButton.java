package sample.primaryButton;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * Created by GSD on 2016-12-23.
 */
public class PrimaryButton extends Button {
    public PrimaryButton(){
        getStyleClass().add("primary-button");
        getStylesheets().add("sample/primaryButton/primarybutton.css");
    }
}
