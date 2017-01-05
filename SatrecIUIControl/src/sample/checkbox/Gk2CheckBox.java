package sample.checkbox;

import com.sun.javafx.scene.control.skin.CheckBoxSkin;
import javafx.scene.control.CheckBox;

/**
 * Created by GSD on 2016-12-27.
 */
public class Gk2CheckBox extends CheckBox{
    public Gk2CheckBox(){
        getStylesheets().add("sample/checkbox/checkbox.css");
        getStyleClass().add("gk2-check-box");
    }
}
