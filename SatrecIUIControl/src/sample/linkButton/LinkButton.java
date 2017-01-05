package sample.linkButton;

import javafx.scene.control.Button;

/**
 * Created by GSD on 2016-12-27.
 */
public class LinkButton extends Button{
    public LinkButton(){
        getStylesheets().add("sample/linkButton/linkbutton.css");
        getStyleClass().add("link-button");
    }
}