package sample.formButton;

import javafx.scene.control.Button;

/**
 * Created by GSD on 2016-12-28.
 */
public class FormButton extends Button{
    public FormButton(){
        getStylesheets().add("/sample/formButton/formbutton.css");
        getStyleClass().add("form-button");
    }
}
