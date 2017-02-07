package sample.ui.control.darklistview;

import javafx.scene.control.ListView;

/**
 * Created by GSD on 2016-12-30.
 */
public class Gk2DarkListView extends ListView {
    public Gk2DarkListView(){
        getStylesheets().add("sample/ui/control/darklistview/gk2darklistview.css");
        getStyleClass().add("gk2-dark-list-view");
    }
}
