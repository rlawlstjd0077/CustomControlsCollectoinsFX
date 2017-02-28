package ui.common;

import javafx.beans.property.StringProperty;
import javafx.scene.control.CheckBox;

/**
 * Created by GSD on 2017-02-28.
 */
public class DeterminationStationDataTableRowViewModel {
  private StringProperty item;
  private CheckBox appR;
  private CheckBox appA;
  private CheckBox appE;
  private CheckBox estR;
  private CheckBox estA;
  private CheckBox estE;

  public DeterminationStationDataTableRowViewModel(StringProperty item, boolean appR,
                                                   boolean appA, boolean appE, boolean estR,
                                                   boolean estA, boolean estE) {
    this.item = item;
    this.appR = new CheckBox();
    this.appR.setSelected(appR);
    this.appR.getStyleClass().add("fds-check-box");
    this.appA = new CheckBox();
    this.appA.setSelected(appA);
    this.appA.getStyleClass().add("fds-check-box");
    this.appE = new CheckBox();
    this.appE.setSelected(appE);
    this.appE.getStyleClass().add("fds-check-box");
    this.estR = new CheckBox();
    this.estR.setSelected(estR);
    this.estR.getStyleClass().add("fds-check-box");
    this.estA = new CheckBox();
    this.estA.setSelected(estA);
    this.estA.getStyleClass().add("fds-check-box");
    this.estE = new CheckBox();
    this.estE.setSelected(estE);
    this.estE.getStyleClass().add("fds-check-box");
  }

  public StringProperty itemProperty() {
    return item;
  }

  public CheckBox getAppR() {
    return appR;
  }

  public CheckBox getAppA() {
    return appA;
  }

  public CheckBox getAppE() {
    return appE;
  }

  public CheckBox getEstR() {
    return estR;
  }

  public CheckBox getEstA() {
    return estA;
  }

  public CheckBox getEstE() {
    return estE;
  }
}
