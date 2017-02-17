package ui.common;

import javafx.scene.control.CheckBox;

/**
 * Created by GSD on 2017-01-23.
 */
public class FDSCheckBox extends CheckBox{
  public FDSCheckBox(){
    getStylesheets().add("/fds/ui/common/fds_check_box.css");
    getStyleClass().add("fds-check-box");
  }
}
