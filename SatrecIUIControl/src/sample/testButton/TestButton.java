package sample.testButton;

import javafx.scene.control.Button;

/**
 * Created by GSD on 2016-12-28.
 */
public class TestButton extends Button{
    public TestButton(){
        getStylesheets().add("/sample/testButton/testbutton.css");
        getStyleClass().add("test-button");
    }
}
