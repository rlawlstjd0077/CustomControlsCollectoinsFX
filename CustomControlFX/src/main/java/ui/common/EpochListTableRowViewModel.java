package ui.common;

import javafx.beans.property.StringProperty;

/**
 * Created by GSD on 2017-01-18.
 */
public class EpochListTableRowViewModel {
  private StringProperty item;
  private StringProperty value;
  private StringProperty unit;

  public EpochListTableRowViewModel(StringProperty item, StringProperty value, StringProperty unit) {
    this.item = item;
    this.value = value;
    this.unit = unit;
  }

  public StringProperty itemProperty() {
    return item;
  }

  public StringProperty valueProperty() {
    return value;
  }

  public StringProperty unitProperty() {
    return unit;
  }
}
