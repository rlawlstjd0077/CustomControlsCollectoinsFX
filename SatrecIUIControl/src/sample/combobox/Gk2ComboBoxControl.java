package sample.combobox;

import javafx.scene.control.ComboBox;

/**
 * Created by GSD on 2016-12-27.
 */
public class Gk2ComboBoxControl extends ComboBox {
    public Gk2ComboBoxControl(){
        getStylesheets().add("sample/combobox/combobox.css");
        getStyleClass().add("gk2-combo-box");
        setEditable(true);
    }
}
