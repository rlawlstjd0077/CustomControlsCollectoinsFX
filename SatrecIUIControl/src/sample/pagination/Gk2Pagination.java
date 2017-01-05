package sample.pagination;

import javafx.scene.control.Label;
import javafx.scene.control.Pagination;

/**
 * Created by GSD on 2016-12-30.
 */
public class Gk2Pagination extends Pagination {
    public Gk2Pagination(){
        getStylesheets().add("sample/pagination/gk2pagination.css");
        getStyleClass().add("gk2-pagination");
    }
}
