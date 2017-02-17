package ui.common;

import javafx.scene.control.CheckBox;

/**
 * Created by GSD on 2017-02-06.
 */
public class FDSChartCheckBox extends CheckBox{
  public FDSChartCheckBox(){
    getStylesheets().add("/mps/gk2amps/ui/common/fds_chart_check_box.css");
    getStyleClass().add("fds-chart-check-box");
  }
}
