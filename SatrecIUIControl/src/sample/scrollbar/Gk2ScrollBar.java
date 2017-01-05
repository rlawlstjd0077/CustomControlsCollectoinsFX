package sample.scrollbar;

import javafx.scene.control.ScrollBar;

/**
 * Created by GSD on 2016-12-29.
 */
public class Gk2ScrollBar extends ScrollBar{
    public Gk2ScrollBar(){
        getStylesheets().add("sample/scrollbar/gk2scrollbar.css");
        getStyleClass().add("gk2-scroll-bar");
    }
}
