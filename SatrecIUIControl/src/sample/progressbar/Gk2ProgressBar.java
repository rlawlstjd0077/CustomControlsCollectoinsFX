package sample.progressbar;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 * Created by GSD on 2016-12-30.
 */
public class Gk2ProgressBar extends ProgressBar {
    public Gk2ProgressBar(){
        getStylesheets().add("sample/progressbar/gk2progressbar.css");
        getStyleClass().add("gk2-progress-bar");
    }
}
